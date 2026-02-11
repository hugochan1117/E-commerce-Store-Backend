package com.fsse2510.lab_project_backend.data.user.domainObject.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FirebaseUserData {
    private String email;
    private String firebaseUid;
}
