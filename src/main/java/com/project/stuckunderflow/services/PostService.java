package com.project.stuckunderflow.services;

import com.project.stuckunderflow.entities.Like;
import com.project.stuckunderflow.entities.Post;
import com.project.stuckunderflow.entities.User;
import com.project.stuckunderflow.repos.LikeRepository;
import com.project.stuckunderflow.repos.PostRepository;
import com.project.stuckunderflow.requests.PostCreateRequest;
import com.project.stuckunderflow.requests.PostUpdateRequest;
import com.project.stuckunderflow.responses.LikeResponse;
import com.project.stuckunderflow.responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserService userService;
    private LikeService likeService;

    public PostService(PostRepository postRepository, UserService userService,LikeService likeService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.likeService = likeService;
    }
    @Autowired
    public void setLikeService(LikeService likeService){
        this.likeService = likeService;
    }
    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if(userId.isPresent()){
            list = postRepository.findByUserId(userId.get());
        } else {
            list = postRepository.findAll();
        }
        return list.stream().map(p -> {
            List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.empty(), Optional.of(p.getId()));
            return new PostResponse(p, likes);}).collect(Collectors.toList());
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userService.getOneUserById(newPostRequest.getUserId());
        if(user == null){
            return null;
        }else{
            Post toSave = new Post();
            toSave.setId(newPostRequest.getId());
            toSave.setText(newPostRequest.getText());
            toSave.setTitle(newPostRequest.getTitle());
            toSave.setUser(user);
            return postRepository.save(toSave);
        }

    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setText(updatePost.getText());
            toUpdate.setTitle(updatePost.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }
}
