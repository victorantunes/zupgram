package com.victorantunes.zupgram.model;

import lombok.Data;

@Data
public class Me {
    private final User user;
    private Settings settings;
}
