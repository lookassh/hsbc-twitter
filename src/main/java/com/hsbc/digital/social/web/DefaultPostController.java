package com.hsbc.digital.social.web;

import com.hsbc.digital.social.service.NewPostEntry;
import com.hsbc.digital.social.service.PostService;
import com.hsbc.digital.social.web.api.PostController;
import com.hsbc.digital.social.web.api.model.NewPost;
import com.hsbc.digital.social.web.api.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@AllArgsConstructor
@Controller
public class DefaultPostController implements PostController {

    private final PostService postService;
    private final PostResponseMapper responseMapper;

    @Override
    public ResponseEntity<Post> createNewPost(NewPost newPost) {
        return ok(responseMapper
                .mapToDto(postService.addPost(new NewPostEntry(newPost.getTitle(), newPost.getBody()))));
    }

}
