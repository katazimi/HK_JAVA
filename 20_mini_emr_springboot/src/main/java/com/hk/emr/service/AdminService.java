package com.hk.emr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 임포트 확인

import com.hk.emr.command.AddDepartmentCommand;
import com.hk.emr.command.AddUserCommand;
import com.hk.emr.dtos.AdminDashBoardDto;
import com.hk.emr.dtos.DepartmentDto;
import com.hk.emr.dtos.DoctorDto;
import com.hk.emr.dtos.MemberDto;
import com.hk.emr.dtos.ScheduleDto;
import com.hk.emr.dtos.ScheduleFormDto;
import com.hk.emr.mapper.AdminMapper;
import com.hk.emr.mapper.DepartmentMapper;
import com.hk.emr.mapper.DoctorMapper;
import com.hk.emr.mapper.MemberMapper;
import com.hk.emr.status.RoleStatus;

@Service
public class AdminService {
	
	@Autowired
    private AdminMapper adminMapper;

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Autowired
	private DoctorMapper doctorMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public AdminDashBoardDto getDashboardStats() {
        return adminMapper.getDashboardStats();
    }
	
	@Transactional // 1. 트랜잭션 처리
	public boolean addUser(AddUserCommand addUserCommand) {
		
		MemberDto mdto = new MemberDto();
		mdto.setUserName(addUserCommand.getId());
		mdto.setName(addUserCommand.getName());
		
		// 2. 비밀번호 암호화
		mdto.setPassword(passwordEncoder.encode(addUserCommand.getPassword()));
		
		// 3. 역할 설정
		if (addUserCommand.getRole().equals("DOCTOR")) {
			mdto.setRole(RoleStatus.DOCTOR.toString()); // RoleStatus.DOCTOR + "" 보다 .toString() 권장
		} else if (addUserCommand.getRole().equals("STAFF")) {
			mdto.setRole(RoleStatus.STAFF.toString());
		} else if (addUserCommand.getRole().equals("ADMIN")) {
			mdto.setRole(RoleStatus.ADMIN.toString());
		}
		
		// 4. USER_ACCOUNT 테이블에 INSERT
        // (MemberMapper.xml에 useGeneratedKeys="true" keyProperty="userId" 설정 필요)
		int userResult = memberMapper.addUser(mdto);
		
		if (userResult == 0) {
            return false; // 회원가입(USER_ACCOUNT) 실패
        }
		
		// 5. 역할이 DOCTOR인 경우, DOCTOR_PROFILE에 추가 INSERT
        if (addUserCommand.getRole().equals("DOCTOR")) {
            
            // 6. ★★★ 방금 USER_ACCOUNT에 생성된 user_id를 mdto에서 가져옴
            Integer newUserId = mdto.getUserId(); 
            
            if (newUserId == null) {
                throw new RuntimeException("신규 사용자 ID(user_id)를 가져오는데 실패했습니다.");
            }
            
            DoctorDto ddto = new DoctorDto();
            
            // 7. DoctorDto에 user_id 설정 (DOCTOR_PROFILE의 FK)
            ddto.setUserId(newUserId); 
            
            // 8. DOCTOR_PROFILE 테이블에 INSERT
            int doctorResult = doctorMapper.addDoctor(ddto);

            if (doctorResult == 0) {
                throw new RuntimeException("의사 프로필 생성에 실패했습니다.");
            }
         // 9. [로직 추가] 방금 DOCTOR_PROFILE에 생성된 doctor_id를 가져옴
            Integer newDoctorId = ddto.getDoctorId(); // ddto에 doctorId가 반환되어야 함
                
            if (newDoctorId == null) {
                // @Transactional에 의해 롤백됨
                throw new RuntimeException("신규 의사 ID(doctor_id)를 가져오는데 실패했습니다.");
            }

            // 10. [로직 추가] SCHEDULE 테이블에 7일치 기본값 INSERT
            // (이전에 작성한 memberMapper의 addSchedule 쿼리 호출)
            int scheduleResult =doctorMapper.addSchedule(newDoctorId);
                
            if (scheduleResult < 7) { // 7개 행이 삽입되어야 함
                // @Transactional에 의해 롤백됨
                throw new RuntimeException("의사 기본 스케줄 생성에 실패했습니다.");
            }
        }
        
        return true; // 모든 작업 성공
    }
	
