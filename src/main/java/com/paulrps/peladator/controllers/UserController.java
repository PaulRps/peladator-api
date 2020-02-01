package com.paulrps.peladator.controllers;

import com.paulrps.peladator.domain.entities.User;
import com.paulrps.peladator.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("{id}")
    User getById(@PathVariable Long id) {
        return userService.findById(id).get();
    }
}
