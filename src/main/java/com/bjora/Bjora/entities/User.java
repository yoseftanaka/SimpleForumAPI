package com.bjora.Bjora.entities;

import com.bjora.Bjora.enumerated.Gender;
import com.bjora.Bjora.enumerated.Role;
import com.bjora.Bjora.validator.Unique;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    @NotBlank @Size(max = 100)
    private String name;

    @NotBlank @Unique
    private String email;

    @NotBlank @Size(min = 6)
    private String password;

    @Enumerated(EnumType.STRING) @NotNull
    private Gender gender;

    @NotBlank
    private String address;

    @Column(name = "profile_picture") @NotBlank
    private String profilePicture;

    @NotNull
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
