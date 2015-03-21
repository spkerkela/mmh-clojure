(ns mmh_client.views.main
  (:require [reagent.core :as reagent :refer [atom]]
            [mmh_client.ajax :as ajax]))

(defonce search-text (atom ""))

(defn navbar-links []
  [:ul.nav.navbar-nav [:li [:a {:href "#/movies"} "Movies"]]
   [:li [:a {:href "#/users"} "Users"]]
   [:li [:a {:href "#/marathons"} "Marathons"]]])

(defn movie-search []
  [:form.navbar-form.navbar-left {:role "search"}
   [:div.form-group [:input.form-control {:type "text"
                                          :placeholder "Title, actor, IMDB id..."
                                          :value @search-text
                                          :on-change #(reset! search-text (-> % .-target .-value))}]
    [:button.btn.btn-default {:on-click search-movie} "Search"]]])

(defn search-movie []
  (let [url (str "http://www.omdbapi.com/?s=" @search-text)]
    (println url)
    (ajax/get-data-ext url (fn [res]
                             (println res)))))

(defn navbar []
  [:nav.navbar.navbar-default [:div.container-fluid [:div.navbar-header [:a.navbar-brand {:href "#/"} "Movie Marathon Helper"]]
                               [navbar-links]
                               [movie-search]]])

(defn default-layout [content]
  [:div [navbar]
   [:div.container content]])

(defn landing-page []
  [:div [:h1 "Movie Marathon Helper"]
   [:p "Welcome to the Movie Marathon Helper!"]])