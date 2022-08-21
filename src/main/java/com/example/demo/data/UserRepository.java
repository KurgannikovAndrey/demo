package com.example.demo.data;

import com.example.demo.data.pojo.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("another")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserRepository {
    private static UserRepository userRepository = null;

    public static UserRepository getInstance(){
        if(userRepository == null){
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    private Map<String, User> repository;

    private UserRepository() {
        repository = new HashMap();
    }

    public boolean add(String username) {
        System.out.println("add");
        System.out.println(repository);
        if (repository.get(username) != null) {
            return false;
        }
        repository.put(username, User.of(username));
        System.out.println("add");
        System.out.println(repository);
        return true;
    }

    public void plus(String username, long value) {
        System.out.println("plus");
        System.out.println(repository);
        User user = repository.get(username);
        if (user == null) {
            throw new IllegalArgumentException("user not found");
        }
        user.setValue(user.getValue() + value);
        System.out.println("plus");
        System.out.println(repository);
    }

    public void minus(String username, long value) {
        System.out.println("minus");
        System.out.println(repository);
        User user = repository.get(username);
        if (user == null) {
            throw new IllegalArgumentException("user not found");
        }
        if (user.getValue() - value < 0) {
            throw new IllegalArgumentException("value not enough");
        }
        user.setValue(user.getValue() - value);
        System.out.println("minus");
        System.out.println(repository);
    }

    public User get(String username) {
        System.out.println("get");
        System.out.println(repository);
        return repository.get(username);
    }

    public boolean exist(String username) {
        System.out.println("exist");
        System.out.println(repository);
        return repository.get(username)!=null;
    }

    protected void put(User user){
        System.out.println("put");
        System.out.println(repository);
        if(user != null){
            repository.put(user.getName(), user);
        }
    }
}
