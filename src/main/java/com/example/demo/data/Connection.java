package com.example.demo.data;

import com.example.demo.data.pojo.User;
import com.example.demo.services.TransferService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Connection {
    private final UserService userService;
    private final TransferService transferService;

    public Connection() {
        this.userService = UserService.getInstance();
        transferService = TransferService.getInstance();
    }

    public boolean transfer(String from, String to, long value) {
        User userFrom = User.of(userService.get(from));
        User userTo = User.of(userService.get(to));
        try {
            if (!from.equals("external")) {
                transferService.minus(userFrom, value);
            }
            transferService.plus(userTo, value);
        } catch(Exception e) {
            return false;
        }
        boolean done = false;
        boolean result = false;
        while(!done){
            if(from.equals("external") || userService.get(from).tryLock()){
                if(userService.get(to).tryLock()){
                    try {
                        if (!from.equals("external")) {
                            userService.updateUser(userFrom);
                        }
                        userService.updateUser(userTo);
                        result = true;
                    }catch (Exception e){
                        e.printStackTrace();
                        result = false;
                    }finally {
                        if (!from.equals("external")) {
                            userService.get(from).unlock();
                        }
                        userService.get(to).unlock();
                        done = true;
                    }
                }
            }else if(!from.equals("external")){
                userService.get(from).unlock();
            }
        }

        return result;
    }

    public boolean addUser(String username) {
        synchronized (userService) {
            if (userService.exist(username)) {
                return false;
            } else {
                userService.add(username);
                return true;
            }
        }
    }

    public long getBalance(String username) throws IllegalAccessException {
        if (!userService.exist(username)) {
            throw new IllegalAccessException("user not found");
        }
        synchronized (userService.get(username)) {
            return userService.get(username).getValue();
        }
    }
}
