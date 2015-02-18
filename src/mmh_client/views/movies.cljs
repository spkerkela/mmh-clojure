(ns mmh_client.views.movies)

(defn movie-view [movie-state]
  (let [movie @movie-state
        reviews (:reviews movie)
        marathoner-rating (/
                            (reduce
                               #(+ (:rating %1) (:rating %2)) 0 reviews)
                            (count reviews))]
    (if (nil? movie)
      [:div [:h3 "Movie not found"]]
      [:div [:h3 (:title movie)]
       [:p (:plot movie)]
       [:p "Directors: " (:director movie)]
       [:p "Actors: " (:actor movie)]
       [:p "Rating in IMDB: " (:imdb_rating movie)]
       [:p "Reviews: " (count reviews)]
       (if (not (empty? reviews))
         [:p "Marathoner rating: " marathoner-rating]
         [:p "This movie has no reviews yet"])])))

(defn movie-component [movie]
  [:div [:h3 [:a {:href (str "#/movies/" (:id movie))} (:title movie)]]
   [:p (:plot movie)]])

(defn movies-view [movie-state]
  [:div [:h1 "Movies"]
   [:ul.list-unstyled (for [movie @movie-state]
                        [:li {:key (:id movie)} [movie-component movie]])]])