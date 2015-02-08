(defproject mmh-clojure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [yesql "0.4.0"]
                 [org.clojure/data.json "0.2.5"]
                 [org.postgresql/postgresql "9.3-1102-jdbc41"]
                 [org.clojure/clojurescript "0.0-2760"]
                 [figwheel "0.2.3-SNAPSHOT"]
                 [ring/ring-defaults "0.1.2"]]
  :plugins [[lein-ring "0.8.13"]
            [lein-cljsbuild "1.0.4"]
            [lein-figwheel "0.2.3-SNAPSHOT"]]
  :ring {:handler mmh-clojure.handler/app}
  :cljsbuild {
               :builds [ { :id "mmh-build"
                           :source-paths ["src/cljs/"]
                           :compiler { :output-to "resources/public/js/compiled/mmh.js"
                                       :output-dir "resources/public/js/compiled/out"
                                       :externs ["resources/public/js/externs/jquery-1.9.js"]
                                       :optimizations :none
                                       :source-map true } } ]
               }
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
