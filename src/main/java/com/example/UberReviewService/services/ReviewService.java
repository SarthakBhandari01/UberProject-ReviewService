package com.example.UberReviewService.services;

import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService  implements CommandLineRunner {

    private  final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(String... args){
        System.out.println("**************");
//        Review r = Review.builder()
//                .content("Poor ride quality")
//                .rating(4.0)
//                .build();
//
//        reviewRepository.save(r);
//
        List<Review> reviews = reviewRepository.findAll();
//        for(Review review : reviews){
//            System.out.println(review.getContent());
//        }

//        reviewRepository.deleteById(2L);

    }
}
