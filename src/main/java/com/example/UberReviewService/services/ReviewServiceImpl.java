package com.example.UberReviewService.services;

import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl  implements ReviewService{

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<Review> findReviewById(Long id){
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> findAllReviews(){
        return reviewRepository.findAll();
    }

    @Override
    public void deleteReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        reviewRepository.delete(review);
    }

    @Override
    public Review publishReview (Review review){
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long id, Review newReviewData){
       Review existingReview = reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);

       // Update fields
        if(existingReview.getRating() != null)
            existingReview.setRating(newReviewData.getRating());

        if(existingReview.getContent()!=null)
            existingReview.setContent(newReviewData.getContent());

        return reviewRepository.save(existingReview); // it returns the saved entity
    }
}

