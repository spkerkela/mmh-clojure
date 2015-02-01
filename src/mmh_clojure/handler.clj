(ns mmh-clojure.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] (resp/redirect "index.html"))
  (context "/movies" []
    (GET "/" [] "All movies")
    (GET "/:id{[0-9]+}" [id] (str "Movie " id))
    (POST "/:id{[0-9]+}" [id] (str "Create movie " id))
    (PUT "/:id{[0-9]+}" [id] (str "Update movie " id))
    (DELETE "/:id{[0-9]+}" [id] (str "Delete movie " id)))
  (context "/users" []
    (GET "/" [] "All users")
    (GET "/:id{[0-9]+}" [id] (str "User " id))
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
  (wrap-defaults app-routes site-defaults))
