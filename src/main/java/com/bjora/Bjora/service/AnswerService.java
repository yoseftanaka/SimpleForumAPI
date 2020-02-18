package com.bjora.Bjora.service;

import com.bjora.Bjora.DTO.answerDTO.GetAnswerDTO;
import com.bjora.Bjora.DTO.answerDTO.PostAnswerDTO;
import com.bjora.Bjora.DTO.userDTO.GetUserDTO;

import java.util.List;

public interface AnswerService {
    public GetAnswerDTO createAnswer(PostAnswerDTO postAnswerDTO);
    public GetAnswerDTO updateAnswer(int answerId, PostAnswerDTO postAnswerDTO);
    public GetAnswerDTO deleteAnswer(int answerId);
    public List<GetAnswerDTO> getAllByQuestion(int questionId);
}
