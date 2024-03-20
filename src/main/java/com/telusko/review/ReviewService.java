package com.telusko.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Integer CompanyId);

    boolean addReview(Integer companyId, Review review);
    Review getReviewById(Integer reviewId);
    Review updateReviewById(Review review, Integer reviewId);
    boolean deleteReviewById(Integer reviewId);

}
