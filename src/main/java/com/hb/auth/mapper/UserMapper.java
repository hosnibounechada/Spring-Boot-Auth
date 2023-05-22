package com.hb.auth.mapper;

import com.hb.auth.model.postgres.User;
import com.hb.auth.payload.request.user.CreateUserRequest;
import com.hb.auth.payload.response.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, CreateUserRequest, UserResponse>{
    @Mapping(target = "fullName", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    UserResponse entityToResponse(User user);

}
