(ns mmh_client.core
  (:require [reagent.core :as reagent :refer [atom]]
            ;[reagent.session :as session]
            [figwheel.client :as fw]
            [cognitect.transit :as t]
            [mmh_client.ajax :as ajax]
            [secretary.core :as secretary :refer-macros [defroute]])
  (:import [goog.net XhrIo]))

(enable-console-print!)
(secretary/set-config! :prefix "#")
(def movie-state (atom {}))
(def user-state (atom {}))

(ajax/get-data "json/movies"
  (fn [res]
    (let [newstate (ajax/read res)]
      (reset! movie-state newstate))))

(ajax/get-data "json/users/"
  (fn [res]
    (let [newstate (ajax/read res)]
      (reset! user-state newstate))))

(println @user-state)

(defn movie-component [movie]
  [:div [:h3 [:a {:href (str "/json/movies/" (:id movie))} (:title movie)]]
   [:p (:plot movie)]])

(defn simple-component []
  [:div.container [:div.jumbotron [:h1 "Movie Marathon Helper"]
                   [:p "Welcome to the Movie Marathon Helper!"]
                   [:a {:href "#/users/4"} "HEY"]
                   [:ul.list-unstyled (for [movie @movie-state]
                                        [:li {:key (:id movie)} [movie-component movie]])]]])


(secretary/defroute "/users/:id" [id]
  (js/console.log id))

(secretary/dispatch! "/users/4")

(defn render []
  (reagent/render-component [simple-component] (.getElementById js/document "app")))

(defn ^:export run []
  (render))

(fw/start {:on-jsload (fn [] (render))})