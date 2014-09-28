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
* figwheel + lein figwheel (live javascript reloading)
* weasel + piggieback (clojurescript REPL)

## Usage

To create a new project:

```
lein new liberagent example
```

In one terminal:

```
$ cd example
$ lein repl
example.api=> (brepl) ;; starts browser repl
```

In a second terminal:

```
cd example
lein figwheel
```

Changes to Clojurescript (.cljs) code will automatically be compiled
when files change. Figwheel takes care of reloading changes the
browser. Also see
[this](blog.michielborkent.nl/blog/2014/09/25/figwheel-keep-Om-turning/)
blog post.

In a third terminal:

```
cd example
lein ring server
```

A browser window will automatically open. If not, go to
http://localhost:8090/index.html.

Changes to Clojure (.clj) code will automatically be compiled upon
browser request.

In the browser repl type for example:

```
(.click (js/$ "#myButton"))
```

You are now controlling the client from a REPL!

## Start project without figwheel and weasel:

Use normal `lein cljsbuild` +

```
IS_DEV=false lein ring server
```

## Credits

This template is inspired by the
[Chestnut](https://github.com/plexus/chestnut) template.

## License

Copyright Michiel Borkent

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
