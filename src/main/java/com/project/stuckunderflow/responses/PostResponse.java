package com.project.stuckunderflow.responses;

import com.project.stuckunderflow.entities.Like;
import com.project.stuckunderflow.entities.Post;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class PostResponse {
    Long id;
    Long userId;
    String userName;
    String title;
    String text;
    List<LikeResponse> postLikes;
    public PostResponse(Post entity, List<LikeResponse> likes){
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.postLikes = likes;
    }
}
