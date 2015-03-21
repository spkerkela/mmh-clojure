(ns mmh_client.views.movies)

(defn movie-view [movie-state]
  (let [movie (:movie @movie-state)
        reviews (:reviews @movie-state)
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
         [:div [reviews-component reviews]
          [:p "Marathoner rating: " marathoner-rating]]
         [:p "This movie has no reviews yet"])])))

(defn reviews-component [reviews]
  [:ul (for [review reviews]
         [:li {:key (:id review)} (:review_text review)])])

(defn movie-component [movie]
  [:div [:h3 [:a {:href (str "#/movies/" (:id movie))} (:title movie)]]
   [:p (:plot movie)]])

(defn movies-view [movie-state]
  [:div [:h1 "Movies"]
   [:ul.list-unstyled (for [movie @movie-state]
                        [:li {:key (:id movie)} [movie-component movie]])]])