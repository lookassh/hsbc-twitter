package com.hsbc.digital.social.web;

import com.hsbc.digital.social.service.FollowService;
import com.hsbc.digital.social.service.PostService;
import com.hsbc.digital.social.service.User;
import com.hsbc.digital.social.web.api.FollowController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import static org.springframework.http.ResponseEntity.ok;

@AllArgsConstructor
@Controller
public class DefaultFollowController implements FollowController {

    private final FollowService followService;

    @Override
    public ResponseEntity updateFollow(String username) {
        followService.follow(User.of(username));
        return ok().build();
    }
}
