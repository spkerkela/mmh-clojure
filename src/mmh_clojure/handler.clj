(ns mmh-clojure.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [clojure.data.json :as json]
            [clojure.java.io :as io]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [mmh-clojure.db :as db]))

(extend-type java.sql.Timestamp
  json/JSONWriter
  (-write [date out]
    (json/-write (str date) out)))

(defroutes app-routes
  (GET "/" [] (io/resource "public/index.html"))
  (context "/movies" []
    (GET "/" [] (json/write-str (db/get-movies)))
    (GET "/:id{[0-9]+}" [id] (json/write-str (db/get-movie (Integer/parseInt id))))
    (GET "/:id{[0-9]+}/reviews" [id] (json/write-str (db/reviews-of-movie (Integer/parseInt id))))
    (POST "/:id{[0-9]+}" [id] (str "Create movie " id))
    (PUT "/:id{[0-9]+}" [id] (str "Update movie " id))
    (DELETE "/:id{[0-9]+}" [id] (str "Delete movie " id)))
  (context "/users" []
    (GET "/" [] (json/write-str (db/get-users)))
    (GET "/:id{[0-9]+}" [id] (json/write-str (db/get-user (Integer/parseInt id))))
    (GET "/:id{[0-9]+}/reviews" [id] (json/write-str (db/reviews-by-user (Integer/parseInt id))))
    (POST "/:id{[0-9]+}" [id] (str "Create user " id))
    (PUT "/:id{[0-9]+}" [id] (str "Update user " id))
    (DELETE "/:id{[0-9]+}" [id] (str "Delete user " id)))
  (context "/marathons" []
    (GET "/" [] "All marathons")
    (GET "/:id{[0-9]+}" [id] (str "Marathon " id))
    (POST "/:id{[0-9]+}" [id] (str "Create marathon " id))
    (PUT "/:id{[0-9]+}" [id] (str "Update marathon " id))
    (DELETE "/:id{[0-9]+}" [id] (str "Delete marathon " id)))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> app-routes
    (wrap-defaults site-defaults)))
