package com.bjora.Bjora.service;

import com.bjora.Bjora.DTO.questionDTO.GetQuestionDTO;
import com.bjora.Bjora.DTO.questionDTO.PostQuestionDTO;

import java.util.List;

public interface QuestionService {
    public List<GetQuestionDTO> getAllQuestion();
    public GetQuestionDTO getSingleQuestion(int questionId);
    public List<GetQuestionDTO> getAllQuestionByUser(int userId);
    public GetQuestionDTO createQuestion(PostQuestionDTO postQuestionDTO);
    public GetQuestionDTO updateQuestion(int id, PostQuestionDTO postQuestionDTO);
    public GetQuestionDTO deleteQuestion(int id);
    public GetQuestionDTO changeQuestionStatus(int id);
}
