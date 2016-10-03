package com.paulwoodiii;

import java.util.ArrayList;

/**
 * Created by paul on 10/3/16.
 */
public class User {
    String username;
    String password;
    ArrayList<Post> posts;

    public User() {
        posts = new ArrayList<>();
    }
}
