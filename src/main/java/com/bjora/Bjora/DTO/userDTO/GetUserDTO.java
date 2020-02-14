package com.bjora.Bjora.DTO.userDTO;

import com.bjora.Bjora.enumerated.Gender;
import com.bjora.Bjora.enumerated.Role;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class GetUserDTO {
    private String address;
    private Date birthday;
    private String email;
    private String name;
    private String profilePicture;

    public GetUserDTO(String address, Date birthday, String email, String name, String profilePicture) {
        this.address = address;
        this.birthday = birthday;
        this.email = email;
        this.name = name;
        this.profilePicture = profilePicture;
    }
}
