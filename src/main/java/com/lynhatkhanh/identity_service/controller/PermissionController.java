package com.lynhatkhanh.identity_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.lynhatkhanh.identity_service.dto.request.PermissionRequest;
import com.lynhatkhanh.identity_service.dto.response.ApiResponse;
import com.lynhatkhanh.identity_service.dto.response.PermissionResponse;
import com.lynhatkhanh.identity_service.service.IPermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {

    IPermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{permissionId}")
    ApiResponse<Void> delete(@PathVariable("permissionId") String permissionId) {
        permissionService.deleteById(permissionId);
        return ApiResponse.<Void>builder().message("Delete complete!").build();
    }
}
