(ns {{ns-name}}.main
  (:require-macros [cljs.core.async.macros :refer (go)])
  (:require
    [reagent.core :as reagent :refer [atom]]
    [cljs-http.client :as http]
    [cljs.core.async :refer (<!)]
    [figwheel.client :as fw]
    [weasel.repl :as ws-repl]))

(enable-console-print!)

(defonce init-dev
  ;; we need to defonce this, so it won't be executed again upon code
  ;; reload
  (go (let [body (:body (<! (http/get "/is-dev")))]
        (when (= body true) ;; has to match exactly true and not some string
          (fw/watch-and-reload
           :websocket-url   "ws://localhost:3449/figwheel-ws"
           :jsload-callback
           (fn []
             (println "reloaded")))
          (ws-repl/connect "ws://localhost:9001" :verbose true)))))

(defonce app-state (atom {:text ""}))

(go (let [body (:body (<! (http/get "/greeting")))]
      (swap! app-state assoc :text body)))

(defn screen []
  [:div.jumbotron
   [:div.container
    [:h1 (-> @app-state :text)]
    [:button#myButton.btn.btn-default
     {:on-click #(swap! app-state assoc :text
                        "You clicked!")}
     "Click to change text"]]])

(reagent/render-component [screen]
                          (js/document.getElementById "app"))
