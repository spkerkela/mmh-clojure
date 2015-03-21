(ns mmh_client.views.users
  (:require [mmh_client.ajax :as ajax]))

(defn microposts-component [microposts]
  [:div (for [mpost microposts]
          [:div.panel.panel-default [:div.panel-body {:key (:id mpost)}
                                     (:content mpost)]
           [:div.panel-footer (str " " (:created_at mpost))]])])

(defn user-view [user-state]
  (let [user (:user @user-state)
        email (:email user)
        username (:username user)
        followers (:followers @user-state)
        following (:following @user-state)
        microposts (:microposts @user-state)]
    (if (nil? user)
      [:div [:h3 "User not found"]]
      [:div.row [:div.col-md-4 [:h3 username]
                 [:p email]
                 [:p "Followers: " (count followers)]
                 [:p "Following: " (count following)]
                 [:p "Microposts: " (count microposts)]]
       [:div.col-md-8 [microposts-component microposts]]])))

(defn user-component [user]
  [:div [:h3 [:a {:href (str "#/users/" (:id user))} (:username user)]]
   [:p (:email user)]])

(defn users-view [users-state]
  [:div [:h1 "Users"]
   [:ul.list-unstyled (for [user @users-state]
                        [:li {:key (:id user)} [user-component user]])]])