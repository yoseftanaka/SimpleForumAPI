package com.bjora.Bjora.service.implementation;

import com.bjora.Bjora.DTO.userDTO.GetUserDTO;
import com.bjora.Bjora.DTO.userDTO.PostUserDTO;
import com.bjora.Bjora.entities.User;
import com.bjora.Bjora.repositories.UserRepository;
import com.bjora.Bjora.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public GetUserDTO getSingleUser(int id) {
        User user = getOneUser(id);
        if(user!=null) return new GetUserDTO(user.getAddress(), user.getBirthday(), user.getEmail(), user.getName(), user.getProfilePicture());
        else return null;
    }

    @Override
    public List<GetUserDTO> getAllUser() {
        List<GetUserDTO> userList = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            userList.add(new GetUserDTO(user.getAddress(), user.getBirthday(), user.getEmail(), user.getName(), user.getProfilePicture()));
        });
        return userList;
    }

    @Override
    public GetUserDTO createUser(PostUserDTO postUserDTO) {
        User user = new User();
        user.setAddress(postUserDTO.getAddress());
        user.setBirthday(postUserDTO.getBirthday());
        user.setEmail(postUserDTO.getEmail());
        user.setGender(postUserDTO.getGender());
        user.setName(postUserDTO.getName());
        user.setPassword(
                bCryptPasswordEncoder.encode(
                        postUserDTO.getPassword()
                )
        );
        user.setProfilePicture(postUserDTO.getProfilePicture());
        user.setRole(postUserDTO.getRole());
        userRepository.save(user);

        return new GetUserDTO(user.getAddress(), user.getBirthday(), user.getEmail(), user.getName(), user.getProfilePicture());
    }

    @Override
    public GetUserDTO updateUser(int userId, PostUserDTO postUserDTO) {
        User user = getOneUser(userId);
        if (user !=null ){
            user.setAddress(postUserDTO.getAddress());
            user.setBirthday(postUserDTO.getBirthday());
            user.setEmail(postUserDTO.getEmail());
            user.setGender(postUserDTO.getGender());
            user.setName(postUserDTO.getName());
            user.setPassword(postUserDTO.getPassword());
            user.setProfilePicture(postUserDTO.getProfilePicture());
            user.setRole(postUserDTO.getRole());
            userRepository.save(user);

            return new GetUserDTO(user.getAddress(), user.getBirthday(), user.getEmail(), user.getName(), user.getProfilePicture());
        }
        else return null;
    }

    @Override
    public GetUserDTO deleteUser(int userId) {
        User user = getOneUser(userId);
        if(user != null) {
            GetUserDTO getUserDTO = new GetUserDTO(user.getAddress(), user.getBirthday(), user.getEmail(), user.getName(), user.getProfilePicture());
            userRepository.deleteById(userId);

            return getUserDTO;
        }
        else return null;
    }

    private User getOneUser(int id){
        if(userRepository.findById(id).isPresent()){
            User user = userRepository.findById(id).get();
            return user;
        }
        else return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
