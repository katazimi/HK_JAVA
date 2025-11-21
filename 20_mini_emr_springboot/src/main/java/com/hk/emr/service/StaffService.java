package com.hk.emr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.emr.dtos.AppointmentDto;
import com.hk.emr.dtos.DoctorDto;
import com.hk.emr.dtos.PatientDto;
import com.hk.emr.mapper.StaffMapper;

@Service
public class StaffService {
	@Autowired
	private StaffMapper staffMapper;
	
	@Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

	@Transactional
	public boolean addPatient(PatientDto dto) {
	    // 1. 첫 번째 INSERT (이름, 연락처 등 기본 정보만)
	    //    (chart_number는 아직 null 또는 임시값)
	    int result1 = staffMapper.addPatient(dto);
	    
	    if (result1 == 0) {
	        return false;
	    }
	    
	    // 2. (중요) 방금 생성된 patient_id를 DTO에서 가져옵니다.
	    //    (Mapper XML에서 useGeneratedKeys="true" keyProperty="patientId" 설정 필요)
	    Integer newPatientId = dto.getPatientId();
	    
	    if (newPatientId == null) {
	        throw new RuntimeException("신규 환자 ID(patient_id)를 가져오는데 실패했습니다.");
	    }
	    
	    // 3. 차트번호 생성 (예: P + 7자리 숫자)
	    String chartNum = "P" + String.format("%07d", newPatientId);
	    
	    // 4. 두 번째 UPDATE (차트번호만 업데이트)
	    dto.setChartNum(chartNum);
	    int result2 = staffMapper.updateChartNum(dto); // (DTO에 patientId와 chartNumber가 모두 있음)
	    
	    return result2 > 0;
	}

	public List<PatientDto> getPatients() {
		return staffMapper.getPatients();
	}

	public List<PatientDto> patientSearch(String query) {
		return staffMapper.patientSearch(query);
	}

	public List<DoctorDto> getDoctors() {
		return staffMapper.getDoctors();
	}

	public void createWalkInAppointment(AppointmentDto dto, Integer staffId) {
		dto.setStaffId(staffId);
		dto.setStatus("WAITING");
		staffMapper.createWalkInAppointment(dto);
	}

	public void createScheduledAppointment(AppointmentDto dto, Integer staffId) {
		dto.setStaffId(staffId);
		dto.setStatus("SCHEDULED");
		staffMapper.createScheduledAppointment(dto);
	}

	public List<AppointmentDto> getSchedules() {
		return staffMapper.getSchedules();
	}

	@Transactional
	public void checkInPatient(int appointmentId) {
		// 1. (기본 로직) DB 상태를 'WAITING'으로 변경
	    int updateCount = staffMapper.updateStatus(appointmentId);
	    
	    if (updateCount == 0) {
	        throw new RuntimeException("환자 접수 처리에 실패했습니다.");
	    }

	    // 2. (다른 로직) 방금 접수된 환자 정보와 담당 의사 ID를 조회
	    // (예: patientName, patientId, doctorId 등)
	    AppointmentDto patientInfo = staffMapper.getPatientInfo(appointmentId);

	    // 3. (핵심) WebSocket을 통해 해당 의사의 Topic으로 메시지 전송
	    // (컨트롤러에서 SimpMessagingTemplate을 주입받아야 함)
	    simpMessagingTemplate.convertAndSend(
	        "/topic/doctor/" + patientInfo.getDoctorId(), // 의사 전용 채널
	        patientInfo // (JSON으로 변환될 환자 정보 객체)
	    );
		
	}

	public List<AppointmentDto> getPaymentList() {
		return staffMapper.getPaymentList();
	}

	public void paymentComplete(int appointmentId) {
		staffMapper.paymentComplete(appointmentId);
		
	}
	
	@Transactional // (데이터 변경이므로 트랜잭션 처리)
    public boolean updatePatient(PatientDto dto) {
        
        int result = staffMapper.updatePatient(dto);
        
        if (result == 0) {
            // 업데이트된 행이 없으면 예외 발생 (트랜잭션 롤백)
            throw new RuntimeException("환자 정보 수정에 실패했습니다. (ID: " + dto.getPatientId() + ")");
        }
        
        return true;
    }
}
