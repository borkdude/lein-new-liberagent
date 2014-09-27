(ns leiningen.new.liberagent
  (:use [leiningen.new.templates :only [renderer name-to-path sanitize-ns ->files]]))

(def render (renderer "liberagent"))

(defn liberagent
  [name]
  (let [data {:name name
              :ns-name (sanitize-ns name)
              :sanitized (name-to-path name)}]
    (->files data
["src/{{sanitized}}/api.clj" (render "api.clj" data)]
["src-cljs/{{sanitized}}/main.cljs" (render "main.cljs" data)]
["project.clj" (render "project.clj" data)]
["resources/public/index.html" (render "index.html" data)]
["resources/public/css/style.css" (render "style.css")]
)))
