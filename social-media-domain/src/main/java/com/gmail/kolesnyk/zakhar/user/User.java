package com.gmail.kolesnyk.zakhar.user;

import com.gmail.kolesnyk.zakhar.post.Post;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;

    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private Timestamp birthDate;

    @NotEmpty
    @Column(name = "login")
    private String login;

    @NotEmpty
    @Column(name = "pass")
    private String pass;

    @NotEmpty
    @Email
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Column(name = "phone")
    private String pjone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Post> postList;


    //    @ManyToMany(cascade = {CascadeType.ALL})
//    @JoinTable(name = "friends",
//            joinColumns = {@JoinColumn(name = "id_user")},
//            inverseJoinColumns = {@JoinColumn(name = "id_friend")})

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "friends",
            joinColumns = @JoinColumn(name = "id_user"))
    @MapKeyJoinColumn(name = "id_friend")
    @Column(name = "date_friendship")
    private Map<User, Timestamp> friends = new HashMap<>();

//    @ManyToMany(mappedBy = "friendsList")
//    private Map<User, Timestamp> backFriendsList = new HashMap<>();

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPjone() {
        return pjone;
    }

    public void setPjone(String pjone) {
        this.pjone = pjone;
    }

    public Map<User, Timestamp> getFriends() {
        return friends;
    }

    public void setFriends(Map<User, Timestamp> friendsList) {
        this.friends = friendsList;
    }

    //    public Set<User> getFriendsList() {
//        return friendsList;
//    }
//
//    public void setFriendsList(Set<User> colleagues) {
//        this.friendsList = colleagues;
//    }
//
//    public Set<User> getBackFriendsList() {
//        return backFriendsList;
//    }
//
//    public void setBackFriendsList(Set<User> teammates) {
//        this.backFriendsList = teammates;
//    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

//    public List<Post> getPostList() {
//        return postList;
//    }
//
//    public void setPostList(List<Post> postList) {
//        this.postList = postList;
//    }
}
