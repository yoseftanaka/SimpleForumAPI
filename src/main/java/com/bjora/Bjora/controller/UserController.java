package com.bjora.Bjora.controller;

import com.bjora.Bjora.DTO.userDTO.GetUserDTO;
import com.bjora.Bjora.DTO.userDTO.PostUserDTO;
import com.bjora.Bjora.service.UserService;
import com.bjora.Bjora.utility.FileUploadUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping (value = "/{userId}")
    public ResponseEntity<GetUserDTO> getSingleUser (@PathVariable int userId){
        return new ResponseEntity<>(userService.getSingleUser(userId), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<GetUserDTO>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<GetUserDTO> createUser(@RequestParam("postUserDTO") String postUserDTOString,
                                                 @RequestParam(value = "file", required = false)
                                                         MultipartFile multipartFile){
        try {
            PostUserDTO postUserDTO = objectMapper.readValue(postUserDTOString, PostUserDTO.class);
            postUserDTO.setProfilePicture(multipartFile.getOriginalFilename());
            return new ResponseEntity<>(userService.createUser(postUserDTO, multipartFile), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @PutMapping (value = "/{userId}")
    public ResponseEntity<GetUserDTO> updateUser(@PathVariable int userId,
                                                 @RequestParam("postUserDTO") String postUserDTOString,
                                                 @RequestParam(value = "file", required = false)
                                                         MultipartFile multipartFile){

        try {
            PostUserDTO postUserDTO = objectMapper.readValue(postUserDTOString, PostUserDTO.class);
            postUserDTO.setProfilePicture(multipartFile.getOriginalFilename());
            return new ResponseEntity<>(userService.updateUser(userId, postUserDTO, multipartFile), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @DeleteMapping (value = "/{userId}")
    public ResponseEntity<GetUserDTO> deleteUser(@PathVariable int userId){
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
    }
}
