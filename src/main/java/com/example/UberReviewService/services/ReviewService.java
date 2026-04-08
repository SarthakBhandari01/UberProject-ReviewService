package com.example.UberReviewService.services;

import com.example.UberReviewService.models.Booking;
import com.example.UberReviewService.models.BookingStatus;
import com.example.UberReviewService.models.Driver;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.BookingRepository;
import com.example.UberReviewService.repositories.DriverRepository;
import com.example.UberReviewService.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService  implements CommandLineRunner {

    private  final ReviewRepository reviewRepository;

    private final BookingRepository bookingRepository;

    private final DriverRepository driverRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         BookingRepository bookingRepository,
                         DriverRepository driverRepository
                         ){
        this.reviewRepository = reviewRepository;
        this.bookingRepository=bookingRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public void run(String... args){
        System.out.println("**************");

        /*
        Review r = Review.builder()
                .content("Amazing ride quality")
                .rating(4.0)
                .build();

        Booking b = Booking.builder()
                .review(r)
                .endTime(LocalDateTime.now())
                .build();


        bookingRepository.save(b);

        List<Review> reviews = reviewRepository.findAll();
        for(Review review : reviews){
            System.out.println(review.getContent());
        }

        reviewRepository.deleteById(2L);

         */

        /*

        Optional<Booking> b =bookingRepository.findById(2L);

        if(b.isPresent()){
            bookingRepository.delete(b.get());
        }
        b.ifPresent(bookingRepository::delete);

         */


        /*
        Optional<Driver> driver = driverRepository.findByIdAndLicenseNumber(1L,"UK074141");

        driver.ifPresent(value -> {
            System.out.println(value.getId());
            List<Booking> bookings = bookingRepository.findAllByDriverId(1L);
            for(Booking booking :bookings){
                System.out.println(booking.getDriver()+" "+booking.getId() + " " + booking.getReview().getContent());
            }
        });

         */

        Optional<Driver> driver = driverRepository.findById(1L); // FetchType is EAGER

        driver.ifPresent(value -> System.out.println(value.getId()));

    }
}
