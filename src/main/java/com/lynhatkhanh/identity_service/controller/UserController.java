package com.lynhatkhanh.identity_service.controller;

import com.lynhatkhanh.identity_service.dto.request.UserCreationRequest;
import com.lynhatkhanh.identity_service.dto.request.UserUpdateRequest;
import com.lynhatkhanh.identity_service.dto.response.ApiResponse;
import com.lynhatkhanh.identity_service.entity.User;
import com.lynhatkhanh.identity_service.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(userService.createUser(request));
         return apiResponse;
    }

    @GetMapping
    ApiResponse<List<User>> getUsers() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(userService.getUsers());
        return apiResponse;
    }

    @GetMapping("/{userId}")
    ApiResponse<User> getUser(@PathVariable("userId") String userId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(userService.getUser(userId));
        return apiResponse;
    }

    @PutMapping("/{userId}")
    ApiResponse<User> updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(userService.updateUser(userId, request));
        return apiResponse;
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("User has been deleted!");
        return apiResponse;
    }
}
