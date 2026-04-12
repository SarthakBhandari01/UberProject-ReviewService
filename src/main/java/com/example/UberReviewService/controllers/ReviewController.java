package com.example.UberReviewService.controllers;

import com.example.UberReviewService.adapters.CreateReviewDtoToReviewAdapter;
import com.example.UberReviewService.dtos.CreateReviewDto;
import com.example.UberReviewService.dtos.ReviewDto;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.services.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final CreateReviewDtoToReviewAdapter  createReviewDtoToReviewAdapter;

    public ReviewController(ReviewService reviewService,
                            CreateReviewDtoToReviewAdapter  createReviewDtoToReviewAdapter){
        this.reviewService = reviewService;
        this.createReviewDtoToReviewAdapter = createReviewDtoToReviewAdapter;
    }

    @PostMapping
    public ResponseEntity<?> publishReview(@Validated  @RequestBody CreateReviewDto request){

        System.out.println("***********");
        System.out.println(request);
        System.out.println("***********");
        Review incomingReview = this.createReviewDtoToReviewAdapter.convertDto(request);
        if(incomingReview==null){
            return new ResponseEntity<>("Invalid arguments", HttpStatus.BAD_REQUEST);
        }

        Review savedReview = reviewService.publishReview(incomingReview);

        ReviewDto response = ReviewDto.builder()
                .id(savedReview.getId())
                .content(savedReview.getContent())
                .booking(savedReview.getBooking().getId())
                .rating(savedReview.getRating())
                .createdAt(savedReview.getCreatedAt())
                .updatedAt(savedReview.getUpdatedAt())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
