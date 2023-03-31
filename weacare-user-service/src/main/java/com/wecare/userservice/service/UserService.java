package com.wecare.userservice.service;

import com.wecare.userservice.dto.LoginDTO;
import com.wecare.userservice.dto.UserDTO;
import com.wecare.userservice.entity.UserEntity;
import com.wecare.userservice.exception.ExceptionConstants;
import com.wecare.userservice.exception.WecareException;
import com.wecare.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Environment environment;

    public UserDTO getUserProfile(String userId) throws WecareException {
        Optional<UserEntity> userEntity = this.userRepository.findById(userId);

        if(userEntity.isEmpty()) {
            throw new WecareException(ExceptionConstants.USER_NOT_FOUND.toString());
        }

        return UserEntity.prepareUserDTO(userEntity.get());
    }

    public String createUser(UserDTO userDTO) {
        UserEntity user = this.userRepository.saveAndFlush(UserDTO.prepareUserEntity(userDTO));
        return user.getUserId();
    }

    public boolean loginUser(LoginDTO loginDTO) {
        Optional<UserEntity> user = this.userRepository.findById(loginDTO.getId());

        if(user.isEmpty()) {
            return false;
        }

        return (loginDTO.getPassword().equals(user.get().getPassword()));
    }

    public boolean checkUserExistence(String userId) {
        Optional<UserEntity> user = this.userRepository.findById(userId);
        return !user.isEmpty();
    }

}
