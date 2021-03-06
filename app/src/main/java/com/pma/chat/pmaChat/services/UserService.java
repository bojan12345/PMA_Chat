package com.pma.chat.pmaChat.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.pma.chat.pmaChat.model.User;

public interface UserService {

    void setFcmToken(final String token);

    void getUser(final String uid, final UserCallback callback);

}
