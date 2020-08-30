package com.victorantunes.zupgram;

import com.victorantunes.zupgram.model.Publication;
import com.victorantunes.zupgram.model.User;
import com.victorantunes.zupgram.repository.PublicationRepository;
import com.victorantunes.zupgram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublicationRepository publicationRepository;


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
                .withLocation("At the beach");
        publicationRepository.save(pub1);

        pedrohenrique.like(pub1);
        userRepository.save(pedrohenrique);

        pedrohenrique.follow(victorantunes_);
        userRepository.save(pedrohenrique);

        victorantunes_.follow(pedrohenrique);
        userRepository.save(victorantunes_);

        raimundoinacio.follow(victorantunes_);
        userRepository.save(raimundoinacio);

        System.out.println(userRepository.findFollowersByUsername("victorantunes_"));
    }
}
