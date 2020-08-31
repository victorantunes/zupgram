package com.victorantunes.zupgram.utils;

import com.victorantunes.zupgram.model.Publication;
import com.victorantunes.zupgram.model.User;
import com.victorantunes.zupgram.repository.PublicationRepository;
import com.victorantunes.zupgram.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PublicationRepository publicationRepository;

    public DatabaseLoader(UserRepository userRepository, PublicationRepository publicationRepository) {
        this.userRepository = userRepository;
        this.publicationRepository = publicationRepository;
    }

    @Override
    public void run(String... args) {
        userRepository.deleteAll();
        publicationRepository.deleteAll();

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

        User raimundoinacio = new User()
                .withUsername("raimundoinacio")
                .withName("Raimundo Inácio")
                .withBiography("Em defesa da educação");
        userRepository.save(raimundoinacio);


        Publication pub1 = new Publication()
                .withOwner(victorantunes_)
                .withDescription("Another cool day at the beach")
                .withLocation("At the beach")
                .withTags(Set.of("beach", "sunset"));
        publicationRepository.save(pub1);

        pedrohenrique.like(pub1);
        userRepository.save(pedrohenrique);

        pedrohenrique.follow(victorantunes_);
        userRepository.save(pedrohenrique);

        victorantunes_.follow(pedrohenrique);
        userRepository.save(victorantunes_);

        raimundoinacio.follow(victorantunes_);
        userRepository.save(raimundoinacio);
    }
}
