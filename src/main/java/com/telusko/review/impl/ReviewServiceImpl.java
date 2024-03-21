package com.telusko.review.impl;

import com.telusko.review.model.Review;
import com.telusko.review.repo.ReviewRepository;
import com.telusko.review.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;


    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;

    }


    @Override
    public List<Review> getAllReviews(Integer CompanyId) {
        return reviewRepository.findByCompanyId(CompanyId);
    }


    @Override
    public boolean addReview(Integer companyId, Review review) {

        if (companyId != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        } else return false;
    }

    @Override
    public Review getReviewById(Integer reviewId) {
    return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public Review updateReviewById(Review review, Integer reviewId) {
        Review oldReview = reviewRepository.findById(reviewId).orElse(null);
        if (oldReview != null) {
            //now update
            oldReview.setDescription(review.getDescription());
            oldReview.setTitle(review.getTitle());
            oldReview.setRating(review.getRating());

            // Returning the updated (and saved) review
            return reviewRepository.save(oldReview);
        }

        return null;

    }

    @Override
    public boolean deleteReviewById(Integer reviewId) {
        Review review=reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }


}
