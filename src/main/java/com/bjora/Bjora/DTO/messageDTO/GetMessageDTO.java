package com.bjora.Bjora.DTO.messageDTO;

import com.bjora.Bjora.DTO.userDTO.GetUserDTO;
import lombok.Data;

import java.util.Date;

@Data
public class GetMessageDTO {
    private Date createdAt;
    private String message;
    private GetUserDTO receiver;
    private GetUserDTO sender;

    public GetMessageDTO(Date createdAt, String message, GetUserDTO receiver, GetUserDTO sender) {
        this.createdAt = createdAt;
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
    }
}
