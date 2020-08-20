package com.hsbc.digital.social.service;

import com.hsbc.digital.social.domain.FollowEntity;
import com.hsbc.digital.social.repository.FollowRepository;
import com.hsbc.digital.social.security.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
class DefaultFollowService implements FollowService {

    private final FollowRepository followRepository;

    @Override
    public void follow(@NonNull final User user) {
        var followEntity = new FollowEntity();
        followEntity.setUsername(user.getUsername());
        followEntity.setFollower(SecurityUtils.currentUsername());
        followRepository.save(followEntity);
    }

}
