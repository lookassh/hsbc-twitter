package com.hsbc.digital.social.repository;

import com.hsbc.digital.social.domain.FollowEntity;
import com.hsbc.digital.social.domain.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, Long> {

}
