package com.victorantunes.zupgram;

import com.victorantunes.zupgram.model.User;
import com.victorantunes.zupgram.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void beforeEach() {
        userRepository.deleteAll();
    }

    @Test
    void givenUserWhenSaveThenShouldMatch() {
        User victorantunes_ = new User()
                .withUsername("victorantunes_")
                .withName("Victor Antunes")
                .withBiography("Poetas tomem o controle!");

        User saved = userRepository.save(victorantunes_);

        assertEquals(victorantunes_.getUsername(), saved.getUsername());
        assertEquals(victorantunes_.getName(), saved.getName());
        assertEquals(victorantunes_.getBiography(), saved.getBiography());
    }

    @Test
    void givenUserSavedWhenFindByIdThenOptionalShouldNotBeEmpty() {
        User victorantunes_ = new User()
                .withUsername("victorantunes_")
                .withName("Victor Antunes")
                .withBiography("Poetas tomem o controle!");

        User saved = userRepository.save(victorantunes_);

        Optional<User> byId = userRepository.findById(saved.getId());

        assertTrue(byId.isPresent());
    }

    @Test
    void givenUserSavedWhenFindByIdThenShouldMatch() {
        User victorantunes_ = new User()
                .withUsername("victorantunes_")
                .withName("Victor Antunes")
                .withBiography("Poetas tomem o controle!");

        User saved = userRepository.save(victorantunes_);

        User found = userRepository.findById(saved.getId()).get();

        assertEquals(saved.getUsername(), found.getUsername());
        assertEquals(saved.getName(), found.getName());
        assertEquals(saved.getBiography(), found.getBiography());
    }

    @Test
    void givenUserSavedWhenFindByUsernameThenShouldMatch() {
        User victorantunes_ = new User()
                .withUsername("victorantunes_")
                .withName("Victor Antunes")
                .withBiography("Poetas tomem o controle!");

        userRepository.save(victorantunes_);

        User found = userRepository.findByUsername(victorantunes_.getUsername());

        assertEquals(victorantunes_.getUsername(), found.getUsername());
        assertEquals(victorantunes_.getName(), found.getName());
        assertEquals(victorantunes_.getBiography(), found.getBiography());
    }

    @Test
    void givenUsersWhenFirstGetsFollowedThenFollowerShouldMatch() {
        User victorantunes_ = new User()
                .withUsername("victorantunes_")
                .withName("Victor Antunes")
                .withBiography("Poetas tomem o controle!");
        userRepository.save(victorantunes_);

        User pedrohenrique = new User()
                .withUsername("pedrohenrique")
                .withName("Pedro Henrique")
                .withBiography("Queria jogar Free Fire!");

        userRepository.save(pedrohenrique);

        pedrohenrique.follow(victorantunes_);
        userRepository.save(pedrohenrique);

        List<User> followers = userRepository.findFollowersByUsername("victorantunes_");

        assertEquals(1, followers.size());
        assertEquals(pedrohenrique.getUsername(), followers.get(0).getUsername());
        assertEquals(pedrohenrique.getName(), followers.get(0).getName());
        assertEquals(pedrohenrique.getBiography(), followers.get(0).getBiography());
    }

    @Test
    void givenUsersWhenFirstFollowsThenFolloweeShouldMatch() {
        User victorantunes_ = new User()
                .withUsername("victorantunes_")
                .withName("Victor Antunes")
                .withBiography("Poetas tomem o controle!");
        userRepository.save(victorantunes_);

        User pedrohenrique = new User()
                .withUsername("pedrohenrique")
                .withName("Pedro Henrique")
                .withBiography("Queria jogar Free Fire!");
        userRepository.save(pedrohenrique);

        victorantunes_.follow(pedrohenrique);
        userRepository.save(victorantunes_);

        List<User> followees = userRepository.findFolloweesByUsername("victorantunes_");

        assertEquals(1, followees.size());
        assertEquals(pedrohenrique.getUsername(), followees.get(0).getUsername());
        assertEquals(pedrohenrique.getName(), followees.get(0).getName());
        assertEquals(pedrohenrique.getBiography(), followees.get(0).getBiography());
    }

    @Test
    void givenUsersWhenMutualFollowThenBothShouldMatch() {
        User victorantunes_ = new User()
                .withUsername("victorantunes_")
                .withName("Victor Antunes")
                .withBiography("Poetas tomem o controle!");
        userRepository.save(victorantunes_);

        User pedrohenrique = new User()
                .withUsername("pedrohenrique")
                .withName("Pedro Henrique")
                .withBiography("Queria jogar Free Fire!");
        userRepository.save(pedrohenrique);

        pedrohenrique.follow(victorantunes_);
        userRepository.save(pedrohenrique);

        victorantunes_.follow(pedrohenrique);
        userRepository.save(victorantunes_);

        List<User> victorantunes_followers = userRepository.findFollowersByUsername("victorantunes_");
        List<User> pedrohenrique_followers = userRepository.findFollowersByUsername("pedrohenrique");

        assertEquals(1, victorantunes_followers.size());
        assertEquals(1, pedrohenrique_followers.size());

        assertEquals(pedrohenrique.getUsername(), victorantunes_followers.get(0).getUsername());
        assertEquals(pedrohenrique.getName(), victorantunes_followers.get(0).getName());
        assertEquals(pedrohenrique.getBiography(), victorantunes_followers.get(0).getBiography());

        assertEquals(victorantunes_.getUsername(), pedrohenrique_followers.get(0).getUsername());
        assertEquals(victorantunes_.getName(), pedrohenrique_followers.get(0).getName());
        assertEquals(victorantunes_.getBiography(), pedrohenrique_followers.get(0).getBiography());

    }
}
