package com.ua.sutty.servervue.form;

import com.ua.sutty.servervue.model.Role;
import com.ua.sutty.servervue.model.User;
import com.ua.sutty.servervue.validator.Birthday;
import com.ua.sutty.servervue.validator.ExistEmail;
import com.ua.sutty.servervue.validator.ExistUsername;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserForm {

    @ExistUsername
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_.]{1,20}$")
    private String username;
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")
    private String password;
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")
    private String confirmPassword;
    @ExistEmail
    @Email
    private String email;
    @Pattern(regexp = "[A-Z][a-z]{1,25}")
    private String firstName;
    @Pattern(regexp = "[A-Z][a-z]{1,25}")
    private String lastName;
    @Birthday
    private LocalDate birthday;
    @Pattern(regexp = "ADMIN|USER")
    private String role;

    public User toUser() {
        Role role = this.role.equals("ADMIN") ? Role.ADMIN : Role.USER;
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .birthday(birthday)
                .role(role)
                .build();
    }

}
