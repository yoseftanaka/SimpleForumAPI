package com.bjora.Bjora.service.implementation;

import com.bjora.Bjora.DTO.userDTO.GetUserDTO;
import com.bjora.Bjora.entities.User;
import com.bjora.Bjora.repositories.UserRepository;
import com.bjora.Bjora.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public GetUserDTO getSingleUser(int id) {
        User user = getOneUser(id);
        if(user!=null) return new GetUserDTO(user.getAddress(), user.getBirthday(), user.getEmail(), user.getName(), user.getProfilePicture());
        else return null;
    }

    private User getOneUser(int id){
        if(userRepository.findById(id).isPresent()){
            User user = userRepository.findById(id).get();
            return user;
        }
        else return null;
    }
}
