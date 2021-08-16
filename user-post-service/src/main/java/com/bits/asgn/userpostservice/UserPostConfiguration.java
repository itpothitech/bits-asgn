package com.bits.asgn.userpostservice;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan
@EntityScan("icom.bits.asgn.userpostservice")
@EnableMongoRepositories("com.bits.asgn.userpostservice")
@PropertySource("classpath:application.properties")
public class UserPostConfiguration {

}
