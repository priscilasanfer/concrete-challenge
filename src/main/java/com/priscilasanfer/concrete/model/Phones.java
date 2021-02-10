package com.priscilasanfer.concrete.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Phones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String ddd;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
