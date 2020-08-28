package com.victorantunes.zupgram.model;

import lombok.Value;
import org.neo4j.ogm.annotation.*;

import java.util.Date;

@RelationshipEntity(type = "FOLLOWS")
@Value
public class Follow {
    @Id
    @GeneratedValue
    Long id;
    @Property
    Date since;
    @StartNode
    User follower;
    @EndNode
    User followed;
}
