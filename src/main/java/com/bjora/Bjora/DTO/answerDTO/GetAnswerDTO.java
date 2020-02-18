package com.bjora.Bjora.DTO.answerDTO;

import com.bjora.Bjora.DTO.questionDTO.GetQuestionDTO;
import com.bjora.Bjora.DTO.userDTO.GetUserDTO;
import lombok.Data;

import java.util.Date;

@Data
public class GetAnswerDTO {
    private String answer;
    private GetQuestionDTO question;
    private Date createdAt;

    public GetAnswerDTO(String answer, GetQuestionDTO question, Date createdAt) {
        this.answer = answer;
        this.question = question;
        this.createdAt = createdAt;
    }
}
