package com.priscilasanfer.concrete.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.priscilasanfer.concrete.dto.request.UserRequest;
import com.priscilasanfer.concrete.dto.response.UserResponse;
import com.priscilasanfer.concrete.exception.ErrorHandler;
import com.priscilasanfer.concrete.exception.NoSuchElementFoundException;
import com.priscilasanfer.concrete.model.Phone;
import com.priscilasanfer.concrete.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private MockMvc mockMvc;

    private UserRequest userRequest;
    private UserResponse userResponse;

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService service;

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

        userResponse = UserResponse.builder()
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

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new ErrorHandler())
                .build();
    }

    @Test
    public void shouldCreateUserWithJsonRequest() throws Exception {
        when(service.create(any())).thenReturn(userResponse);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("João da Silva")));
    }

    @Test
    public void shouldReturnNotFoundStateWithInvalidId() throws Exception {
        when(service.findById(anyLong())).thenThrow(NoSuchElementFoundException.class);

        mockMvc.perform(get("users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnUserWithValidId() throws Exception {
        when(service.findById(anyLong())).thenReturn(userResponse);

        mockMvc.perform(get("users/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    public void deleteEmployeeAPI() throws Exception {
        mockMvc.perform(delete("/users/{id}", 1))
                .andExpect(status().isNoContent());
    }
}
