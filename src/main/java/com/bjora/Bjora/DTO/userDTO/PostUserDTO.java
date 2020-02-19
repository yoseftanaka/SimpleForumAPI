package com.bjora.Bjora.DTO.userDTO;

import com.bjora.Bjora.enumerated.Gender;
import com.bjora.Bjora.enumerated.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PostUserDTO {
    private String address;
    private Date birthday;
    private String email;
    private Gender gender;
    private String name;
    private String password;
    private String profilePicture;
    private Role role;
}
