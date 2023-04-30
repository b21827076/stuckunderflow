package com.project.stuckunderflow.responses;

import com.project.stuckunderflow.entities.Comment;
import lombok.Data;

@Data
public class CommentResponse {
    Long id;
    Long userId;
    String userName;
    String text;

    public CommentResponse(Comment entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.text = entity.getText();
        this.userName = entity.getUser().getUserName();
    }
}
