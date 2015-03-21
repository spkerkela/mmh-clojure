(ns mmh_client.core
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent.session :as session]
            [figwheel.client :as fw]
            [mmh_client.ajax :as ajax]
            [secretary.core :as secretary :refer-macros [defroute]]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [mmh_client.views.main :as main-views]
            [mmh_client.views.movies :as movie-views]
            [mmh_client.views.users :as user-views]
            [mmh_client.views.marathons :as marathon-views])
  (:import goog.History))

(enable-console-print!)
(secretary/set-config! :prefix "#")

(defn landing-component []
  [main-views/default-layout
   [main-views/landing-page]])

(defn movies-component []
  (let [movies-state (atom {})]
    (ajax/get-data "json/movies"
      (fn [res] (reset! movies-state (ajax/read res))))
    [main-views/default-layout
     [movie-views/movies-view movies-state]]))

(defn movie-component [id]
  (let [movie-state (atom {})]
    (ajax/get-data (str "json/movies/" id)
      (fn [res] (swap! movie-state #(assoc % :movie (ajax/read res)))))
    (ajax/get-data (str "json/movies/" id "/reviews")
      (fn [res] (swap! movie-state #(assoc % :reviews (ajax/read res)))))
    [main-views/default-layout
     [movie-views/movie-view movie-state]]))

(defn users-component []
  (let [users-state (atom {})]
    (ajax/get-data "json/users/"
      (fn [res] (reset! users-state (ajax/read res))))
    [main-views/default-layout
     [user-views/users-view users-state]]))

(defn user-component [id]
  (let [user-state (atom {})
        user-url (str "json/users/" id)]
    (ajax/get-data user-url
      (fn [res] (swap! user-state #(assoc % :user (ajax/read res)))))
    (ajax/get-data (str user-url "/followers")
      (fn [res] (swap! user-state #(assoc % :followers (ajax/read res)))))
    (ajax/get-data (str user-url "/following")
      (fn [res] (swap! user-state #(assoc % :following (ajax/read res)))))
    (ajax/get-data (str user-url "/microposts")
      (fn [res] (swap! user-state #(assoc % :microposts (ajax/read res)))))
    [main-views/default-layout
     [user-views/user-view user-state]]))

(defn marathons-component []
  (let [marathons-state (atom {})]
    (ajax/get-data "json/marathons/"
      (fn [res]
        (reset! marathons-state (ajax/read res))))
    [main-views/default-layout
     [marathon-views/marathons-view marathons-state]]))

(defn marathon-component [id]
  (let [marathon-state (atom {})
        marathon-url (str "json/marathons/" id)]
    (ajax/get-data marathon-url
      (fn [res]
        (swap! marathon-state #(assoc % :marathon (ajax/read res)))))
    (ajax/get-data (str marathon-url "/participants")
      (fn [res]
        (swap! marathon-state #(assoc % :participants (ajax/read res)))))
    (ajax/get-data (str marathon-url "/movies")
      (fn [res]
        (swap! marathon-state #(assoc % :movies (ajax/read res)))))
    [main-views/default-layout
     [marathon-views/marathon-view marathon-state]]))

(secretary/defroute home-path "/" []
  (session/put! :current-page landing-component))

(secretary/defroute movies-path "/movies" []
  (session/put! :current-page movies-component))

(secretary/defroute movie-path #"/movies/(\d+)" [id]
  (session/put! :current-page #(movie-component id)))

(secretary/defroute users-path "/users" []
  (session/put! :current-page users-component))

(secretary/defroute user-path #"/users/(\d+)" [id]
  (session/put! :current-page #(user-component id)))

(secretary/defroute marathons-path "/marathons" []
  (session/put! :current-page marathons-component))

(secretary/defroute marathon-path #"/marathons/(\d+)" [id]
  (session/put! :current-page #(marathon-component id)))


(defn current-page []
  [:div [(session/get :current-page)]])

(defn render []
  (reagent/render-component [current-page] (.getElementById js/document "app")))

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
      EventType/NAVIGATE
      (fn [event]
        (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn init! []
  (hook-browser-navigation!)
  (render))

(fw/start {:on-jsload (fn [] (render))})

(init!)