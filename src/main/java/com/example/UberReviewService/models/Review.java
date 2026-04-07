package com.example.UberReviewService.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;



@Getter // Automatically prepare all the getter for your class
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "booking_review")
@Inheritance(strategy = InheritanceType.JOINED)
public class Review extends  BaseModel {

    @Column(nullable = false)
    private String  content;

    private Double rating;

}
