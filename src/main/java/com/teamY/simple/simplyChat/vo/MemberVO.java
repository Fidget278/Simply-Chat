package com.teamY.simple.simplyChat.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@ToString
@Getter
@AllArgsConstructor
@Alias("MemberVO")
public class MemberVO {

    private int id;
    private int fileId;
    private String nickname;
    private String password;


    public MemberVO(int id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public MemberVO(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

}
