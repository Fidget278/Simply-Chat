package com.teamY.simple.simplyChat.dto;

import lombok.*;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class JoinDTO {
    private String nickname;
    private String password;
}
