package com.bjora.Bjora.DTO.topicDTO;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
public class GetTopicDTO {
    @NonNull
    private String topicName;
}
