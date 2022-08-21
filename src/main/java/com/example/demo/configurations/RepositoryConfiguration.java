package com.example.demo.configurations;

import com.example.demo.data.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RepositoryConfiguration {

    @Bean
    @Qualifier("default")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public UserRepository getRepo(){
        return new UserRepository();
    }
}
