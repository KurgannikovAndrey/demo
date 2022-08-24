package com.example.demo.services;

import com.example.demo.data.DataSource;
import com.example.demo.data.pojo.User;

public class TransferService {
    private volatile static TransferService transferService = null;

    public static TransferService getInstance(){
        if(transferService == null){
            synchronized(TransferService.class) {
                if(transferService == null) {
                    transferService = new TransferService();
                }
            }
        }
        return transferService;
    }

    private DataSource repository;

    private TransferService() {
        repository = DataSource.getInstance();
    }

    public void plus(String username, long value) {
        User user = repository.getRepository().get(username);
        if (user == null) {
            throw new IllegalArgumentException("user not found");
        }
        user.setValue(user.getValue() + value);
    }

    public void plus(User user, long value) {
        if (user == null) {
            throw new IllegalArgumentException("user not found");
        }
        user.setValue(user.getValue() + value);
    }

    public void minus(String username, long value) {
        User user = repository.getRepository().get(username);
        if (user == null) {
            throw new IllegalArgumentException("user not found");
        }
        if (user.getValue() - value < 0) {
            throw new IllegalArgumentException("value not enough");
        }
        user.setValue(user.getValue() - value);
    }

    public void minus(User user, long value) {
        if (user == null) {
            throw new IllegalArgumentException("user not found");
        }
        if (user.getValue() - value < 0) {
            throw new IllegalArgumentException("value not enough");
        }
        user.setValue(user.getValue() - value);
    }
}
