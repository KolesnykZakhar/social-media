package com.gmail.kolesnyk.zakhar.post;


import com.gmail.kolesnyk.zakhar.user.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

@Entity
@Table(name = "posts")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    private Integer idPost;

    @NotEmpty
    @Column(name = "text_post")
    private String textPost;

    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;

    @CreationTimestamp
    @Column(name = "date_post")
    private Timestamp datePost;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "media_posts", joinColumns = @JoinColumn(name = "id_post"))
    @MapKeyColumn(name = "media_files")
    @Column(name = "media_type")
    @Enumerated(EnumType.ORDINAL)
    private Map<String, MEDIA_TYPE> mediaFiles;

    private transient boolean inBookmarks;

    public Post() {
    }

    public boolean isInBookmarks() {
        return inBookmarks;
    }

    public void setInBookmarks(boolean inBookmarks) {
        this.inBookmarks = inBookmarks;
    }

    public Map<String, MEDIA_TYPE> getMediaFiles() {
        return mediaFiles;
    }

    public void setMediaFiles(Map<String, MEDIA_TYPE> mediaFileNames) {
        this.mediaFiles = mediaFileNames;
    }

    public Timestamp getDatePost() {
        return datePost;
    }

    public void setDatePost(Timestamp datePost) {
        this.datePost = datePost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public String getTextPost() {
        return textPost;
    }

    public void setTextPost(String comment) {
        this.textPost = comment;
    }
}
