package com.priscilasanfer.concrete.mapper;

import com.priscilasanfer.concrete.dto.request.UserRequest;
import com.priscilasanfer.concrete.dto.response.UserResponse;
import com.priscilasanfer.concrete.model.Phone;
import com.priscilasanfer.concrete.model.User;
import org.junit.Test;

import java.time.Instant;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserMapperTest {

    @Test
    public void shouldMapUserToUserResponse() {
        User user = User.builder()
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

        UserResponse stateResponse = UserMapper.INSTANCE.userToUserResponse(user);

        assertThat(stateResponse.getId(), is(user.getId()));
        assertThat(stateResponse.getName(), is(user.getName()));
    }

    @Test
    public void shouldMapUserRequestToUser() {
        UserRequest userRequest = UserRequest.builder()
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

        User user = UserMapper.INSTANCE.userRequestToUser(userRequest);

        assertThat(userRequest.getId(), is(user.getId()));
        assertThat(userRequest.getName(), is(user.getName()));
    }
}
