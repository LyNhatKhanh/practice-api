package com.lynhatkhanh.identity_service.service;

import java.util.List;

import com.lynhatkhanh.identity_service.dto.request.PermissionRequest;
import com.lynhatkhanh.identity_service.dto.response.PermissionResponse;

public interface IPermissionService {

    PermissionResponse create(PermissionRequest request);

    List<PermissionResponse> getAll();

    void deleteById(String id);
}
