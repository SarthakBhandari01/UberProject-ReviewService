package com.example.UberReviewService.repositories;

import com.example.UberReviewService.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Integer countAllByRatingIsLessThanEqual(Double givenRating);

    List<Review> findAllByRatingIsLessThanEqual(Double givenRating);

    List<Review> findAllByCreatedAtBefore(Date date);

    @Query("select r from Booking b join b.review r where b.id = :bookingId")
    Optional<Review> findReviewByBookingId(Long bookingId);


}