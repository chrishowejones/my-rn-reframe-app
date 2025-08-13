(ns my-app.effects
  (:require ["react-native" :as rn]
            [re-frame.core :as rf]))

(def log-count
  (re-frame.core/->interceptor
    :id      :log-count
    :before  (fn [context]
               (println "State before fx:" (-> context :coeffects :db))
               context)
    :after   (fn [context]
               (println "State after fx:" (-> context :effects :db))
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
    {:log-effect (str "Current state: " db)}))

(rf/reg-event-fx
 :update-name
 (fn [{:keys [db]} [_ text]]
   {:db (assoc db :name text)}))
