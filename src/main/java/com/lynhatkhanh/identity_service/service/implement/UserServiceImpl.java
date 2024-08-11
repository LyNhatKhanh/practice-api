package com.lynhatkhanh.identity_service.service.implement;

import com.lynhatkhanh.identity_service.dto.request.UserCreationRequest;
import com.lynhatkhanh.identity_service.dto.request.UserUpdateRequest;
import com.lynhatkhanh.identity_service.entity.User;
import com.lynhatkhanh.identity_service.exception.AppException;
import com.lynhatkhanh.identity_service.exception.ErrorCode;
import com.lynhatkhanh.identity_service.repository.UserRepository;
import com.lynhatkhanh.identity_service.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request) {
        User newUser = new User();

        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        newUser.setUsername(request.getUsername());
        newUser.setPassword(request.getPassword());
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setDob(request.getDob());

        return userRepository.save(newUser);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public User updateUser(String userId, UserUpdateRequest request) {

        User user = getUser(userId);

        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }


}
