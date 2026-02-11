package com.fsse2510.lab_project_backend.mapper;

import com.fsse2510.lab_project_backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2510.lab_project_backend.data.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {
    public UserEntity toUserEntity(FirebaseUserData firebaseUserData){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(firebaseUserData.getEmail());
        userEntity.setFirebaseUid(firebaseUserData.getFirebaseUid());
        return userEntity;
    }

}
