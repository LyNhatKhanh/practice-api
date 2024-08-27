package com.lynhatkhanh.identity_service.controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lynhatkhanh.identity_service.dto.request.AuthenticationRequest;
import com.lynhatkhanh.identity_service.dto.request.IntrospectRequest;
import com.lynhatkhanh.identity_service.dto.request.LogoutRequest;
import com.lynhatkhanh.identity_service.dto.request.RefreshRequest;
import com.lynhatkhanh.identity_service.dto.response.ApiResponse;
import com.lynhatkhanh.identity_service.dto.response.AuthenticationResponse;
import com.lynhatkhanh.identity_service.dto.response.IntrospectResponse;
import com.lynhatkhanh.identity_service.service.implement.AuthenticationService;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse result = authenticationService.authenticated(request);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authentication(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        IntrospectResponse result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder().result(result).build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> authentication(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/refreshToken")
    ApiResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        AuthenticationResponse result = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }
}
