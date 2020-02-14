package com.bjora.Bjora.DTO.questionDTO;

import com.bjora.Bjora.DTO.topicDTO.GetTopicDTO;
import com.bjora.Bjora.DTO.userDTO.GetUserDTO;
import com.bjora.Bjora.entities.Topic;
import com.bjora.Bjora.entities.User;
import com.bjora.Bjora.enumerated.Status;
import lombok.*;

import java.util.Date;

@Data
@RequiredArgsConstructor
@ToString
public class GetQuestionDTO {
    @NonNull
    private Date created_at;

    @NonNull
    private Status status;

    @NonNull
    private String title;

    @NonNull
    private GetUserDTO member;

    @NonNull
    private GetTopicDTO topic;
}
