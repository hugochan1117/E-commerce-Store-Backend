package com.fsse2510.lab_project_backend.mapper;

import com.fsse2510.lab_project_backend.data.user.domainObject.request.FirebaseUserData;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {
    public FirebaseUserData toFirebaseUserData(Jwt jwt){
        FirebaseUserData firebaseUserData = new FirebaseUserData();
        firebaseUserData.setFirebaseUid(jwt.getClaimAsString("user_id"));
        firebaseUserData.setEmail(jwt.getClaimAsString("email"));

        return firebaseUserData;
    }
}
