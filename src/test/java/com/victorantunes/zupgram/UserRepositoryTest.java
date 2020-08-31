package com.victorantunes.zupgram;

import com.victorantunes.zupgram.model.User;
import com.victorantunes.zupgram.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void givenUserWhenSaveThenShouldMatch() {
        User victorantunes_ = new User()
                .withUsername("victorantunes_")
                .withName("Victor Antunes")
                .withBiography("Poetas tomem o controle!");

        User saved = userRepository.save(victorantunes_);

        assertEquals(saved.getUsername(), victorantunes_.getUsername());
        assertEquals(saved.getName(), victorantunes_.getName());
        assertEquals(saved.getBiography(), victorantunes_.getBiography());
    }
}
