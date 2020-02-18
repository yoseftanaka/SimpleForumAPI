package com.bjora.Bjora.controller;

import com.bjora.Bjora.DTO.answerDTO.GetAnswerDTO;
import com.bjora.Bjora.DTO.answerDTO.PostAnswerDTO;
import com.bjora.Bjora.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping
    public ResponseEntity<GetAnswerDTO> createAnswer(@RequestBody PostAnswerDTO postAnswerDTO){
        return new ResponseEntity<>(answerService.createAnswer(postAnswerDTO), HttpStatus.CREATED);
    }

    @PutMapping (value = "/{answerId}")
    public ResponseEntity<GetAnswerDTO> updateAnswer(@PathVariable int answerId, @RequestBody PostAnswerDTO postAnswerDTO){
        return new ResponseEntity<>(answerService.updateAnswer(answerId, postAnswerDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{answerId}")
    public ResponseEntity<GetAnswerDTO> deleteAnswer(@PathVariable int answerId){
        return new ResponseEntity<>(answerService.deleteAnswer(answerId), HttpStatus.OK);
    }

    @GetMapping(value="/{questionId}")
    public ResponseEntity<List<GetAnswerDTO>> getAllAnswerByQuestion(@PathVariable int questionId){
        return new ResponseEntity<>(answerService.getAllByQuestion(questionId), HttpStatus.FOUND);
    }

}
