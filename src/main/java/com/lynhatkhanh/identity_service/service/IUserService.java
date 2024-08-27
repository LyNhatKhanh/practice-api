package com.lynhatkhanh.identity_service.service;

import java.util.List;

import com.lynhatkhanh.identity_service.dto.request.UserCreationRequest;
import com.lynhatkhanh.identity_service.dto.request.UserUpdateRequest;
import com.lynhatkhanh.identity_service.dto.response.UserResponse;

public interface IUserService {

    UserResponse getMyInfo();

    UserResponse createUser(UserCreationRequest request);

    List<UserResponse> getUsers();

    UserResponse getUser(String userId);

    UserResponse updateUser(String userId, UserUpdateRequest request);

    void deleteUser(String userId);
}
