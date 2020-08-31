package com.victorantunes.zupgram.controller;

import com.victorantunes.zupgram.repository.UserRepository;
import com.victorantunes.zupgram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@RepositoryRestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping(value = "image/{username}")
    public ResponseEntity<byte[]> image(@PathVariable String username) throws IOException {
//        User user = userRepository.findById(Long.valueOf(id)).get();
//        System.out.println(user);
        byte[] image = userService.loadImageByUsername(username);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
}
