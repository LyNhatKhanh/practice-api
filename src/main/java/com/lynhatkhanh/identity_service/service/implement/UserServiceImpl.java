package com.lynhatkhanh.identity_service.service.implement;

import com.lynhatkhanh.identity_service.dto.request.UserCreationRequest;
import com.lynhatkhanh.identity_service.dto.request.UserUpdateRequest;
import com.lynhatkhanh.identity_service.dto.response.UserResponse;
import com.lynhatkhanh.identity_service.entity.User;
import com.lynhatkhanh.identity_service.enums.RoleEnum;
import com.lynhatkhanh.identity_service.exception.AppException;
import com.lynhatkhanh.identity_service.exception.ErrorCode;
import com.lynhatkhanh.identity_service.mapper.UserMapper;
import com.lynhatkhanh.identity_service.repository.UserRepository;
import com.lynhatkhanh.identity_service.service.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserServiceImpl implements IUserService {
    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public UserResponse getMyInfo() {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return userMapper.toUserResponse(user);
    }

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User newUser = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(RoleEnum.USER.name());
        newUser.setRoles(roles);

        User createdUser = userRepository.save(newUser);

        return userMapper.toUserResponse(createdUser);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        log.info("in getUsers method");
        List<User> users = userRepository.findAll();
        List<UserResponse> results = new ArrayList<>();
        for (User user : users) {
            results.add(userMapper.toUserResponse(user));
        }
        return results;
    }

    @Override
    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String userId) {
        log.info("in getUser method");
        return userMapper.toUserResponse(userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    @Override
    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }


}
