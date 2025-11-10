package com.hk.emr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.emr.dtos.PatientDto;
import com.hk.emr.mapper.StaffMapper;

@Service
public class StaffService {
	@Autowired
	private StaffMapper staffMapper;

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
	
	
}
