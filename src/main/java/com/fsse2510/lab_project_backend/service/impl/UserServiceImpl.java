package com.fsse2510.lab_project_backend.service.impl;

import com.fsse2510.lab_project_backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2510.lab_project_backend.data.user.entity.UserEntity;
import com.fsse2510.lab_project_backend.mapper.UserEntityMapper;
import com.fsse2510.lab_project_backend.repository.UserRepository;
import com.fsse2510.lab_project_backend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserServiceImpl(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    public UserEntity getUserEntityByEmail(FirebaseUserData firebaseUserData){
        Optional<UserEntity> userEntity = userRepository.findByEmail(firebaseUserData.getEmail());
        return userEntity.orElseGet(() -> userRepository.save(userEntityMapper.toUserEntity(firebaseUserData)));
    }
}
