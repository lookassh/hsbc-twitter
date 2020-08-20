package com.hsbc.digital.social.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.time.Instant;

import static javax.persistence.GenerationType.AUTO;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "POST")
public class PostEntity {
    @Id
    @GeneratedValue(strategy= AUTO)
    private Long id;
    @CreatedBy
    private String createdBy;
    @CreatedDate
    private Instant createdAt;
    @NotBlank
    @Size(max = 30)
    private String title;
    @NotBlank
    @Size(max = 140)
    private String body;

}
