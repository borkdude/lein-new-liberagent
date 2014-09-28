(ns {{ns-name}}.api
    (:require
     [liberator.core :refer (resource)]
     [compojure.core :refer (defroutes ANY GET)]
     [compojure.route :refer (resources not-found)]
     [ring.middleware.params :refer (wrap-params)]
     [ring.middleware.edn :refer (wrap-edn-params)]
     [ring.util.response :refer (redirect)]
     [cemerick.piggieback :as piggieback]
     [weasel.repl.websocket :as weasel]
     [environ.core :refer [env]]))

(def is-dev? (let [setting (env :is-dev)]
               (if (string? setting)
                 (Boolean/parseBoolean setting)
                 setting)))

(defroutes routes
  (GET "/is-dev" []
       {:status 200
        :body (pr-str is-dev?)
        :headers {"Content-Type" "application/edn"}})
  (GET "/greeting" []
       "Hello World!")
  (ANY "/"
       []
       (redirect "/index.html"))

  (resources "/" {:root "public"})
  (resources "/" {:root "/META-INF/resources"})
  (not-found "404"))

(def handler
  (-> routes
      wrap-params
      wrap-edn-params))

(defn brepl []
  (piggieback/cljs-repl
   :repl-env
   (weasel/repl-env :ip "0.0.0.0" :port 9001)))
