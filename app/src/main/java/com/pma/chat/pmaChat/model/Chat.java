package com.pma.chat.pmaChat.model;


import java.io.Serializable;

public class Chat implements Serializable {

    private Long id;

    private Long chatContactId;

    private String firebaseId;

    public Chat() {
    }

    public Chat(Long id, Long chatContactId, String firebaseId) {
        this.id = id;
        this.chatContactId = chatContactId;
        this.firebaseId = firebaseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatContactId() {
        return chatContactId;
    }

    public void setChatContactId(Long chatContactId) {
        this.chatContactId = chatContactId;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }
}
