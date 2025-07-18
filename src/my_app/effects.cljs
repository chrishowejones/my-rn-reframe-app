(ns my-app.effects
  (:require ["react-native" :as rn]
            [re-frame.core :as rf]))

(def log-count
  (re-frame.core/->interceptor
    :id      :log-count
    :before  (fn [context]
               (println "Count before fx:" (-> context :coeffects :db :count))
               context)
    :after   (fn [context]
               (println "Count after fx:" (-> context :effects :db :count))
               context)))

(rf/reg-fx
 :log-effect
 (fn [message]
   (rn/Alert.alert "Effect Log" message)))

(rf/reg-event-fx
 :increment-count
 [log-count]
 (fn [{db :db} [_]]
   (let [new-db (update db :count inc)]
     {:db new-db})))

(rf/reg-event-fx
 :decrement-count
 [log-count]
 (fn [{db :db} [_]]
   {:db (update db :count dec)}))

(rf/reg-event-fx
  :alert-count
  (fn [{:keys [db]} [_]]
    {:log-effect (str "Current count: " (:count db))}))
