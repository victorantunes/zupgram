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

import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class Publication {
    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "POSTED", direction = Relationship.INCOMING)
    @JsonIgnoreProperties({"publications"})
    private User owner;

    //    private Image image;
    private String description;

    private Set<String> tags = new HashSet<>();

    private String location; // TODO: transform into Location object

    @PastOrPresent
    private LocalDateTime createdAt = LocalDateTime.now();

    @Relationship(type = "LIKED", direction = Relationship.INCOMING)
    @JsonIgnoreProperties({"publication"})
    private Set<Like> likes = new HashSet<>();

}

