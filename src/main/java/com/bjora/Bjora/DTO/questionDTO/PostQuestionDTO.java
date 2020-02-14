package com.bjora.Bjora.DTO.questionDTO;

import com.bjora.Bjora.enumerated.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostQuestionDTO {
    private Status status;
    private String title;
    private int memberId;
    private int topicId;
}
