package com.bjora.Bjora.DTO.messageDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostMessageDTO {
    private String message;
    private Integer receiverId;
    private Integer senderId;
}
