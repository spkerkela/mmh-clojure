(ns mmh-clojure.db
  (:require [yesql.core :refer [defqueries]]))

(def db-spec (atom {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname "//localhost:5432/mmh"
              :user (System/getenv "USER")}))

(defqueries "sql/movies.sql")
(defqueries "sql/users.sql")
(defqueries "sql/reviews.sql")
(defqueries "sql/marathons.sql")

;; -- User functions

(defn get-user [id]
  (first (get-user-by-id @db-spec id)))

(defn get-users []
  (all-users @db-spec))

(defn create-user [user]
  (create-user!
    @db-spec
    (:username user)
    (:email user)
    (:password_digest user)))

(defn delete-user [id]
  (delete-user! @db-spec id))

(defn update-user [id user]
  (update-movie! @db-spec
    (:username user)
    (:email user)
    (:password_digest user)
    id))

;; -- Movie functions

(defn get-movie [id]
  (first (get-movie-by-id @db-spec id)))

(defn get-movies []
  (all-movies @db-spec))

(defn create-movie [movie]
  (create-movie!
    @db-spec
    (:title movie)
    (:rating movie)
    (:released movie)
    (:genre movie)
    (:director movie)
    (:writer movie)
    (:actor movie)
    (:plot movie)
    (:imdb_rating movie)
    (:imdb_votes movie)
    (:imdb_id movie)
    (:poster_url movie)))

(defn delete-movie [id]
  (delete-movie! @db-spec id))

(defn update-movie [id movie]
  (update-movie! @db-spec
    (:title movie)
    (:rating movie)
    (:released movie)
    (:genre movie)
    (:director movie)
    (:writer movie)
    (:actor movie)
    (:plot movie)
    (:imdb_rating movie)
    (:imdb_votes movie)
    (:imdb_id movie)
    (:poster_url movie)
    id))

;; -- Review functions

(defn reviews-by-user [user-id]
  (reviews-by-user-id @db-spec user-id))

(defn reviews-of-movie [movie-id]
  (reviews-by-movie-id @db-spec movie-id))

;; -- Marathon functions

(defn marathons []
  (all-marathons @db-spec))

(defn users-in-marathon [marathon-id]
  (marathon-participants @db-spec marathon-id))

(defn movies-in-marathon [marathon-id]
  (marathon-movies @db-spec marathon-id))