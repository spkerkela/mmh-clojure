(ns mmh_client.ajax
  (:require [cognitect.transit :as t])
  (:import [goog.net XhrIo]))

(defonce r (t/reader :json))
(defonce wr (t/writer :json))

(defn get-data [url cb]
  (XhrIo.send (str "/" url)
    (fn [e]
      (let [xhr (.-target e)]
        (cb (.getResponseText xhr))))))

(defn get-data-ext [url cb]
  (XhrIo.send url
    (fn [e]
      (let [xhr (.-target e)]
        (cb (.getResponseText xhr))))))

(defn read [res]
  (t/read r res))
