(ns my-app.views
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [react-native :as rn]))

(defonce splash-img (js/require "../assets/shadow-cljs.png"))

(defn main-screen []
  (let [current-count (rf/subscribe [:current-count])]
    [:> rn/View {:style {:flex 1 :justify-content :center :align-items :center :backgroundColor "white"}}
     [:> rn/TouchableOpacity {:on-press #(rf/dispatch [:initialize-db])}
      [:> rn/Image {:source splash-img :style {:width 200 :height 200}}]]
     [:> rn/Text {:style {:font-size 24 :margin-bottom 20}}
      (str "Count: " @current-count)]
     [:> rn/Button
      {:title "Increment"
       :on-press #(rf/dispatch [:increment-count])}]
     [:> rn/Button
      {:title "Decrement"
       :on-press #(rf/dispatch [:decrement-count])}]
     [:> rn/Button
      {:title "Show Alert"
       :on-press #(rf/dispatch [:alert-count])}]]))
