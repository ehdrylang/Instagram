package com.copinstagram.instagram.configuration;

import com.copinstagram.instagram.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MemberService memberService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        String encodedPassword = passwordEncoder.encode(password);
        UserDetails loadedUser = memberService.loadUserByUsername(username);
        if(loadedUser == null){
            throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
        }

        return new UsernamePasswordAuthenticationToken(loadedUser, null, loadedUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
        //return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}