package com.example.UberReviewService.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseModel {

    @Id // this annotation makes the id property a primary key of our table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identity means auto increment
    private Long id;

    @Column(nullable = false, updatable = false)
//    @Temporal(TemporalType.TIMESTAMP) // this annotation tells spring about the formats of Date object to be stored i.e. Date / Time ? Timestamp
    @CreatedDate // this annotation tells spring that only handle it for object creation
    private LocalDateTime createdAt;

    @Column
//    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate // this annotation tells spring that only handle it for object update
    private LocalDateTime  updatedAt;
}
