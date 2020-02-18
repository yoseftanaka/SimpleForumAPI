package com.bjora.Bjora.controller;

import com.bjora.Bjora.DTO.messageDTO.GetMessageDTO;
import com.bjora.Bjora.DTO.messageDTO.PostMessageDTO;
import com.bjora.Bjora.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<GetMessageDTO> createMessage(@RequestBody PostMessageDTO postMessageDTO){
        return new ResponseEntity<>(messageService.createMessage(postMessageDTO), HttpStatus.CREATED);
    }

    @DeleteMapping (value = "/{messageId}")
    public ResponseEntity<GetMessageDTO> deleteMessage (@PathVariable int messageId){
        return new ResponseEntity<>(messageService.deleteMessage(messageId), HttpStatus.OK);
    }

    @GetMapping (value = "/users/{userId}")
    public ResponseEntity<List<GetMessageDTO>> getAllMessageByUser (@PathVariable int userId){
        return new ResponseEntity<>(messageService.getMessageByUser(userId), HttpStatus.FOUND);
    }
}
