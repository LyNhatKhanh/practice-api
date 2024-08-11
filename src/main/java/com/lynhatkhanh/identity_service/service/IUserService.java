package com.lynhatkhanh.identity_service.service;

import com.lynhatkhanh.identity_service.dto.request.UserCreationRequest;
import com.lynhatkhanh.identity_service.dto.request.UserUpdateRequest;
import com.lynhatkhanh.identity_service.entity.User;

import java.util.List;

public interface IUserService {

    User createUser(UserCreationRequest request);

    List<User> getUsers();

    User getUser(String userId);

    User updateUser(String userId, UserUpdateRequest request);

    void deleteUser(String userId);

}
