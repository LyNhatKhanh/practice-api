package com.lynhatkhanh.identity_service.service.implement;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lynhatkhanh.identity_service.dto.request.RoleRequest;
import com.lynhatkhanh.identity_service.dto.response.RoleResponse;
import com.lynhatkhanh.identity_service.entity.Permission;
import com.lynhatkhanh.identity_service.entity.Role;
import com.lynhatkhanh.identity_service.mapper.RoleMapper;
import com.lynhatkhanh.identity_service.repository.PermissionRepository;
import com.lynhatkhanh.identity_service.repository.RoleRepository;
import com.lynhatkhanh.identity_service.service.IRoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleServiceImpl implements IRoleService {

    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    @Override
    public RoleResponse create(RoleRequest request) {
        Role role = roleMapper.toRole(request);
        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }

    @Override
    public List<RoleResponse> getAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleResponse> results =
                roles.stream().map(roleMapper::toRoleResponse).toList();

        return results;
    }

    @Override
    public void deleteById(String roleId) {
        roleRepository.deleteById(roleId);
    }
}
