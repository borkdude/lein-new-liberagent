# lein new liberagent

Template for apps that use Reagent on the client and
Compojure+Liberator on the server.

## Dependencies and plugins for app

* clojure 1.6.0
* clojurescript + lein cljsbuild
* core.async
* ring-core + lein ring
* ring-jetty-adapter
* compojure
* liberator
* React 0.11.2
* Bootstrap 3.2.0
* cljs-http
* reagent
* sablono
* figwheel + lein figwheel

## Usage

To create a new project:

```
lein new liberagent example
```

In one terminal:

````
cd example
lein figwheel
````

Changes to Clojurescript (.cljs) code will automatically be compiled
when files change. Figwheel takes care of reloading changes the
browser. Also see
[this](blog.michielborkent.nl/blog/2014/09/25/figwheel-keep-Om-turning/)
blog post.

In another terminal:

````
cd example
lein ring server
````

A browser window will automatically open. If not, go to
http://localhost:8090/index.html.

Changes to Clojure (.clj) code will automatically be compiled upon
browser request.

## License

Copyright Michiel Borkent

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
