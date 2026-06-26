package com.example.UberReviewService;

import com.example.UberEntityService.models.Booking;
import com.example.UberEntityService.models.Review;
import com.example.UberReviewService.adapters.CreateReviewDtoToReviewAdapter;
import com.example.UberReviewService.controllers.ReviewController;
import com.example.UberReviewService.dtos.CreateReviewDto;
import com.example.UberReviewService.dtos.ReviewDto;
import com.example.UberReviewService.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReviewControllerTest {

    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewService reviewService;

    @Mock
    private CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindReviewById_Success(){

        long reviewId = 1L;

        Review mockReview =  Review.builder().build();
        mockReview.setId(reviewId);

        // mocking
        when(reviewService.findReviewById(reviewId)).thenReturn(Optional.of(mockReview));

        //testing
        ResponseEntity<?>response =  reviewController.findReviewById(reviewId);

        //assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Review returnReview = (Review) response.getBody();

        assertEquals(reviewId, returnReview.getId());

    }

    @Test
    public void testPublishReview_Success(){
        Booking booking = Booking.builder().build();

        booking.setId(1L);

        CreateReviewDto requestDto = new CreateReviewDto();
        requestDto.setBookingId(booking.getId());

        Review mockIncomigReview = Review.builder()
                .booking(booking)
                .rating(4.5)
                .content("test review content")
                .build();

        Review mockResponseReview = Review.builder()
                .booking(mockIncomigReview.getBooking())
                        .content(mockIncomigReview.getContent())
                                .rating(mockIncomigReview.getRating())
                                        .build();

        // mocking
        when(createReviewDtoToReviewAdapter.convertDto(requestDto)).thenReturn(mockIncomigReview);

        when(reviewService.publishReview(mockIncomigReview)).thenReturn(mockResponseReview);


        // testing
         ResponseEntity<?> response  = reviewController.publishReview(requestDto);

         assertEquals(HttpStatus.CREATED, response.getStatusCode());

         ReviewDto responseBody =(ReviewDto) response.getBody();

         assertEquals(mockIncomigReview.getRating(),responseBody.getRating());

         assertEquals(mockIncomigReview.getBooking().getId(), responseBody.getBooking());
    }
}
