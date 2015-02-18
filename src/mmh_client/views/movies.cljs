(ns mmh_client.views.movies)

(defn movie-component [movie]
  [:div [:h3 [:a {:href (str "/json/movies/" (:id movie))} (:title movie)]]
   [:p (:plot movie)]])

(defn movies-view [movie-state]
  [:div [:h1 "Movies"]
   [:ul.list-unstyled
    (for [movie @movie-state]
      [:li {:key (:id movie)} [movie-component movie]])]])