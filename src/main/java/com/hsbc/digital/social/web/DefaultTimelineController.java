package com.hsbc.digital.social.web;

import com.hsbc.digital.social.service.PostService;
import com.hsbc.digital.social.web.api.FollowController;
import com.hsbc.digital.social.web.api.PostController;
import com.hsbc.digital.social.web.api.TimelineController;
import com.hsbc.digital.social.web.api.WallController;
import com.hsbc.digital.social.web.api.model.PostResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import static org.springframework.http.ResponseEntity.ok;

@AllArgsConstructor
@Controller
public class DefaultTimelineController implements TimelineController {

    private final PostService postService;
    private final PostResponseMapper responseMapper;

    @Override
    public ResponseEntity<PostResponse> getPostResponse(Long page, Long size) {
        return ok(responseMapper
                .mapPage(postService.getTimelinePosts(PageRequest.of(page.intValue(), size.intValue()))));
    }

}
