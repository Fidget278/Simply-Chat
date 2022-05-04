package com.teamY.simple.simplyChat.vo;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@ToString
@AllArgsConstructor
@Alias("MemberVO")
public class MemberVO {

    private int id;
    private String text;
}
