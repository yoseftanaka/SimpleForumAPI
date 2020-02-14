package com.bjora.Bjora.service.implementation;

import com.bjora.Bjora.DTO.questionDTO.GetQuestionDTO;
import com.bjora.Bjora.DTO.questionDTO.PostQuestionDTO;
import com.bjora.Bjora.DTO.topicDTO.GetTopicDTO;
import com.bjora.Bjora.DTO.userDTO.GetUserDTO;
import com.bjora.Bjora.entities.Question;
import com.bjora.Bjora.entities.Topic;
import com.bjora.Bjora.entities.User;
import com.bjora.Bjora.enumerated.Status;
import com.bjora.Bjora.repositories.QuestionRepository;
import com.bjora.Bjora.repositories.TopicRepository;
import com.bjora.Bjora.repositories.UserRepository;
import com.bjora.Bjora.service.QuestionService;
import com.bjora.Bjora.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public List<GetQuestionDTO> getAllQuestion() {
        List<GetQuestionDTO> questionList = new ArrayList<>();
        questionRepository.findAll().forEach(question -> {
            User member = question.getMember();
            questionList.add(new GetQuestionDTO(question.getCreated_at(), question.getStatus(), question.getTitle(),
                    new GetUserDTO(member.getAddress(), member.getBirthday(), member.getEmail(), member.getName(), member.getProfilePicture()),
                    new GetTopicDTO(question.getTopic().getTopicName())));
        });
        return questionList;
    }

    @Override
    public GetQuestionDTO getSingleQuestion(int questionId) {
        Question question = getOneQuestion(questionId);
        if(question!=null){
            return new GetQuestionDTO(question.getCreated_at(), question.getStatus(),question.getTitle(),
                    new GetUserDTO(question.getMember().getAddress(),question.getMember().getBirthday(),question.getMember().getEmail(),question.getMember().getName(),question.getMember().getProfilePicture()),
                    new GetTopicDTO(question.getTopic().getTopicName()));
        }
        else return null;
    }

    @Override
    public List<GetQuestionDTO> getAllQuestionByUser(int userId) {
        List<GetQuestionDTO> questionList = new ArrayList<>();
        User user = userRepository.findById(userId).get();
        if(user==null) return null;
        questionRepository.findAll().forEach(question->{
            if(question.getMember().getId() == userId){
                questionList.add(new GetQuestionDTO(question.getCreated_at(), question.getStatus(), question.getTitle(),
                        new GetUserDTO(question.getMember().getAddress(), question.getMember().getBirthday(), question.getMember().getEmail(), question.getMember().getName(), question.getMember().getProfilePicture()),
                        new GetTopicDTO(question.getTopic().getTopicName())));
            }
        });
        return questionList;
    }

    @Override
    public GetQuestionDTO createQuestion(PostQuestionDTO postQuestionDTO) {
        User user = userRepository.findById(postQuestionDTO.getMemberId()).get();
        Topic topic = topicRepository.findById(postQuestionDTO.getTopicId()).get();
        Question question = new Question();
        if(user!=null&& topic!=null){
            question.setMember(user);
            question.setStatus(Status.OPEN);
            question.setTitle(postQuestionDTO.getTitle());
            question.setTopic(topic);
            questionRepository.save(question);
            return new GetQuestionDTO(question.getCreated_at(), question.getStatus(), question.getTitle(),
                    new GetUserDTO(question.getMember().getAddress(), question.getMember().getBirthday(), question.getMember().getEmail(), question.getMember().getName(), question.getMember().getProfilePicture()),
                    new GetTopicDTO(question.getTopic().getTopicName()));
        }
        else return null;
    }

    @Override
    public GetQuestionDTO updateQuestion(int id, PostQuestionDTO postQuestionDTO) {
        User user = userRepository.findById(postQuestionDTO.getMemberId()).get();
        Topic topic = topicRepository.findById(postQuestionDTO.getTopicId()).get();
        Question question = getOneQuestion(id);
        if(user!=null&& topic!=null && question!=null){
            question.setMember(user);
            question.setStatus(Status.OPEN);
            question.setTitle(postQuestionDTO.getTitle());
            question.setTopic(topic);
            questionRepository.save(question);
            return new GetQuestionDTO(question.getCreated_at(), question.getStatus(), question.getTitle(),
                    new GetUserDTO(question.getMember().getAddress(), question.getMember().getBirthday(), question.getMember().getEmail(), question.getMember().getName(), question.getMember().getProfilePicture()),
                    new GetTopicDTO(question.getTopic().getTopicName()));
        }
        else return null;
    }

    @Override
    public GetQuestionDTO deleteQuestion(int id) {
        Question question = getOneQuestion(id);
        if(question!=null){
            GetQuestionDTO questionDTO = new GetQuestionDTO(question.getCreated_at(), question.getStatus(), question.getTitle(),
                    new GetUserDTO(question.getMember().getAddress(), question.getMember().getBirthday(), question.getMember().getEmail(), question.getMember().getName(), question.getMember().getProfilePicture()),
                    new GetTopicDTO(question.getTopic().getTopicName()));
            questionRepository.deleteById(id);
            return questionDTO;
        }
        else return null;
    }

    @Override
    public GetQuestionDTO changeQuestionStatus(int id) {
        Question question = getOneQuestion(id);
        if(question!=null){
            if (question.getStatus() == Status.OPEN) question.setStatus(Status.CLOSED);
            else question.setStatus(Status.OPEN);

            questionRepository.save(question);

            return new GetQuestionDTO(question.getCreated_at(), question.getStatus(), question.getTitle(),
                    new GetUserDTO(question.getMember().getAddress(), question.getMember().getBirthday(), question.getMember().getEmail(), question.getMember().getName(), question.getMember().getProfilePicture()),
                    new GetTopicDTO(question.getTopic().getTopicName()));
        }
        else return null;
    }

    private Question getOneQuestion(int id){
        if (questionRepository.findById(id).isPresent()){
            Question question = questionRepository.findById(id).get();
            return question;
        }
        else return null;
    }
}
