(ns mmh_client.views.marathons)

(defn marathon-view [marathon-state]
  (let [marathon (:marathon @marathon-state)
        movies (:movies @marathon-state)
        participants (:participants @marathon-state)]
    [:div [:h3 (:name marathon)]
     [:p (:description marathon)]
     [:p "Participants: " (count participants)]
     [:p "Movies: " (count movies)]
     [:ul.list-unstyled
      (for [movie movies]
        [:li {:key (:id movie)} 
         [:a {:href (str "#/movies/" (:id movie))} (:title movie)]])]]))

(defn marathon-component [marathon]
  [:div [:h3 [:a {:href (str "#/marathons/" (:id marathon))} (:name marathon)]]
   [:p (:description marathon)]])

(defn marathons-view [marathons-state]
  [:div [:h1 "Marathons"]
   [:ul.list-unstyled
    (for [marathon @marathons-state]
      [:li {:key (:id marathon)} [marathon-component marathon]])]])