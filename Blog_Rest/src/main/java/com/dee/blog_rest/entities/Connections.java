package com.dee.blog_rest.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class Connections {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User connectionFrom;

    @ManyToOne
    @JoinColumn(name = "connectedTo_id")
    private User connectedTo;

    private Timestamp date;

}
