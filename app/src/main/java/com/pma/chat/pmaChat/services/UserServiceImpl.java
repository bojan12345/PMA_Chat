package com.pma.chat.pmaChat.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pma.chat.pmaChat.model.User;
import com.pma.chat.pmaChat.sync.MyFirebaseService;
import com.pma.chat.pmaChat.utils.RemoteConfig;

public class UserServiceImpl implements UserService {
    @Override
    public void setFcmToken(String token) {
        {
            FirebaseDatabase.getInstance()
                    .getReference()
                    .child(RemoteConfig.USER)
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child(User.USER_FCM_TOKEN_FIELD)
                    .setValue(token);
        }
    }

    @Override
    public void getUser(final String uid, final UserCallback callback) {
        MyFirebaseService.getUsersDatabaseReference().child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final User user = dataSnapshot.getValue(User.class);
                callback.notify(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
