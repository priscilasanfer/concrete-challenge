package com.priscilasanfer.concrete.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhonesResponse {
    private String number;
    private String ddd;

}
