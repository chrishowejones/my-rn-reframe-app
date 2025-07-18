(ns my-app.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :current-count
 (fn [db _]
   (:count db)))
