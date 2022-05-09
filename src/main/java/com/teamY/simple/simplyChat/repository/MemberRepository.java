package com.teamY.simple.simplyChat.repository;

import com.teamY.simple.simplyChat.mapper.MemberMapper;
import com.teamY.simple.simplyChat.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class MemberRepository {

    private final MemberMapper mapper;

    public MemberVO selectMemberByNickname(String nickname) {
        return mapper.selectMemberByNickname(nickname);
    }
    public void insertMember(MemberVO memberVO){
        mapper.insertMember(memberVO);
    }
}
