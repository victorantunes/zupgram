package com.victorantunes.zupgram.repository;

import com.victorantunes.zupgram.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends Neo4jRepository<User, Long> {


    User findByUsername(@Param("username") String username);

    @Query("MATCH (u:User)<-[:FOLLOWS]-(friends) WHERE u.username = $username RETURN friends;")
    List<User> findFollowersByUsername(@Param("username") String username);

    @Query("MATCH (u:User)-[:FOLLOWS]->(friends) WHERE u.username = $username RETURN friends;")
    List<User> findFolloweesByUsername(@Param("username") String username);

    @Query("MATCH (u:User)<-[:FOLLOWS]-(friends)" +
            "MATCH (u:User)-[:FOLLOWS]->(friends) WHERE u.username = $username RETURN friends;")
    List<User> findMutualFollowersByUsername(@Param("username") String username);

}
