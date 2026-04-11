package com.example.UberReviewService.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;
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

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Booking booking;  // we have defined a 1:1 relationship between booking and review

    @Column(nullable = false)
    private String  content;

    private Double rating;

    @Override
    public String toString(){
        return "Review: " + this.content + " " + this.rating + " " + " booking: " + this.booking.getId() + " " + this.createdAt;
    }

}
