package com.fsse2510.lab_project_backend.service;

import com.fsse2510.lab_project_backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2510.lab_project_backend.data.user.entity.UserEntity;


public interface UserService {
    UserEntity getUserEntityByEmail(FirebaseUserData firebaseUserData);
}
