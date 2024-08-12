package com.lynhatkhanh.identity_service.dto.response;

import com.lynhatkhanh.identity_service.dto.request.AuthenticationRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {

    boolean authenticated;

}
