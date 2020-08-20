package com.hsbc.digital.social.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;

import static javax.persistence.GenerationType.AUTO;

@Entity(name = "FOLLOW")
@Getter
@Setter
public class FollowEntity {
    @Id
    @GeneratedValue(strategy= AUTO)
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    @CreatedBy
    private String follower;
}
