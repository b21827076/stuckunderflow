package com.project.stuckunderflow.requests;

import lombok.Data;

@Data
public class RefreshRequest {
    Long userId;
    String refreshToken;
}
