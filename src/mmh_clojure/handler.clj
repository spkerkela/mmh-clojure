(ns mmh-clojure.handler
  (:import [java.io ByteArrayOutputStream])
  (:require [compojure.core :refer :all]
            [clojure.repl :refer [doc]]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [clojure.data.json :as json]
            [clojure.java.io :as io]
            [ring.util.response :refer [response redirect content-type status]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [cognitect.transit :as transit]
            [mmh-clojure.db :as db]))

(extend-type java.sql.Timestamp
  json/JSONWriter
  (-write [date out]
    (json/-write (str date) out)))

(extend-type java.sql.Date
  json/JSONWriter
  (-write [date out]
    (json/-write (str date) out)))

(defn json-resp [data]
  (-> (response
        (let [out (ByteArrayOutputStream. 4096)
              writer (transit/writer out :json)]
          (transit/write writer data)
          (.toString out)))
      (status 200)
      (content-type "application/json")))

(defroutes app-routes
  (GET "/" [] (io/resource "public/views/index.html"))
  (context "/json" []
    (context "/movies" []
      (GET "/" []
        (json-resp (db/get-movies)))
      (GET "/:id{[0-9]+}" [id] (json-resp (db/get-movie (Integer/parseInt id))))
      (GET "/:id{[0-9]+}/reviews" [id] (json-resp (db/reviews-of-movie (Integer/parseInt id))))
      (POST "/:id{[0-9]+}" [id] (str "Create movie " id))
      (PUT "/:id{[0-9]+}" [id] (str "Update movie " id))
      (DELETE "/:id{[0-9]+}" [id] (str "Delete movie " id)))
    (context "/users" []
      (GET "/" [] (json-resp (db/get-users)))
      (GET "/:id{[0-9]+}" [id] (json-resp (db/get-user (Integer/parseInt id))))
      (GET "/:id{[0-9]+}/reviews" [id] (json-resp (db/reviews-by-user (Integer/parseInt id))))
      (GET "/:id{[0-9]+}/followers" [id] (json-resp (db/get-followers (Integer/parseInt id))))
      (GET "/:id{[0-9]+}/following" [id] (json-resp (db/get-followed-by (Integer/parseInt id))))
      (GET "/:id{[0-9]+}/microposts" [id] (json-resp (db/microposts-by-user (Integer/parseInt id))))
      (POST "/:id{[0-9]+}" [id] (str "Create user " id))
      (PUT "/:id{[0-9]+}" [id] (str "Update user " id))
      (DELETE "/:id{[0-9]+}" [id] (str "Delete user " id)))
    (context "/marathons" []
      (GET "/" [] (json-resp (db/get-marathons)))
      (GET "/:id{[0-9]+}" [id] (json-resp (db/get-marathon (Integer/parseInt id))))
      (GET "/:id{[0-9]+}/participants" [id] (json-resp (db/users-in-marathon (Integer/parseInt id))))
      (GET "/:id{[0-9]+}/movies" [id] (json-resp (db/movies-in-marathon (Integer/parseInt id))))
      (POST "/:id{[0-9]+}" [id] (str "Create marathon " id))
      (PUT "/:id{[0-9]+}" [id] (str "Update marathon " id))
      (DELETE "/:id{[0-9]+}" [id] (str "Delete marathon " id)))
    (context "/microposts" []
      (GET "/" [] (json-resp (db/get-microposts)))
      (GET "/:id{[0-9]+}" [id] (json-resp (db/get-micropost (Integer/parseInt id))))
      (POST "/:id{[0-9]+}" [id] (str "Create micropost " id))
      (PUT "/:id{[0-9]+}" [id] (str "Update micropost " id))
      (DELETE "/:id{[0-9]+}" [id] (str "Delete micropost " id))))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> app-routes
    (wrap-defaults site-defaults)))
