package com.example.demo.data;

import com.example.demo.data.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Connection {
    private final UserRepository userRepository;

//    public Connection(@Autowired @Qualifier("default") UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public Connection() {
        this.userRepository = UserRepository.getInstance();
    }

    public boolean transfer(String from, String to, long value) {
        synchronized (userRepository) {
            User userFrom = User.of(userRepository.get(from));
            User userTo = User.of(userRepository.get(to));
            try {
                if(!from.equals("external")){
                    userRepository.minus(from, value);
                }
                userRepository.plus(to, value);
            } catch (Exception e) {
                e.printStackTrace();
                if(!from.equals("external")){
                    userRepository.put(userFrom);
                }
                userRepository.put(userTo);
                return false;
            }
            return true;
        }
    }

    public boolean addUser(String username) {
        synchronized (userRepository) {
            return userRepository.add(username);
        }
    }

    public long getBalance(String username) throws IllegalAccessException {
        synchronized (userRepository) {
            if (!userRepository.exist(username)) {
                throw new IllegalAccessException("user not found");
            }
            return userRepository.get(username).getValue();
        }
    }
}
