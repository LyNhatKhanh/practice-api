package com.lynhatkhanh.identity_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.lynhatkhanh.identity_service.dto.request.PermissionRequest;
import com.lynhatkhanh.identity_service.dto.response.PermissionResponse;
import com.lynhatkhanh.identity_service.entity.Permission;

// declare to provide Dependency injection => @Autowired
@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);

    void updatePermission(@MappingTarget Permission permission, PermissionRequest request);
}
