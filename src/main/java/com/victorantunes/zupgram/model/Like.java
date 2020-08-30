package com.victorantunes.zupgram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import org.neo4j.ogm.annotation.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@RelationshipEntity(type = "LIKED")
@With
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue
    Long id;

    @PastOrPresent
    @Property
    @JsonProperty
    LocalDateTime when = LocalDateTime.now();

    @StartNode
    @JsonIgnoreProperties({"fan"})
    User fan;

    @EndNode
    @JsonIgnoreProperties({"owner"})
    Publication publication;
}
