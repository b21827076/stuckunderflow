package com.project.stuckunderflow.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthResponse {
    String message;
    Long userId;
    String accessToken;
    String refreshToken;

}
