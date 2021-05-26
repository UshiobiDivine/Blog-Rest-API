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
@Table(name = "users")
public class User extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Post> posts;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    List<Favourite> favourites;

    @OneToMany(mappedBy = "connectionFrom")
    List<Connections> following;

    @OneToMany(mappedBy = "connectedTo")
    List<Connections> followers;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
