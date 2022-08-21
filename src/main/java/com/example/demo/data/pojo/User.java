package com.example.demo.data.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private String name;
    private long value;

    public static User of(User user){
        if(user == null){
            return null;
        }
        User newUser = new User();
        newUser.name = user.name;
        newUser.value = user.value;
        return newUser;
    }

    public static User of(String username){
        User newUser = new User();
        newUser.name = username;
        newUser.value = 0L;
        return newUser;
    }
}
