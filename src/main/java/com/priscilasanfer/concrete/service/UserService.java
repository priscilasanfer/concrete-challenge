package com.priscilasanfer.concrete.service;

import com.priscilasanfer.concrete.dto.request.UserRequest;
import com.priscilasanfer.concrete.dto.request.UserUpdateRequest;
import com.priscilasanfer.concrete.dto.response.UserResponse;
import com.priscilasanfer.concrete.exception.EmailAlreadyExistsException;
import com.priscilasanfer.concrete.exception.NoSuchElementFoundException;
import com.priscilasanfer.concrete.mapper.UserMapper;
import com.priscilasanfer.concrete.model.Phones;
import com.priscilasanfer.concrete.model.User;
import com.priscilasanfer.concrete.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository repository;

    public List<UserResponse> findAll() {
        return repository.findAll().stream()
                .map(UserMapper.INSTANCE::userToUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException(id));
        return UserMapper.INSTANCE.userToUserResponse(user);
    }

    public UserResponse create(UserRequest userRequest) {

        if (emailAlreadyInUse(userRequest.getEmail())) {
            throw new EmailAlreadyExistsException(userRequest.getEmail());
        }

        String encryptedPassword = encryptPassword(userRequest.getPassword());

        User user = UserMapper.INSTANCE.userRequestToUser(userRequest);
        user.setCreated(Instant.now());
        user.setModified(Instant.now());
        user.setLastLogin(Instant.now());
        user.setPassword(encryptedPassword);
        user = repository.save(user);

        return UserMapper.INSTANCE.userToUserResponse(user);
    }

    private String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    private boolean emailAlreadyInUse(String email) {
        return repository.existsUserByEmail(email);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementFoundException(id);
        }
    }

    public UserResponse update(Long id, UserUpdateRequest userUpdateRequest) {
        User user = repository.findById(id).orElseThrow(() -> new NoSuchElementFoundException(id));
        user.setPassword(userUpdateRequest.getPassword());
        user.setModified(Instant.now());
        user = repository.save(user);
        return UserMapper.INSTANCE.userToUserResponse(user);
    }
}
