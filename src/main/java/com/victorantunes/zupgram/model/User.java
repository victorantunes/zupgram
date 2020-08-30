package com.victorantunes.zupgram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String name;
    private String biography;
    private File profilePicture;


    @Relationship(type = "FOLLOWS")
    @JsonIgnoreProperties({"follower"})
    private Set<Follow> followings = new HashSet<>();

    @Relationship(type = "POSTED")
    @JsonIgnoreProperties({"user"})
    private Set<Publication> publications = new HashSet<>();

    @Relationship(type = "LIKED")
    @JsonIgnoreProperties({"fan"})
    private Set<Like> publicationsLiked = new HashSet<>();

    public void like(Publication publication) {
        Like like = new Like()
                .withFan(this)
                .withPublication(publication)
                .withWhen(LocalDateTime.now());
        publicationsLiked.add(like);
    }

    public void follow(User user) {
        if (user != null && user != this) {
            Follow follow = new Follow()
                    .withFollower(this)
                    .withFollowee(user)
                    .withSince(LocalDateTime.now());
            followings.add(follow);
        }
    }
}
