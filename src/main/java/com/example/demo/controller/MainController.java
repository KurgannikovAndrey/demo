package com.example.demo.controller;

import com.example.demo.data.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    @GetMapping("add/{username}")
    public String addUser(@Autowired Connection connection, @PathVariable String username) {
        return connection.addUser(username) ? "success" : "fail";
    }

    @PostMapping("transfer")
    public String addUser(@Autowired Connection connection, @RequestParam(name = "from") String from,
                          @PathVariable(name = "to") String to, @PathVariable(name = "value") int value) {
        return connection.transfer(from, to, value) ? "success" : "fail";
    }

    @GetMapping("balance/{username}")
    public String getBalance(@Autowired Connection connection, @PathVariable String username) {
        long balance = 0;
        try {
            balance = connection.getBalance(username);
        } catch (Exception e) {
            return "fail";
        }
        return String.valueOf(balance);
    }
}
