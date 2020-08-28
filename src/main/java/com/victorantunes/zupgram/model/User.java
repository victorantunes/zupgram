package com.victorantunes.zupgram.model;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.awt.*;
import java.util.List;

@NodeEntity
@Data
public class User {
    @Id
    @GeneratedValue
    private final Long id;
    private final String login;
    private final String bio;
    private final Image profilePicture;
    private final List<Publication> publications;
    @Relationship(type = "FOLLOWS", direction = Relationship.INCOMING)
    private final List<User> following;
    @Relationship(type = "FOLLOWS", direction = Relationship.OUTGOING)
    private final List<User> followers;
    @Relationship(type = "LIKED", direction = Relationship.OUTGOING)
    private final List<Like> likes;
}
