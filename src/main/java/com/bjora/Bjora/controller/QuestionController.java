package com.bjora.Bjora.controller;

import com.bjora.Bjora.DTO.questionDTO.GetQuestionDTO;
import com.bjora.Bjora.DTO.questionDTO.PostQuestionDTO;
import com.bjora.Bjora.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<GetQuestionDTO>> getAllQuestion(){
        return new ResponseEntity<>(questionService.getAllQuestion(),HttpStatus.OK);
    }

    @GetMapping(value = "/{questionId}")
    public ResponseEntity<GetQuestionDTO> getSingleQuestion(@PathVariable int questionId){
        return new ResponseEntity<>(questionService.getSingleQuestion(questionId), HttpStatus.FOUND);
    }

    @GetMapping(value = "/{userId}/user")
    public ResponseEntity<List<GetQuestionDTO>> getAllQuestionByUser(@PathVariable int userId){
        return new ResponseEntity<>(questionService.getAllQuestionByUser(userId), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<GetQuestionDTO> createQuestion(@RequestBody PostQuestionDTO postQuestionDTO){
        return new ResponseEntity<>(questionService.createQuestion(postQuestionDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{questionId}")
    public ResponseEntity<GetQuestionDTO> updateQuestion(@PathVariable int questionId, @RequestBody PostQuestionDTO postQuestionDTO){
        return new ResponseEntity<>(questionService.updateQuestion(questionId, postQuestionDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{questionId}")
    public ResponseEntity<GetQuestionDTO> createQuestion(@PathVariable int questionId){
        return new ResponseEntity<>(questionService.deleteQuestion(questionId), HttpStatus.OK);
    }

    @GetMapping(value = "/{questionId}/change-status")
    public ResponseEntity<GetQuestionDTO> changeQuestionStatus(@PathVariable int questionId){
        return new ResponseEntity<>(questionService.changeQuestionStatus(questionId), HttpStatus.OK);
    }
}
