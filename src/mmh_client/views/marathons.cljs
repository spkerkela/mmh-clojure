(ns mmh_client.views.marathons)

(defn marathon-component [marathon]
  [:div [:h3 [:a {:href (str "/json/marathons/" (:id marathon))} (:name marathon)]]
   [:p (:description marathon)]])

(defn marathons-view [marathons-state]
  [:div [:h1 "Marathons"]
   [:ul.list-unstyled
    (for [marathon @marathons-state]
      [:li {:key (:id marathon)} [marathon-component marathon]])]])