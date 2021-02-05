package com.bjora.Bjora.service.implementation;

import com.bjora.Bjora.DTO.userDTO.GetUserDTO;
import com.bjora.Bjora.DTO.userDTO.PostUserDTO;
import com.bjora.Bjora.entities.User;
import com.bjora.Bjora.repositories.UserRepository;
import com.bjora.Bjora.service.UserService;
import com.bjora.Bjora.utility.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public GetUserDTO getSingleUser(int id) {
        User user = getOneUser(id);
        if(user!=null) {
            String profilePicture = null;

            try {
                profilePicture = Base64.encodeBase64String(IOUtils.toByteArray(
                        new FileInputStream("./user-photos/" + user.getId() + "/" + user.getProfilePicture())));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            return new GetUserDTO(user.getAddress(), user.getBirthday(), user.getEmail(), user.getName(), profilePicture);
        }
        else return null;
    }

    @Override
    public List<GetUserDTO> getAllUser() {
        List<GetUserDTO> userList = new ArrayList<>();
        userRepository.findAll().forEach(user -> {

            String profilePicture = null;

            try {
                profilePicture = Base64.encodeBase64String(IOUtils.toByteArray(
                        new FileInputStream("./user-photos/" + user.getId() + "/" + user.getProfilePicture())));
            } catch (Exception e) {
                log.error(e.getMessage());
            }

            userList.add(new GetUserDTO(user.getAddress(), user.getBirthday(), user.getEmail(), user.getName(), profilePicture));
        });
        return userList;
    }

    @Override
    public GetUserDTO createUser(PostUserDTO postUserDTO, MultipartFile multipartFile) {
        User user = new User();
        user.setAddress(postUserDTO.getAddress());
        user.setBirthday(postUserDTO.getBirthday());
        user.setEmail(postUserDTO.getEmail());
        user.setGender(postUserDTO.getGender());
        user.setName(postUserDTO.getName());
        user.setPassword(postUserDTO.getPassword());
        user.setProfilePicture(postUserDTO.getProfilePicture());
        user.setRole(postUserDTO.getRole());
        userRepository.save(user);

        String profilePicture = null;

        try {
            FileUploadUtil.saveFile("user-photos/" + user.getId(), user.getProfilePicture(), multipartFile);
            profilePicture = Base64.encodeBase64String(IOUtils.toByteArray(
                    new FileInputStream("./user-photos/" + user.getId() + "/" + user.getProfilePicture())));
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return new GetUserDTO(user.getAddress(), user.getBirthday(), user.getEmail(), user.getName(), profilePicture);
    }

    @Override
    public GetUserDTO updateUser(int userId, PostUserDTO postUserDTO, MultipartFile multipartFile) {
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

            String profilePicture = null;

            try {
                FileUploadUtil.saveFile("user-photos/" + user.getId(), user.getProfilePicture(), multipartFile);
                profilePicture = Base64.encodeBase64String(IOUtils.toByteArray(
                        new FileInputStream("./user-photos/" + user.getId() + "/" + user.getProfilePicture())));
            } catch (Exception e) {
                log.error(e.getMessage());
            }

            return new GetUserDTO(user.getAddress(), user.getBirthday(), user.getEmail(), user.getName(), profilePicture);
        }
        else return null;
    }

    @Override
    public GetUserDTO deleteUser(int userId) {
        User user = getOneUser(userId);
        if(user != null) {
            GetUserDTO getUserDTO = new GetUserDTO(user.getAddress(), user.getBirthday(), user.getEmail(), user.getName(), user.getProfilePicture());
            userRepository.deleteById(userId);

            try {
                FileUtils.forceDelete(new File("./user-photos/" + user.getId()));
            } catch (Exception e) {
                log.error(e.getMessage());
            }

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
}
