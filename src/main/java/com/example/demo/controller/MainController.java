package com.example.demo.controller;

import com.example.demo.data.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    @PostMapping("{username}/add")
    public String addUser(@Autowired @Qualifier("configured") Connection connection, @PathVariable String username) {
        return connection.addUser(username) ? "success" : "fail";
    }

    @PostMapping("transfer")
    public String addUser(@Autowired @Qualifier("configured") Connection connection, @RequestParam(name = "from") String from,
                          @RequestParam(name = "to") String to, @RequestParam(name = "value") int value) {
        return value < 0 || !connection.transfer(from, to, value) ? "fail" : "success";
    }

    @GetMapping("{username}/balance")
    public String getBalance(@Autowired @Qualifier("configured") Connection connection, @PathVariable String username) {
        long balance = 0;
        try {
            balance = connection.getBalance(username);
        } catch (Exception e) {
            return "fail";
        }
        return String.valueOf(balance);
    }
}
