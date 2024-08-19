package com.lynhatkhanh.identity_service.controller;

import com.lynhatkhanh.identity_service.dto.request.RoleRequest;
import com.lynhatkhanh.identity_service.dto.response.ApiResponse;
import com.lynhatkhanh.identity_service.dto.response.RoleResponse;
import com.lynhatkhanh.identity_service.service.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {

    IRoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{roleId}")
    ApiResponse delete(@PathVariable("roleId") String roleId) {
        roleService.deleteById(roleId);
        return ApiResponse.builder()
                .message("Delete Complete!")
                .build();
    }

}
