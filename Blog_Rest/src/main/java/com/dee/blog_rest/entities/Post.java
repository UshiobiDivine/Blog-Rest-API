package com.dee.blog_rest.entities;

import com.dee.blog_rest.entities.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Post extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String caption;
    @Column
    private String body;
    @Column
    private int total_likes;
    @Column
    private String createdBy;

    @OneToMany(mappedBy = "post", cascade = ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = ALL)
    private List<PostLike> likes;

    @OneToMany(mappedBy = "post")
    List<Favourite> favourites;



    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
