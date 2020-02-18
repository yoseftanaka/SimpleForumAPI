package com.bjora.Bjora.DTO.answerDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostAnswerDTO {
    private String answer;
    private Integer questionId;
    private Integer userId;
}
