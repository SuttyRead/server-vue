package com.ua.sutty.servervue.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")
    private String password;
    @Column(unique = true)
    private String email;
    @Pattern(regexp = "[A-Z][a-z]{1,25}")
    private String firstName;
    @Pattern(regexp = "[A-Z][a-z]{1,25}")
    private String lastName;
    @Past
    private LocalDate birthday;
    @Enumerated(value = EnumType.STRING)
    private Role role;

}
