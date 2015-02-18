(ns mmh_client.views.users)

(defn user-component [user]
  [:div [:h3 [:a {:href (str "/json/users/" (:id user))} (:username user)]]
   [:p (:email user)]])

(defn users-view [user-state]
  [:div [:h1 "Users"]
   [:ul.list-unstyled
    (for [user @user-state]
      [:li {:key (:id user)} [user-component user]])]])