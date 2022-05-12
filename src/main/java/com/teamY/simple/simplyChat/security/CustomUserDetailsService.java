package com.teamY.simple.simplyChat.security;

import com.teamY.simple.simplyChat.repository.MemberRepository;
import com.teamY.simple.simplyChat.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO memberVO = memberRepository.selectMemberByNickname(username);

        if(memberVO == null)
            throw new UsernameNotFoundException("UsernameNotFoundException, memberVo == null");

        log.info("login Member : " + memberVO.toString());

        String systemFileName = "https://t1.daumcdn.net/cfile/tistory/2513B53E55DB206927";
        return MemberPrincipal.createMemberPrincipal(memberVO, systemFileName);
    }
}
