package com.hk.emr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 임포트 확인

import com.hk.emr.command.AddDepartmentCommand;
import com.hk.emr.command.AddUserCommand;
import com.hk.emr.dtos.DepartmentDto;
import com.hk.emr.dtos.DoctorDto;
import com.hk.emr.dtos.MemberDto;
import com.hk.emr.mapper.DepartmentMapper;
import com.hk.emr.mapper.DoctorMapper;
import com.hk.emr.mapper.MemberMapper;
import com.hk.emr.status.RoleStatus;

@Service
public class AdminService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Autowired
	private DoctorMapper doctorMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    /**
     * 사용자 추가 (회원가입)
     * 의사(DOCTOR)일 경우, DOCTOR_PROFILE에도 추가 정보를 생성합니다.
     */
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
                // keyProperty 설정이 잘못되었거나 DB 설정 문제일 수 있음
                throw new RuntimeException("신규 사용자 ID(user_id)를 가져오는데 실패했습니다.");
            }
            
            DoctorDto ddto = new DoctorDto();
            
            // 7. DoctorDto에 user_id 설정 (DOCTOR_PROFILE의 FK)
            // (DoctorDto에 setUserId()가 있다고 가정)
            ddto.setUserId(newUserId); 
            
            // (선택) AddUserCommand에 의사 관련 정보가 더 있다면 여기서 설정
            // 예: ddto.setSpecialty(addUserCommand.getSpecialty());
            
            // 8. DOCTOR_PROFILE 테이블에 INSERT
            int doctorResult = doctorMapper.addDoctor(ddto);

            if (doctorResult == 0) {
                // 의사 프로필 생성 실패 시, @Transactional이 userResult(회원가입)까지 롤백함
                throw new RuntimeException("의사 프로필 생성에 실패했습니다.");
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
	
}