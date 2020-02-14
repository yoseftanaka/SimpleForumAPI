package com.bjora.Bjora.service;

import com.bjora.Bjora.DTO.topicDTO.PostTopicDTO;
import com.bjora.Bjora.DTO.topicDTO.GetTopicDTO;
import com.bjora.Bjora.entities.Topic;

import java.util.List;

public interface TopicService {
    public List<GetTopicDTO> getAllTopic();
    public GetTopicDTO getSingleTopic(int id);
    public GetTopicDTO createTopic(PostTopicDTO postTopicDTO);
    public GetTopicDTO updateTopic(int id, PostTopicDTO postTopicDTO);
    public GetTopicDTO deleteTopic(int id);
}
