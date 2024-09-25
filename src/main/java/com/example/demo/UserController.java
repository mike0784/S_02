package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return repository.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getUser(@PathVariable long id) {
        User user =repository.getById(id);
        return user.toString();
    }

    @GetMapping
    public User getUserByName(@RequestParam String name) {
        return repository.getByName(name);
    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user) {
        User existsUser = repository.getById(id);
        if (existsUser == null) { // 404
            System.out.println("Ничего нет");
            throw new IllegalArgumentException();
        }

        existsUser.setName(user.getName());
        return existsUser;
    }
}
