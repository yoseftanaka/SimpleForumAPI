package com.bjora.Bjora.controller;

import com.bjora.Bjora.DTO.userDTO.GetUserDTO;
import com.bjora.Bjora.DTO.userDTO.PostUserDTO;
import com.bjora.Bjora.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping (value = "/{userId}")
    public ResponseEntity<GetUserDTO> getSingleUser (@PathVariable int userId){
        return new ResponseEntity<>(userService.getSingleUser(userId), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<GetUserDTO>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<GetUserDTO> createUser(@Valid @RequestBody PostUserDTO postUserDTO){
        return new ResponseEntity<>(userService.createUser(postUserDTO), HttpStatus.CREATED);
    }

    @PutMapping (value = "/{userId}")
    public ResponseEntity<GetUserDTO> updateUser(@PathVariable int userId, @Valid @RequestBody PostUserDTO postUserDTO){
        return new ResponseEntity<>(userService.updateUser(userId, postUserDTO), HttpStatus.CREATED);
    }

    @DeleteMapping (value = "/{userId}")
    public ResponseEntity<GetUserDTO> deleteUser(@PathVariable int userId){
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
    }
}
