package com.project.stuckunderflow.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CommentUpdateRequest {
    String text;
}
