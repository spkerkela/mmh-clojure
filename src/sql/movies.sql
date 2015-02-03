-- name: all-movies
-- Return all movies from database
SELECT * FROM movies;

-- name: create-movie!
-- Create a new movie
INSERT INTO movies(title, rating, released, genre, director, writer, actor, plot, imdb_rating, imdb_votes, imdb_id, poster_url, created_at, updated_at)
VALUES (
  :title,
  :rating,
  :released,
  :genre,
  :director,
  :writer,
  :actor,
  :plot,
  :imdb_rating,
  :imdb_votes,
  :imdb_id,
  :poster_url,
  NOW(),
  NOW()
);

-- name: delete-movie!
-- Delete movie with the given id
DELETE FROM movies
WHERE id=:id;

-- name: get-movie-by-id
-- Find movie with the given id
SELECT * FROM movies
WHERE id=:id;

-- name: update-movie!
-- Update the movie with the given params
UPDATE movies
SET
  title=:title,
  rating=:rating,
  released=:released,
  genre=:genre,
  director=:director,
  writer=:writer,
  actor=:actor,
  plot=:plot,
  imdb_rating=:imdb_rating,
  imdb_votes=:imdb_votes,
  imdb_id=:imdb_id,
  poster_url=:poster_url,
  updated_at=NOW()
WHERE id=:id;