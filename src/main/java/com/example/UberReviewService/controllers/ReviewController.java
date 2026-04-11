package com.example.UberReviewService.controllers;

import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.services.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> publishReview(@RequestBody Review request){
        Review savedReview = reviewService.publishReview(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(){
        List<Review> reviews = reviewService.findAllReviews();
        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> findReviewById(@PathVariable("reviewId") Long id) {

        Optional<Review> review = reviewService.findReviewById(id);

        if (review.isPresent()) {
            return ResponseEntity.ok(review.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Review not found with id: " + id);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long reviewId) {
        try {
            reviewService.deleteReviewById(reviewId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{reviewId}")
    public  ResponseEntity<?> updateReviewById(@PathVariable Long reviewId,
                                               @RequestBody Review request){
        try{
            Review updatedReview = reviewService.updateReview(reviewId, request);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(updatedReview);
        }catch(EntityNotFoundException e){
            return  ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }

    }
}
