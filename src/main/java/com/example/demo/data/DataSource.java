package com.example.demo.data;

import com.example.demo.data.pojo.User;
import com.example.demo.services.TransferService;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataSource {
    private volatile static DataSource src = null;

    public static DataSource getInstance(){
        if(src == null){
            synchronized(DataSource.class) {
                if(src == null) {
                    src = new DataSource();
                }
            }
        }
        return src;
    }

    @Getter
    private Map<String, User> repository;
    private DataSource(){
        repository = new HashMap();
    }
}
