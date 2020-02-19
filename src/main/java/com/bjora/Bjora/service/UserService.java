package com.bjora.Bjora.service;

import com.bjora.Bjora.DTO.userDTO.GetUserDTO;
import com.bjora.Bjora.DTO.userDTO.PostUserDTO;

import java.util.List;

public interface UserService {
    public GetUserDTO getSingleUser(int id);
    public List<GetUserDTO> getAllUser();
    public GetUserDTO createUser(PostUserDTO postUserDTO);
    public GetUserDTO updateUser(int userId, PostUserDTO postUserDTO);
    public GetUserDTO deleteUser(int userId);
}
