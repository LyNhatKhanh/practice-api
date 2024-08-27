package com.lynhatkhanh.identity_service.service;

import java.util.List;

import com.lynhatkhanh.identity_service.dto.request.RoleRequest;
import com.lynhatkhanh.identity_service.dto.response.RoleResponse;

public interface IRoleService {
    RoleResponse create(RoleRequest request);

    List<RoleResponse> getAll();

    void deleteById(String roleId);
}
