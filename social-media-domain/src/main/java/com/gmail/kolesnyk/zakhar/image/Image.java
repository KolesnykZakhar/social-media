package com.gmail.kolesnyk.zakhar.image;

import com.gmail.kolesnyk.zakhar.user.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "images")
public class Image implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
    private Integer idImage;

    @Column(name = "name_image")
    private String nameImage;

    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;

    public Integer getIdImage() {
        return idImage;
    }

    public void setIdImage(Integer idImage) {
        this.idImage = idImage;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
