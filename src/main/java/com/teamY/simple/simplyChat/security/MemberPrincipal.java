package com.teamY.simple.simplyChat.security;

import com.teamY.simple.simplyChat.vo.MemberVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Member;
import java.util.Collection;
import java.util.Collections;

@Getter
@RequiredArgsConstructor
public class MemberPrincipal implements UserDetails {

    private final int id;
    private final String nickname;
    private final String password;
    private final Collection<GrantedAuthority> authorities;

    public MemberVO getMemberVO() {
        return new MemberVO(id, nickname);
    }

    public static MemberPrincipal createMemberPrincipal(MemberVO memberVO) {
        return new MemberPrincipal(
                memberVO.getId(), memberVO.getNickname(), memberVO.getPassword()
                , Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
