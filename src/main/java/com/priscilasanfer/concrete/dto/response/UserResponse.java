package com.priscilasanfer.concrete.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String password;
    private List<PhoneResponse> phones = new ArrayList<>();
    private Instant created;
    private Instant modified;

    @JsonProperty("last_login")
    private Instant lastLogin;
    private String token;
}
