package com.project.stuckunderflow.services;

import com.project.stuckunderflow.entities.Comment;
import com.project.stuckunderflow.entities.Post;
import com.project.stuckunderflow.entities.User;
import com.project.stuckunderflow.repos.CommentRepository;
import com.project.stuckunderflow.requests.CommentCreateRequest;
import com.project.stuckunderflow.requests.CommentUpdateRequest;
import com.project.stuckunderflow.responses.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@EnableAutoConfiguration
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;
    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.userService = userService;
    }

    public List<CommentResponse> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Comment> comments;
        if(userId.isPresent() && postId.isPresent()){
            comments = commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        } else if (userId.isPresent()) {
            comments = commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            comments =  commentRepository.findByPostId(postId.get());
        }else{
            comments =  commentRepository.findAll();
        }
        return comments.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest request) {
        User user = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if(user!= null && post != null){
            Comment commentToSave = new Comment();
            commentToSave.setId(request.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(request.getText());
            commentToSave.setCreateDate(new Date());
            return commentRepository.save(commentToSave);
        }else{
            return null;
        }
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest request) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(request.getText());
            return commentRepository.save(commentToUpdate);
        }else{
            return null;
        }
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
