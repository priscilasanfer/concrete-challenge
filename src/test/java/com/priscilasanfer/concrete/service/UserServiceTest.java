package com.priscilasanfer.concrete.service;

import com.priscilasanfer.concrete.dto.request.UserRequest;
import com.priscilasanfer.concrete.dto.response.UserResponse;
import com.priscilasanfer.concrete.exception.EmailAlreadyExistsException;
import com.priscilasanfer.concrete.exception.NoSuchElementFoundException;
import com.priscilasanfer.concrete.mapper.UserMapper;
import com.priscilasanfer.concrete.model.Phone;
import com.priscilasanfer.concrete.model.User;
import com.priscilasanfer.concrete.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    private UserRequest userRequest;
    private User user;
    private UserResponse userResponse;

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Before
    public void setUp() {
        userRequest = UserRequest.builder()
                .id(1L)
                .name("João da Silva")
                .email("joao@silva.org")
                .password("hunter2")
                .phones(Collections.singletonList(Phone.builder()
                        .id(1L)
                        .number("987654321")
                        .ddd("21")
                        .build()))
                .build();

        user = User.builder()
                .id(1L)
                .name("João da Silva")
                .email("joao@silva.org")
                .password("hunter2")
                .phones(Collections.singletonList(Phone.builder()
                        .id(1L)
                        .number("987654321")
                        .ddd("21")
                        .build()))
                .created(Instant.now())
                .modified(Instant.now())
                .lastLogin(Instant.now())
                .token(null)
                .build();

        userResponse = UserMapper.INSTANCE.userToUserResponse(user);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateUser() {
        service.create(userRequest);
        verify(repository, Mockito.times(1)).save(user);
    }

    @Test
    public void shouldReturnExceptionBecauseEmailAlreadyExist() {
        when(repository.existsUserByEmail(Mockito.any())).thenReturn(true);
        Assertions.assertThrows(EmailAlreadyExistsException.class, () -> {
            service.create(userRequest);
        });
    }

    @Test
    public void shouldReturnUserFromDatabase() {
        Mockito.when(repository.findById(user.getId())).thenReturn(java.util.Optional.of(user));
        UserResponse userResponse = service.findById(user.getId());
        Assertions.assertEquals(userResponse.getId(), user.getId());
    }

    @Test
    public void shouldThrowsNoSuchElementFoundExceptionWithInvalidIdWhenFindById() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementFoundException.class, () -> {
            service.findById(1L);
        });
    }
    @Test
    public void shouldDeleteUser() {
        service.delete(anyLong());
        verify(repository, Mockito.times(1)).deleteById(anyLong());
    }

}
