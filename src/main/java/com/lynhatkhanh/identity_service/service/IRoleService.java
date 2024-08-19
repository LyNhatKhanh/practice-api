package com.lynhatkhanh.identity_service.service;

import com.lynhatkhanh.identity_service.dto.request.RoleRequest;
import com.lynhatkhanh.identity_service.dto.response.RoleResponse;
import com.lynhatkhanh.identity_service.entity.Role;

import java.util.List;

public interface IRoleService {
    RoleResponse create(RoleRequest request);

    List<RoleResponse> getAll();

    void deleteById(String roleId);
}
