(ns my-app.views
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [react-native :as rn]))

(defonce splash-img (js/require "../assets/shadow-cljs.png"))

(defn main-screen []
  (let [current-count (rf/subscribe [:current-count])
        name (rf/subscribe [:name])
        local-name (r/atom "")]
    (fn []
      [:> rn/View {:style {:flex 1 :justify-content :center :align-items :center :backgroundColor "white"}}
       [:> rn/TextInput {:style {:font-size 18 :margin-top 10 :margin-bottom 10}
                         :on-change-text #(reset! local-name %)
                         :placeholder "Enter name"
                         :value @local-name}]
       [:> rn/Button
        {:title "Submit Name"
         :on-press (fn [_]
                     (rf/dispatch [:update-name @local-name])
                     (reset! local-name ""))}]
       [:> rn/Text {:style {:font-size 18 :margin-bottom 20 :margin-top 20 :text-align :center}}
        (str "This is a demo re-frame app using react native written by " @name)]
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
         :on-press #(rf/dispatch [:alert-count])}]])))
