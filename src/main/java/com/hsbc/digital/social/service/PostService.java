package com.hsbc.digital.social.service;

import com.hsbc.digital.social.domain.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    PostEntity addPost(NewPostEntry newPostEntry);
    Page<PostEntity> getTimelinePosts(Pageable pageable);
    Page<PostEntity> getWallPosts(Pageable pageable);

}
