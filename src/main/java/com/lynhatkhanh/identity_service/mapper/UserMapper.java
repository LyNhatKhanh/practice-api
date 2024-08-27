package com.lynhatkhanh.identity_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.lynhatkhanh.identity_service.dto.request.UserCreationRequest;
import com.lynhatkhanh.identity_service.dto.request.UserUpdateRequest;
import com.lynhatkhanh.identity_service.dto.response.UserResponse;
import com.lynhatkhanh.identity_service.entity.User;

// declare to provide Dependency injection => @Autowired
@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    User toUser(UserCreationRequest request);

    //    @Mapping(source = "firstName", target = "lastName")
    //    @Mapping(target = "dob", ignore = true)
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
