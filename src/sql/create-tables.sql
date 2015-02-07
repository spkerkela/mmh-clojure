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

CREATE TABLE reviews(
  id SERIAL PRIMARY KEY,
  movie_id INTEGER NOT NULL ,
  user_id INTEGER NOT NULL ,
  rating INTEGER NOT NULL CONSTRAINT rating_bounds CHECK (rating > 0 AND rating <= 10),
  review_text TEXT NOT NULL ,
  created_at TIMESTAMP NOT NULL ,
  updated_at TIMESTAMP,
  CONSTRAINT user_exists FOREIGN KEY (user_id) REFERENCES users(id)
  ON DELETE CASCADE ON UPDATE CASCADE ,
  CONSTRAINT movie_exists FOREIGN KEY (movie_id) REFERENCES movies(id)
  ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT one_review_per_user_and_movie UNIQUE (movie_id, user_id)
);

CREATE TABLE users(
  id SERIAL PRIMARY KEY,
  username VARCHAR(255) UNIQUE ,
  email VARCHAR(255) NOT NULL UNIQUE ,
  password_digest VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP,
  last_login TIMESTAMP
);

CREATE TABLE marathons(
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE,
  name VARCHAR(255),
  description TEXT
);

CREATE TABLE marathon_movies(
  marathon_id INTEGER NOT NULL,
  movie_id INTEGER NOT NULL,
  CONSTRAINT user_exists FOREIGN KEY (movie_id) REFERENCES movies(id)
  ON DELETE CASCADE ON UPDATE CASCADE ,
  CONSTRAINT movie_exists FOREIGN KEY (marathon_id) REFERENCES marathons(id)
  ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT same_movie_once_per_marathon UNIQUE (marathon_id, movie_id)
);

CREATE TABLE marathon_participants(
  marathon_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  CONSTRAINT user_exists FOREIGN KEY (user_id) REFERENCES users(id)
  ON DELETE CASCADE ON UPDATE CASCADE ,
  CONSTRAINT movie_exists FOREIGN KEY (marathon_id) REFERENCES marathons(id)
  ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT same_user_once_per_marathon UNIQUE (marathon_id, user_id)
);