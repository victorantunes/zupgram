package com.victorantunes.zupgram.model;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.awt.*;
import java.util.List;

@NodeEntity
@Data
public class Publication {
    @Id
    @GeneratedValue
    private final Long id;
    private final Image image;
    private final String description;
    private final List<String> tags;
    private final String location; // TODO: transform into Location
    @Relationship(type = "LIKED", direction = Relationship.INCOMING)
    private final List<Like> likes;
}
