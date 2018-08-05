package com.krishna.spring.springgitdemo.controller;


import com.krishna.spring.springgitdemo.model.User;
import com.krishna.spring.springgitdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class RestApiController {

    private static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    UserService userService; //Service which will do all data retrieval/manipulation work

    // -------------------Retrieve All Users---------------------------------------------

    @GetMapping
    public List<User> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return null;
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return users;
    }

    // -------------------Retrieve Single User------------------------------------------

    @GetMapping(value = "/user/{id}")
    public User getUser(@PathVariable("id") long id) {
        logger.info("Fetching User with id {}", id);
        User user = userService.findById(id);
        if (user == null) {
            logger.error("User with id {} not found.", id);
            return null;
        }
        return user;
    }



}
