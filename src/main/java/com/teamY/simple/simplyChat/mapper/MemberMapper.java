package com.teamY.simple.simplyChat.mapper;

import com.teamY.simple.simplyChat.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberVO selectTest();
}
