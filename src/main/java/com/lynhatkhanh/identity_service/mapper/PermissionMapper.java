package com.lynhatkhanh.identity_service.mapper;

import com.lynhatkhanh.identity_service.dto.request.PermissionRequest;
import com.lynhatkhanh.identity_service.dto.response.PermissionResponse;
import com.lynhatkhanh.identity_service.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

// declare to provide Dependency injection => @Autowired
@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);

    void updatePermission(@MappingTarget Permission permission, PermissionRequest request);
}
