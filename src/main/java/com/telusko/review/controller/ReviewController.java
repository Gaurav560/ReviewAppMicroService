package com.telusko.review.controller;

import com.telusko.review.service.ReviewService;
import com.telusko.review.model.Review;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Integer companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody Review review, @RequestParam Integer companyId) {
        if (reviewService.addReview(companyId, review)) {
            return new ResponseEntity<>("Review Added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("COMPANY NOT FOUND SO,Review not Added", HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Integer reviewId) {
        Review review = reviewService.getReviewById(reviewId);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReviewById(@RequestBody Review review, @PathVariable Integer reviewId) {
        Review review1 = reviewService.updateReviewById(review, reviewId);

        if (review1 != null) {
            return new ResponseEntity<>(review1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Integer reviewId) {
        if (reviewService.deleteReviewById(reviewId)) {
            return new ResponseEntity<>("review deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("review not found", HttpStatus.NOT_FOUND);
        }

    }

}
