package com.victorantunes.zupgram.model;

import lombok.Value;
import org.neo4j.ogm.annotation.*;


@RelationshipEntity(type = "LIKED")
@Value
public class Like {
    @Id
    @GeneratedValue
    Long id;
    @Property
    Publication publication;
    @StartNode
    User userLiked;
    @EndNode
    User userPosted;
}
