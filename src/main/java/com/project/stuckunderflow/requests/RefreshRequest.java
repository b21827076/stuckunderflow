package com.project.stuckunderflow.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RefreshRequest {
    Long userId;
    String refreshToken;
}
