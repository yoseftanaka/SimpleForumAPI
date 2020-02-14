package com.bjora.Bjora.service.implementation;

import com.bjora.Bjora.DTO.topicDTO.GetTopicDTO;
import com.bjora.Bjora.DTO.topicDTO.PostTopicDTO;
import com.bjora.Bjora.entities.Topic;
import com.bjora.Bjora.repositories.TopicRepository;
import com.bjora.Bjora.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public List<GetTopicDTO> getAllTopic() {
        List<GetTopicDTO> topicList =new ArrayList<>();
        topicRepository.findAll().forEach(topic->{
            topicList.add(new GetTopicDTO(topic.getTopicName()));
        });
        return topicList;
    }

    @Override
    public GetTopicDTO getSingleTopic(int id) {
        if (getOneTopic(id)!=null) {
            Topic topic = getOneTopic(id);
            return new GetTopicDTO(topic.getTopicName());
        }
        else return null;
    }

    @Override
    public GetTopicDTO createTopic(PostTopicDTO postTopicDTO) {
        Topic topic = new Topic();
        topic.setTopicName(postTopicDTO.getTopicName());
        topicRepository.save(topic);
        return new GetTopicDTO(topic.getTopicName());
    }

    @Override
    public GetTopicDTO updateTopic(int id, PostTopicDTO postTopicDTO) {
        Topic topic = getOneTopic(id);
        if(topic!=null){
            topic.setTopicName(postTopicDTO.getTopicName());
            topicRepository.save(topic);
            return new GetTopicDTO(topic.getTopicName());
        }
        else return null;
    }

    @Override
    public GetTopicDTO deleteTopic(int id) {
        if(getOneTopic(id)!=null) {
            GetTopicDTO topicDTO = new GetTopicDTO(getOneTopic(id).getTopicName());
            topicRepository.deleteById(id);
            return topicDTO;
        }
        else return null;
    }

    private Topic getOneTopic(int id){
        if(topicRepository.findById(id).isPresent()){
            Topic topic =topicRepository.findById(id).get();
            return topic;
        }
        else return null;
    }
}
