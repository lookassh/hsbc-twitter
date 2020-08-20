package com.hsbc.digital.social.service;

import com.hsbc.digital.social.domain.PostEntity;
import com.hsbc.digital.social.repository.PostRepository;
import com.hsbc.digital.social.security.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Slf4j
@AllArgsConstructor
@Service
class DefaultPostService implements PostService{

    private final PostRepository postRepository;

    public PostEntity addPost(@NonNull final NewPostEntry newPostEntry){
        var postEntity = new PostEntity();
        postEntity.setTitle(newPostEntry.getTitle());
        postEntity.setBody(newPostEntry.getBody());
        postRepository.save(postEntity);
        log.debug("Successfully stored post entity");
        return postEntity;
    }

    public Page<PostEntity> getWallPosts(@NonNull final Pageable pageable){
        return postRepository.findWall(SecurityUtils.currentUsername(), pageable);
    }

    public Page<PostEntity> getTimelinePosts(@NonNull final Pageable pageable){
        return postRepository.findTimeline(SecurityUtils.currentUsername(), pageable);
    }

}
