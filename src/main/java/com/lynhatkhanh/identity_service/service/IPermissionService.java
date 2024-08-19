package com.lynhatkhanh.identity_service.service;

import com.lynhatkhanh.identity_service.dto.request.PermissionRequest;
import com.lynhatkhanh.identity_service.dto.response.PermissionResponse;

import java.util.List;

public interface IPermissionService {

    PermissionResponse create(PermissionRequest request);

    List<PermissionResponse> getAll();

    void deleteById(String id);
}
