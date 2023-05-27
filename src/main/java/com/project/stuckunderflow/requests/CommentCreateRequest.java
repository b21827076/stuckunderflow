package com.project.stuckunderflow.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CommentCreateRequest {

    Long id;
    Long userId;
    Long postId;
    String text;
}
