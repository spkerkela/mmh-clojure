(ns mmh-clojure.db
  (:require [yesql.core :refer [defqueries]]))

(def db-spec (atom {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname "//localhost:5432/mmh"
              :user "spkerkela"}))

(defqueries "sql/movies.sql")

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