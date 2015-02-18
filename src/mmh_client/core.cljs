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

(defonce movie-state (atom {}))
(defonce user-state (atom {}))
(defonce marathon-state (atom {}))

(defn load-movie-data []
  (ajax/get-data "json/movies"
    (fn [res]
      (let [newstate (ajax/read res)]
        (reset! movie-state newstate)))))

(defn load-user-data []
  (ajax/get-data "json/users/"
    (fn [res]
      (let [newstate (ajax/read res)]
        (reset! user-state newstate)))))

(defn load-marathon-data []
  (ajax/get-data "json/marathons/"
    (fn [res]
      (let [newstate (ajax/read res)]
        (reset! marathon-state newstate)))))

(defn load-data []
  (load-movie-data)
  (load-user-data)
  (load-marathon-data))

;(js/setInterval foo 10000)

(defn simple-component []
  [main-views/default-layout
   [:div [:h1 "Movie Marathon Helper"]
    [:p "Welcome to the Movie Marathon Helper!"]]])

(defn movies-component []
  [main-views/default-layout
   [movie-views/movies-view movie-state]])

(defn users-component []
  [main-views/default-layout
   [user-views/users-view user-state]])

(defn user-component [id]
  (do (println id)
    [main-views/default-layout
     [movie-views/movies-view movie-state]]))

(defn marathons-component []
  [main-views/default-layout
   [marathon-views/marathons-view marathon-state]])

(secretary/defroute "/" []
  (session/put! :current-page simple-component))

(secretary/defroute "/movies" []
  (session/put! :current-page movies-component))

(secretary/defroute "/users" []
  (session/put! :current-page users-component))

(secretary/defroute #"/users/(\d+)" [id]
  (session/put! :current-page #(user-component id)))

(secretary/defroute "/marathons" []
  (session/put! :current-page marathons-component))


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

(defn ^:export init! []
  (load-data)
  (hook-browser-navigation!)
  (render))

(defn ^:export run []
  (init!))

(fw/start {:on-jsload (fn [] (render))})

(init!)