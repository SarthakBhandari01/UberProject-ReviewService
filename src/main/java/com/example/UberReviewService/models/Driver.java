package com.example.UberReviewService.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends BaseModel {

    private String name;

    private String phoneNumber;

    @Column(nullable=false , unique = true)
    private String licenseNumber;

    // 1 : n , Driver : Booking
    @OneToMany(mappedBy = "driver")
//    @JsonIgnore
    private List<Booking> bookings ;
}