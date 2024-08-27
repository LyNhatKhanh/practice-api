package com.lynhatkhanh.identity_service.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.lynhatkhanh.identity_service.dto.request.UserCreationRequest;
import com.lynhatkhanh.identity_service.dto.request.UserUpdateRequest;
import com.lynhatkhanh.identity_service.dto.response.ApiResponse;
import com.lynhatkhanh.identity_service.dto.response.UserResponse;
import com.lynhatkhanh.identity_service.service.IUserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    IUserService userService;

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(userService.getMyInfo());
        return apiResponse;
    }

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        log.info("UserController - createUser");
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(userService.getUsers());
        return apiResponse;
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(userService.getUser(userId));
        return apiResponse;
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(
            @PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
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
