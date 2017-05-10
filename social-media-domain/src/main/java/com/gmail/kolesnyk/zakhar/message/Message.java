package com.gmail.kolesnyk.zakhar.message;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "messages")
public class Message implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_message")
    private Integer idMessage;

    @Column(name = "id_sender")
    private Integer idUser;

    @Column(name = "id_receiver")
    private Integer idFriend;

    @Column(name = "text_message")
    private String textMessage;

    @Column(name = "unread")
    private Boolean unread;

    public Boolean getUnread() {
        return unread;
    }

    public void setUnread(Boolean readed) {
        this.unread = readed;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdFriend() {
        return idFriend;
    }

    public void setIdFriend(Integer idFriend) {
        this.idFriend = idFriend;
    }

    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
}
