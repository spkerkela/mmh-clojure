(ns mmh_client.views.users
  (:require [mmh_client.ajax :as ajax]))

(defn user-view [user-state]
  (let [user @user-state]
      (if (nil? user)
        [:div [:h3 "User not found"]]
        [:div [:h3 (:username user)]
         [:p (:email user)]
         [:p "Followers: " (count (:followers user))]
         [:p "Following: " (count (:following user))]])))

(defn user-component [user]
  [:div [:h3 [:a {:href (str "#/users/" (:id user))} (:username user)]]
   [:p (:email user)]])

(defn users-view [users-state]
  [:div [:h1 "Users"]
   [:ul.list-unstyled (for [user @users-state]
                        [:li {:key (:id user)} [user-component user]])]])