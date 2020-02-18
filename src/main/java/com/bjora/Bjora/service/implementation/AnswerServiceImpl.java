package com.bjora.Bjora.service.implementation;

import com.bjora.Bjora.DTO.answerDTO.GetAnswerDTO;
import com.bjora.Bjora.DTO.answerDTO.PostAnswerDTO;
import com.bjora.Bjora.DTO.questionDTO.GetQuestionDTO;
import com.bjora.Bjora.DTO.topicDTO.GetTopicDTO;
import com.bjora.Bjora.DTO.userDTO.GetUserDTO;
import com.bjora.Bjora.entities.Answer;
import com.bjora.Bjora.entities.Question;
import com.bjora.Bjora.entities.User;
import com.bjora.Bjora.repositories.AnswerRepository;
import com.bjora.Bjora.repositories.QuestionRepository;
import com.bjora.Bjora.repositories.UserRepository;
import com.bjora.Bjora.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public GetAnswerDTO createAnswer(PostAnswerDTO postAnswerDTO) {
        User user = userRepository.findById(postAnswerDTO.getUserId()).get();
        Question question = questionRepository.findById(postAnswerDTO.getQuestionId()).get();
        if(user !=null && question!=null){
            Answer answer = new Answer();
            answer.setAnswer(postAnswerDTO.getAnswer());
            answer.setQuestion(question);
            answer.setUser(user);
            answerRepository.save(answer);

            question = answer.getQuestion();
            return new GetAnswerDTO(answer.getAnswer(),
                    new GetQuestionDTO(question.getCreated_at(), question.getStatus(), question.getTitle(),
                            new GetUserDTO(question.getMember().getAddress(),question.getMember().getBirthday(),question.getMember().getEmail(),question.getMember().getName(),question.getMember().getProfilePicture()),
                            new GetTopicDTO(question.getTopic().getTopicName())),
                    answer.getCreated_at());
        }

        else return null;
    }

    @Override
    public GetAnswerDTO updateAnswer(int answerId, PostAnswerDTO postAnswerDTO) {
        User user = userRepository.findById(postAnswerDTO.getUserId()).get();
        Question question = questionRepository.findById(postAnswerDTO.getQuestionId()).get();
        Answer answer = answerRepository.findById(answerId).get();

        if(user !=null && question!=null && answer!=null){
            answer.setAnswer(postAnswerDTO.getAnswer());
            answer.setQuestion(question);
            answer.setUser(user);
            answerRepository.save(answer);

            question = answer.getQuestion();
            return new GetAnswerDTO(answer.getAnswer(),
                    new GetQuestionDTO(question.getCreated_at(), question.getStatus(), question.getTitle(),
                            new GetUserDTO(question.getMember().getAddress(),question.getMember().getBirthday(),question.getMember().getEmail(),question.getMember().getName(),question.getMember().getProfilePicture()),
                            new GetTopicDTO(question.getTopic().getTopicName())),
                    answer.getCreated_at());
        }
        else return null;
    }

    @Override
    public GetAnswerDTO deleteAnswer(int answerId) {
        Answer answer = answerRepository.findById(answerId).get();
        if(answer!=null){
            Question question = answer.getQuestion();
            GetAnswerDTO getAnswerDTO = new GetAnswerDTO(answer.getAnswer(),
                    new GetQuestionDTO(question.getCreated_at(), question.getStatus(), question.getTitle(),
                            new GetUserDTO(question.getMember().getAddress(),question.getMember().getBirthday(),question.getMember().getEmail(),question.getMember().getName(),question.getMember().getProfilePicture()),
                            new GetTopicDTO(question.getTopic().getTopicName())),
                    answer.getCreated_at());
            answerRepository.deleteById(answerId);

            return getAnswerDTO;
        }
        else return null;
    }

    @Override
    public List<GetAnswerDTO> getAllByQuestion(int questionId) {
        List<GetAnswerDTO> answerList = new ArrayList<>();
        Question question = questionRepository.findById(questionId).get();
        if(question==null) return null;
        answerRepository.findAll().forEach(answer -> {
            if(answer.getQuestion().getId() == questionId){
                answerList.add(new GetAnswerDTO(answer.getAnswer(),
                        new GetQuestionDTO(question.getCreated_at(), question.getStatus(), question.getTitle(),
                                new GetUserDTO(question.getMember().getAddress(),question.getMember().getBirthday(),question.getMember().getEmail(),question.getMember().getName(),question.getMember().getProfilePicture()),
                                new GetTopicDTO(question.getTopic().getTopicName())),
                        answer.getCreated_at()));
            }
        });
        return answerList;

    }
}
