package com.bjora.Bjora.service;

import com.bjora.Bjora.DTO.messageDTO.GetMessageDTO;
import com.bjora.Bjora.DTO.messageDTO.PostMessageDTO;

import java.util.List;

public interface MessageService {
    public GetMessageDTO createMessage(PostMessageDTO postMessageDTO);
    public GetMessageDTO deleteMessage(int messageId);
    public List<GetMessageDTO> getMessageByUser(int userId);
}
