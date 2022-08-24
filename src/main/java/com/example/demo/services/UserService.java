package com.example.demo.services;

import com.example.demo.data.DataSource;
import com.example.demo.data.pojo.User;

public class UserService {
    private volatile static UserService userService = null;

    public static UserService getInstance(){
        if(userService == null){
            synchronized(UserService.class) {
                if(userService == null) {
                    userService = new UserService();
                }
            }
        }
        return userService;
    }

    private DataSource repository;

    private UserService() {
        repository = DataSource.getInstance();
    }

    public User get(String username) {
        return repository.getRepository().get(username);
    }

    public void add(String username) {
        repository.getRepository().put(username, User.of(username));
    }

    public boolean exist(String username) {
        return repository.getRepository().get(username)!=null;
    }

    public void updateUser(User user){
        if(user != null && exist(user.getName())){
            get(user.getName()).setValue(user.getValue());
        }
    }
}
