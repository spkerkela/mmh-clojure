-- name: all-marathons
-- Get all marathons
SELECT * from marathons;

-- name: marathon-participants
-- Get all users that are participating in the given marathon
SELECT * FROM users
  INNER JOIN marathon_participants
  ON users.id = marathon_participants.user_id AND marathon_id=:marathon_id;

-- name: marathon-movies
-- Get all movies that are a part of the given marathon
SELECT * FROM movies
  INNER JOIN marathon_movies
  ON movies.id = marathon_movies.movie_id AND marathon_id=:marathon_id;
