package com.example.demo.data;

import com.example.demo.data.pojo.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository {
    private Map<String, User> repository;

    public UserRepository() {
        repository = new HashMap();
    }

    public boolean add(String username) {
        if (repository.get(username) != null) {
            return false;
        }
        repository.put(username, User.of(username));
        return true;
    }

    public void plus(String username, long value) {
        User user = repository.get(username);
        if (user == null) {
            throw new IllegalArgumentException("user not found");
        }
        user.setValue(user.getValue() + value);
    }

    public void minus(String username, long value) {
        User user = repository.get(username);
        if (user == null) {
            throw new IllegalArgumentException("user not found");
        }
        if (user.getValue() - value < 0) {
            throw new IllegalArgumentException("value not enough");
        }
        user.setValue(user.getValue() - value);
    }

    public User get(String username) {
        return repository.get(username);
    }

    public boolean exist(String username) {
        return repository.get(username)!=null;
    }

    protected void put(User user){
        if(user != null){
            repository.put(user.getName(), user);
        }
    }
}
