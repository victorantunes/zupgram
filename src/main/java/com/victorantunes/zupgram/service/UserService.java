package com.victorantunes.zupgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.file.Files;

@Service
public class UserService {

    @Autowired
    ResourceLoader resourceLoader;

    public byte[] loadImageByUsername(String username) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/profile_images/" + username + ".jpg");
        return StreamUtils.copyToByteArray(resource.getInputStream());
    }
}
