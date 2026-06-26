package com.example.UberReviewService.adapters;

import com.example.UberEntityService.models.Review;
import com.example.UberReviewService.dtos.CreateReviewDto;


public interface CreateReviewDtoToReviewAdapter {
    public Review convertDto(CreateReviewDto createReviewDto);
}
