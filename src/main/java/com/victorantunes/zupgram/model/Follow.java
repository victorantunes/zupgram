package com.victorantunes.zupgram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import org.neo4j.ogm.annotation.*;

import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@RelationshipEntity(type = "FOLLOWS")
@With
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
    @Id
    @GeneratedValue
    Long id;

    @PastOrPresent
    @Property
    @JsonProperty
    LocalDateTime since = LocalDateTime.now();

    @StartNode
    @JsonIgnore
    User follower;

    @EndNode
    User followee;
}
