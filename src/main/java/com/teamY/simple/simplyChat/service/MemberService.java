package com.teamY.simple.simplyChat.service;

import com.teamY.simple.simplyChat.repository.MemberRepository;
import com.teamY.simple.simplyChat.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberVO retrieveMemberByNickname(String nickname) {
        return memberRepository.selectMemberByNickname(nickname);
    }
    public void registerMember(MemberVO memberVO) {
        memberRepository.insertMember(memberVO);
    }
}
