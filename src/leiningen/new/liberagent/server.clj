(ns webapp.server
  (:use [ring.adapter.jetty]
        [ring.middleware.resource]))

;; Low level handler to illustrate what a ring response might look like
(defn handler [request]
  {:status 404
   :headers {"Content-Type" "text/html"}
   :body (str "Cannot find:" (:uri request))})

(def app
  (-> handler
      (wrap-resource "public")
      (wrap-resource "/META-INF/resources")
      ;;resources from webjars
      ))

;; Logic to stop and start server from REPL.
;; Not needed when using lein ring server.
(def server (atom nil))

(defn stop-server! []
  (.stop @server)
  (reset! server nil))

(defn start-server! []
  (let [s (run-jetty app {:port
                          (Integer/parseInt (or (System/getenv "PORT")
                                                "8080"))
                          :join? false})]
    (reset! server s)))
