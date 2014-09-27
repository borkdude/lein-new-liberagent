(ns {{ns-name}}.main
  (:require-macros [cljs.core.async.macros :refer (go)])
  (:require
    [reagent.core :as reagent :refer [atom]]
    [sablono.core :as html :refer-macros [html]]
    [cljs-http.client :as http]
    [cljs.core.async :refer (<!)]
    [figwheel.client :as fw]))

(enable-console-print!)

(defonce app-state (atom {:text ""}))

(go (let [body (:body (<! (http/get "/greeting")))]
      (swap! app-state assoc :text body)))

(defn screen []
  [:div.jumbotron
   [:div.container
    [:h1 (-> @app-state :text)]]])

(reagent/render-component [screen]
                          (js/document.getElementById "app"))

(fw/watch-and-reload
 :websocket-url   "ws://localhost:3449/figwheel-ws"
 :jsload-callback
 (fn []
   (println "reloaded")))
