package com.copinstagram.instagram.member.service;

import com.copinstagram.instagram.member.model.dto.MemberSignUpRequestDto;
import com.copinstagram.instagram.member.repository.MemberRepository;
import com.copinstagram.instagram.member.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Long joinUser(MemberSignUpRequestDto memberSignUpRequestDto) {
        // 비밀번호 암호화
        memberSignUpRequestDto.setPassword(passwordEncoder.encode(memberSignUpRequestDto.getPassword()));
        return memberRepository.save(memberSignUpRequestDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return memberRepository.findByEmail(userEmail).orElseThrow(()->new UsernameNotFoundException("Username not found '"+ userEmail + "'"));
    }
}