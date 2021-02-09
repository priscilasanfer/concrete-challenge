package com.priscilasanfer.concrete.mapper;

import com.priscilasanfer.concrete.dto.request.UserRequest;
import com.priscilasanfer.concrete.dto.response.UserResponse;
import com.priscilasanfer.concrete.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userRequestToUser(UserRequest userRequest);
    UserResponse userToUserResponse(User user);

}
