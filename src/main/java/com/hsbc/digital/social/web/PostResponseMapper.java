package com.hsbc.digital.social.web;

import com.hsbc.digital.social.domain.PostEntity;
import com.hsbc.digital.social.web.api.model.PageInfo;
import com.hsbc.digital.social.web.api.model.Post;
import com.hsbc.digital.social.web.api.model.PostResponse;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Optional;

@Component
public class PostResponseMapper {

    public PostResponse mapPage(final @NonNull Page<PostEntity> page){
        var postResponse = new PostResponse();
        postResponse.setData(page.map(this::mapToDto).getContent());
        var pageInfo = new PageInfo(
                (long)page.getNumber(),
                (long)page.getSize(),
                page.getTotalElements());
        postResponse.setPageInfo(pageInfo);
        return postResponse;
    }

    public Post mapToDto(final @NonNull PostEntity post){
        var dto = new Post();
        dto.setId(post.getId());
        dto.setCreatedAt(Date.from(post.getCreatedAt()));
        dto.setCreatedBy(post.getCreatedBy());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        return dto;
    }

}
