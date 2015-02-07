-- name: reviews-by-user-id
-- Return all reviews by given user
SELECT * FROM reviews
WHERE user_id=:user_id;

-- name: reviews-by-movie-id
-- Return all reviews of the given movie
SELECT * FROM reviews
WHERE movie_id=:movie_id;

-- name: update-review!
-- Update the review
UPDATE reviews
SET
  rating=:rating,
  review_text=:review_text,
  updated_at=NOW()
WHERE movie_id=:movie_id AND user_id=:user_id;

-- name: all-reviews
-- Return all reviews
SELECT * FROM reviews;