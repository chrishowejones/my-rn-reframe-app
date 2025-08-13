(ns my-app.core
  (:require
   [shadow.expo :as expo]
   [re-frame.core :as rf]
   [reagent.core :as r]
   [my-app.events]
   [my-app.subs]
   [my-app.effects]
   [my-app.views :refer [main-screen]]
   ;; ["expo" :as ex]
   ["react-native" :as rn]
   ;; ["react" :as react]
   ))



(defn cljs-init-fn []
  (rf/dispatch-sync [:initialize-db])
  (println "Re-frame initialized for React Native app!"))

(def styles
  ^js (-> {:container
           {:flex 1
            :backgroundColor "#fff"
            :alignItems "center"
            :justifyContent "center"}
           :title
           {:fontWeight "bold"
            :fontSize 24
            :color "blue"}
           :normal
           {:fontSize 12
            :color "black"}}
          (clj->js)
          (rn/StyleSheet.create)))

(defn root-component-fn []
  [main-screen])

(defn start
  {:dev/after-load true}
  []
  (cljs-init-fn)
  (expo/render-root (r/as-element [root-component-fn])))

(defn init []
  (start))

(comment

  

  ;;
  )
