CREATE TABLE movies(
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  rating VARCHAR(255) NOT NULL,
  released VARCHAR(255) NOT NULL,
  genre VARCHAR(255) NOT NULL,
  director VARCHAR(255) NOT NULL,
  writer VARCHAR(255) NOT NULL,
  actor VARCHAR(255) NOT NULL,
  plot TEXT NOT NULL,
  imdb_rating FLOAT NOT NULL,
  imdb_votes INTEGER NOT NULL,
  imdb_id VARCHAR(255) NOT NULL,
  poster_url VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP
);

CREATE TABLE users(
  id SERIAL PRIMARY KEY,
  username VARCHAR(255),
  email VARCHAR(255) NOT NULL,
  password_digest VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP,
  last_login TIMESTAMP
);