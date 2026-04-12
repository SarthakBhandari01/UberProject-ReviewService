package com.example.UberReviewService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReviewDto {
    private String content;
    private Double rating;
    private Long bookingId;

    @Override
    public String toString() {
        return "CreateReviewDto{" +
                "content='" + content + '\'' +
                ", rating=" + rating +
                ", bookingId=" + bookingId +
                '}';
    }
}

