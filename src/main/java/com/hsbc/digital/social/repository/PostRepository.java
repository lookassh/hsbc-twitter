package com.hsbc.digital.social.repository;

import com.hsbc.digital.social.domain.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Query("SELECT p FROM POST p WHERE p.createdBy = :username ORDER BY p.createdAt DESC ")
    Page<PostEntity> findWall(@Param("username") String username, Pageable pageable);

    @Query("SELECT p FROM POST p " +
            " WHERE p.createdBy IN(select distinct f.username from FOLLOW f where f.follower=:username) " +
            " ORDER BY p.createdAt DESC ")
    Page<PostEntity> findTimeline(@Param("username") String username, Pageable pageable);
}
