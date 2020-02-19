package com.bjora.Bjora.service.implementation;

import com.bjora.Bjora.DTO.messageDTO.GetMessageDTO;
import com.bjora.Bjora.DTO.messageDTO.PostMessageDTO;
import com.bjora.Bjora.DTO.userDTO.GetUserDTO;
import com.bjora.Bjora.entities.Message;
import com.bjora.Bjora.entities.User;
import com.bjora.Bjora.repositories.MessageRepository;
import com.bjora.Bjora.repositories.UserRepository;
import com.bjora.Bjora.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public GetMessageDTO createMessage(PostMessageDTO postMessageDTO) {
        User sender = userRepository.findById(postMessageDTO.getSenderId()).get();
        User receiver = userRepository.findById(postMessageDTO.getReceiverId()).get();
        if(sender!=null && receiver !=null){
            Message message = new Message();
            message.setMessage(postMessageDTO.getMessage());
            message.setReceiver(receiver);
            message.setSender(sender);
            messageRepository.save(message);

            return new GetMessageDTO(message.getCreated_at(), message.getMessage(),
                    new GetUserDTO(receiver.getAddress(), receiver.getBirthday(), receiver.getEmail(), receiver.getName(), receiver.getProfilePicture()) ,
                    new GetUserDTO(sender.getAddress(), sender.getBirthday(), sender.getEmail(), sender.getName(), sender.getProfilePicture()));
        }
        else return null;
    }

    @Override
    public GetMessageDTO deleteMessage(int messageId) {
        Message message = messageRepository.findById(messageId).get();
        if(message!=null){
            User receiver = message.getReceiver();
            User sender = message.getSender();
            GetMessageDTO getMessageDTO = new GetMessageDTO(message.getCreated_at(), message.getMessage(),
                    new GetUserDTO(receiver.getAddress(), receiver.getBirthday(), receiver.getEmail(), receiver.getName(), receiver.getProfilePicture()) ,
                    new GetUserDTO(sender.getAddress(), sender.getBirthday(), sender.getEmail(), sender.getName(), sender.getProfilePicture()));
            messageRepository.deleteById(messageId);

            return getMessageDTO;
        }
        else return null;
    }

    @Override
    public List<GetMessageDTO> getMessageByUser(int userId) {
        List<GetMessageDTO> messagesList = new ArrayList<>();
        User receiver = userRepository.findById(userId).get();
        if(receiver==null) return null;
        messageRepository.findAll().forEach(message -> {
            if (message.getReceiver().getId() == userId){
                messagesList.add(new GetMessageDTO(message.getCreated_at(), message.getMessage(),
                        new GetUserDTO(message.getReceiver().getAddress(), message.getReceiver().getBirthday(), message.getReceiver().getEmail(), message.getReceiver().getName(), message.getReceiver().getProfilePicture()) ,
                        new GetUserDTO(message.getSender().getAddress(), message.getSender().getBirthday(), message.getSender().getEmail(), message.getSender().getName(), message.getSender().getProfilePicture())));
            }
        });
        return messagesList;
    }
}
