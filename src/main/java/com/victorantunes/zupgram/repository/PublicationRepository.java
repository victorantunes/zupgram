package com.victorantunes.zupgram.repository;

import com.victorantunes.zupgram.model.Publication;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "publications", path = "publications")
public interface PublicationRepository extends Neo4jRepository<Publication, Long> {

//    List<Publication> findByTags(@Param("tags") String... tags);
}
