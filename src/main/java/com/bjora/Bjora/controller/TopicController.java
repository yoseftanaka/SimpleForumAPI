package com.bjora.Bjora.controller;

import com.bjora.Bjora.DTO.topicDTO.GetTopicDTO;
import com.bjora.Bjora.DTO.topicDTO.PostTopicDTO;
import com.bjora.Bjora.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping
    public ResponseEntity<List<GetTopicDTO>> getAllTopic(){
        return new ResponseEntity<>(topicService.getAllTopic(), HttpStatus.FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GetTopicDTO> getSingleTopic(@PathVariable int id){
        return new ResponseEntity<>(topicService.getSingleTopic(id), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<GetTopicDTO> createTopic(@RequestBody PostTopicDTO postTopicDTO){
        return new ResponseEntity<>(topicService.createTopic(postTopicDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GetTopicDTO> updateTopic(@PathVariable int id, @RequestBody PostTopicDTO postTopicDTO){
        return new ResponseEntity<>(topicService.updateTopic(id, postTopicDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<GetTopicDTO> deleteTopic(@PathVariable int id){
        return new ResponseEntity<>(topicService.deleteTopic(id), HttpStatus.OK);
    }
}
