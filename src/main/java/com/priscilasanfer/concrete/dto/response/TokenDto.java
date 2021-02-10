package com.priscilasanfer.concrete.dto.response;

import lombok.Getter;

@Getter
public class TokenDto {
    private String token;
    private String type;

    public TokenDto(String token, String type) {
        this.token = token;
        this.type = type;
    }
}
