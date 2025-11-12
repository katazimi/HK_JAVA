package com.hk.emr.service; // ◀ 본인 패키지 경로에 맞게 수정

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hk.emr.dtos.MemberDto;
import com.hk.emr.mapper.MemberMapper; // ◀ 기존에 사용하시던 매퍼

@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    private MemberMapper memberMapper; // ◀ DB 조회를 위해 기존 매퍼 주입

    /**
     * Spring Security가 로그인 처리 시 호출하는 메서드
     * @param username (로그인 폼에서 입력된 ID)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // 1. (기존 로직 재활용) MemberMapper를 사용해 사용자 정보를 가져옴
        MemberDto memberDto = memberMapper.loginUser(username);

        if (memberDto == null) {
            // 2. 사용자가 없으면 예외 발생
            System.out.println("UserLoginService: 사용자를 찾을 수 없음 - " + username);
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
        }

        // 3. Spring Security가 알아볼 수 있는 UserDetails 객체로 변환
        // (DB의 Role 이름이 "DOCTOR", "ADMIN"이라고 가정)
        return org.springframework.security.core.userdetails.User
                .withUsername(memberDto.getUserName())
                .password(memberDto.getPassword())
                .roles(memberDto.getRole()) // ◀ "DOCTOR", "STAFF", "ADMIN"
                .build();
    }
}