	public String idChk(String id) {
		return memberMapper.idChk(id);
	}

	public List<MemberDto> findAllAccounts() {
		
		return memberMapper.findAllAccounts();
	}

	public boolean addDepartment(AddDepartmentCommand addDepartmentCommand) {
		
		return  departmentMapper.addDepartment(addDepartmentCommand.getName());
	}

	public List<DepartmentDto> findAllDepartments() {
		
		return departmentMapper.findAllDepartments();
	}

	public List<DoctorDto> getDoctorList() {
		return doctorMapper.getDoctorList();
	}

	public List<ScheduleDto> findSchedulesByDoctorId(int doctorId) {
		
		return doctorMapper.findSchedulesByDoctorId(doctorId);
	}

	@Transactional // 7개 쿼리가 모두 성공하거나 모두 실패해야 함
    public void updateSchedules(ScheduleFormDto form) {
        
        Integer doctorId = form.getDoctorId();
        
        // 1. 폼(Map)을 매퍼용(List)으로 변환
        List<ScheduleDto> listToUpdate = new ArrayList<>();
        
        for (Map.Entry<String, ScheduleDto> entry : form.getSchedules().entrySet()) {
            
            ScheduleDto dayData = entry.getValue();
            
            ScheduleDto dto = new ScheduleDto();
            dto.setDoctorId(doctorId);
            dto.setDayOfWeek(entry.getKey()); // "MONDAY", "TUESDAY" ...
            dto.setStartTime(dayData.getStartTime());
            dto.setEndTime(dayData.getEndTime());
            
            // 2. ★★★ 체크박스(Boolean)를 boolean으로 변환 ★★★
            // 폼에서 체크가 안 되면 'null'이 오므로, 'false' (0)으로 바꿔줘야 함
            int isChecked = dayData.getIsClosed()==null?0:dayData.getIsClosed();
            dto.setIsClosed(isChecked);
            
            listToUpdate.add(dto);
        }

        // 3. 매퍼 호출 (7개 쿼리 실행)
        doctorMapper.updateSchedule(listToUpdate);
    }
	
	@Transactional
	public boolean updateAccount(MemberDto dto) {
		int result = memberMapper.updateAccount(dto); 
        if (result == 0) {
            throw new RuntimeException("수정된 계정이 없습니다. (ID: " + dto.getUserId() + ")");
        }
        return true;
	}

	@Transactional
	public boolean deleteAccount(int userId) {
		MemberDto account = memberMapper.getUser(userId); // (이 쿼리가 필요)
        
		if (account == null) {
            throw new RuntimeException("삭제할 계정을 찾을 수 없습니다.");
        }

        if ("DOCTOR".equals(account.getRole())) {
            // 2. DOCTOR라면, 의존하는 데이터(SCHEDULE, DOCTOR_PROFILE)를 먼저 삭제
            Integer doctorId = doctorMapper.getDoctorId(userId); // (이 쿼리가 필요)
            if (doctorId != null) {
                // (경고: APPOINTMENT/CHART에 FK가 걸려있다면 이 쿼리도 실패합니다)
                doctorMapper.deleteSchedulesByDoctorId(doctorId); // (이 쿼리가 필요)
                doctorMapper.deleteDoctorProfile(doctorId);       // (이 쿼리가 필요)
            }
        }
        
        // 3. USER_ACCOUNT 삭제
        int result = memberMapper.deleteAccount(userId); // (이 쿼리가 필요)
        if (result == 0) {
            throw new RuntimeException("계정 삭제에 실패했습니다.");
        }
        return true;
	}
	
}