package com.paulrps.peladator.controllers;

import com.paulrps.peladator.domain.dto.UserFormDto;
import com.paulrps.peladator.domain.entities.User;
import com.paulrps.peladator.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("{id}")
    ResponseEntity<User> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getOne(id).get());
    }

    @GetMapping("form-data")
    ResponseEntity<UserFormDto> getFormData() {
        return ResponseEntity.ok(userService.getFormData());
    }

    @GetMapping
    ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping
    ResponseEntity<List<User>> save(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(userService.getAll());
    }

    @PutMapping
    ResponseEntity<List<User>> update(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(userService.getAll());
    }

    @DeleteMapping("{id}")
    ResponseEntity<List<User>> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok(userService.getAll());
    }

}
