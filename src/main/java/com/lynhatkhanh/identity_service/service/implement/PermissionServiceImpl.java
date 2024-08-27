package com.lynhatkhanh.identity_service.service.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lynhatkhanh.identity_service.dto.request.PermissionRequest;
import com.lynhatkhanh.identity_service.dto.response.PermissionResponse;
import com.lynhatkhanh.identity_service.entity.Permission;
import com.lynhatkhanh.identity_service.mapper.PermissionMapper;
import com.lynhatkhanh.identity_service.repository.PermissionRepository;
import com.lynhatkhanh.identity_service.service.IPermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionServiceImpl implements IPermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        PermissionResponse response = permissionMapper.toPermissionResponse(permissionRepository.save(permission));

        return response;
    }

    @Override
    public List<PermissionResponse> getAll() {
        List<Permission> permissions = permissionRepository.findAll();

        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    public void deleteById(String id) {
        permissionRepository.deleteById(id);
    }
}
