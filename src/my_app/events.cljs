(ns my-app.events
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
  :initialize-db
  (fn [_ _]
    {:count 0}))
