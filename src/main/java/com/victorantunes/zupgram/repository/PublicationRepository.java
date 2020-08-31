package com.victorantunes.zupgram.repository;

import com.victorantunes.zupgram.model.Publication;
import com.victorantunes.zupgram.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "publications", path = "publications")
public interface PublicationRepository extends Neo4jRepository<Publication, Long> {

    @Query("MATCH (p:Publication) WHERE $tag IN p.tags RETURN p;")
    List<Publication> findByTag(@Param("tag") String tag);

    @Query("MATCH (p:Publication) WHERE p.location =~ '.*(?i)'+$location+'.*' RETURN p;")
    List<Publication> findByLocation(@Param("location") String tag);

    @Query("MATCH (p:Publication)<-[:LIKED]-(fans) WHERE id(p) = $id RETURN fans;")
    List<User> findFansByPublicationId(@Param("id") Long id);
}
