package com.priscilasanfer.concrete.controller;

import com.priscilasanfer.concrete.dto.request.UserRequest;
import com.priscilasanfer.concrete.dto.request.UserUpdateRequest;
import com.priscilasanfer.concrete.dto.response.UserResponse;
import com.priscilasanfer.concrete.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {

    private UserService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserResponse> findAll() {
        return service.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        return service.create(userRequest);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest) {
        return service.update(id, userUpdateRequest);
    }
}
