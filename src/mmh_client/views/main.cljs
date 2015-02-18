(ns mmh_client.views.main)

(defn default-layout [content]
  [:div
   [navbar]
   [:div.container [:div.jumbotron content]]])

(defn landing-page []
  [:div [:h1 "Movie Marathon Helper"]
   [:p "Welcome to the Movie Marathon Helper!"]])

(defn navbar []
  [:nav.navbar.navbar-default
   [:div.container-fluid
    [:div.navbar-header
     [:a.navbar-brand {:href "#/"} "Movie Marathon Helper"]]
    [navbar-links]]])

(defn navbar-links []
  [:ul.nav.navbar-nav
   [:li [:a {:href "#/movies"} "Movies"]]
   [:li [:a {:href "#/users"} "Users"]]
   [:li [:a {:href "#/marathons"} "Marathons"]]])