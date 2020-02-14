package com.bjora.Bjora.entities;

import com.bjora.Bjora.enumerated.Gender;
import com.bjora.Bjora.enumerated.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String address;

    @Column(name = "profile_picture")
    private String profilePicture;
    private Date birthday;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<Question> questions;

    @OneToMany(mappedBy = "user")
    private List<Answer> answers;

    @OneToMany(mappedBy = "sender")
    private List<Message> senders;

    @OneToMany(mappedBy = "receiver")
    private List<Message> receivers;

}
