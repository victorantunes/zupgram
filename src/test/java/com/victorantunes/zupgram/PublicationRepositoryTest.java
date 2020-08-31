package com.victorantunes.zupgram;

import com.victorantunes.zupgram.model.Publication;
import com.victorantunes.zupgram.model.User;
import com.victorantunes.zupgram.repository.PublicationRepository;
import com.victorantunes.zupgram.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PublicationRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    private User victorantunes_;

    @BeforeAll
    void beforeAll() {
        victorantunes_ = new User()
                .withUsername("victorantunes_")
                .withName("Victor Antunes")
                .withBiography("Poetas tomem o controle!");
        userRepository.save(victorantunes_);
    }

    @BeforeEach
    void beforeEach() {
        publicationRepository.deleteAll();
    }

    @Test
    void givenPublicationWhenSaveThenShouldMatch() {
        Publication publication = new Publication()
                .withOwner(victorantunes_)
                .withDescription("Another cool day at the beach")
                .withLocation("At the beach")
                .withTags(Set.of("beach", "sunset"));

        Publication saved = publicationRepository.save(publication);


        assertEquals(victorantunes_.getUsername(), saved.getOwner().getUsername());
        assertEquals(publication.getDescription(), saved.getDescription());
        assertEquals(publication.getLocation(), saved.getLocation());
        assertEquals(publication.getTags(), saved.getTags());
    }

    @Test
    void givenPublicationSavedWhenFindByIdThenOptionalShouldNotBeEmpty() {
        Publication publication = new Publication()
                .withOwner(victorantunes_)
                .withDescription("Another cool day at the beach")
                .withLocation("At the beach")
                .withTags(Set.of("beach", "sunset"));

        publicationRepository.save(publication);

        Optional<Publication> byId = publicationRepository.findById(publication.getId());

        assertTrue(byId.isPresent());
    }

    @Test
    void givenPublicationSavedWhenFindByIdThenShouldMatch() {
        Publication publication = new Publication()
                .withOwner(victorantunes_)
                .withDescription("Another cool day at the beach")
                .withLocation("At the beach")
                .withTags(Set.of("beach", "sunset"));

        Publication saved = publicationRepository.save(publication);

        Publication found = publicationRepository.findById(publication.getId()).get();

        assertEquals(victorantunes_.getUsername(), found.getOwner().getUsername());
        assertEquals(saved.getDescription(), found.getDescription());
        assertEquals(saved.getLocation(), found.getLocation());
        assertEquals(saved.getTags(), found.getTags());
    }

    @Test
    void givenPublicationSavedWhenFindByTagThenShouldMatch() {
        Publication publication = new Publication()
                .withOwner(victorantunes_)
                .withDescription("Another cool day at the beach")
                .withLocation("At the beach")
                .withTags(Set.of("beach", "sunset"));

        publicationRepository.save(publication);

        List<Publication> found = publicationRepository.findByTag("sunset");

        System.err.println(found);
        assertEquals(1, found.size());
        assertEquals(publication.getDescription(), found.get(0).getDescription());
        assertEquals(publication.getLocation(), found.get(0).getLocation());
        assertEquals(publication.getTags(), found.get(0).getTags());
    }

    @Test
    void givenPublicationSavedWhenFindByLocationCaseInsensitiveThenShouldMatch() {
        Publication publication = new Publication()
                .withOwner(victorantunes_)
                .withDescription("Another cool day at the beach")
                .withLocation("At the beach")
                .withTags(Set.of("beach", "sunset"));

        publicationRepository.save(publication);

        List<Publication> found = publicationRepository.findByLocation("BEACH");

        assertEquals(1, found.size());
        assertEquals(publication.getDescription(), found.get(0).getDescription());
        assertEquals(publication.getLocation(), found.get(0).getLocation());
        assertEquals(publication.getTags(), found.get(0).getTags());
    }

    @Test
    void givenPublicationSavedWhenFindFansByPublicationIdThenShouldMatch() {
        Publication publication = new Publication()
                .withOwner(victorantunes_)
                .withDescription("Another cool day at the beach")
                .withLocation("At the beach")
                .withTags(Set.of("beach", "sunset"));

        Publication saved = publicationRepository.save(publication);

        victorantunes_.like(publication);
        userRepository.save(victorantunes_);

        List<User> fans = publicationRepository.findFansByPublicationId(saved.getId());

        assertEquals(1, fans.size());
        assertEquals(victorantunes_.getUsername(), fans.get(0).getUsername());
        assertEquals(victorantunes_.getName(), fans.get(0).getName());
        assertEquals(victorantunes_.getBiography(), fans.get(0).getBiography());
    }
}
