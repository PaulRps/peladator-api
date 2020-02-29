package com.paulrps.peladator.controllers;

import com.paulrps.peladator.domain.dto.UserFormDto;
import com.paulrps.peladator.domain.entities.User;
import com.paulrps.peladator.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

  @Autowired UserService userService;

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("{id}")
  ResponseEntity<User> getById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getOne(id).get());
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("form-data")
  ResponseEntity<UserFormDto> getFormData() {
    return ResponseEntity.ok(userService.getFormData());
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping
  ResponseEntity<List<User>> getAll() {
    return ResponseEntity.ok(userService.getAll());
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping
  ResponseEntity<List<User>> save(@RequestBody User user) {
    userService.save(user);
    return ResponseEntity.ok(userService.getAll());
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping
  ResponseEntity<List<User>> update(@RequestBody User user) {
    userService.save(user);
    return ResponseEntity.ok(userService.getAll());
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping("{id}")
  ResponseEntity<List<User>> delete(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.ok(userService.getAll());
  }
}
