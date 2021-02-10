package com.priscilasanfer.concrete.dto.request;

import com.priscilasanfer.concrete.model.Phone;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Phone> phones = new ArrayList<>();
}
