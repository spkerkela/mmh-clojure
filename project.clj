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
                 [figwheel "0.5.20"]
                 [reagent "0.5.0-alpha"]
                 [reagent-forms "0.4.3"]
                 [reagent-utils "0.1.2"]
                 [cljs-ajax "0.3.9"]
                 [com.cognitect/transit-cljs "0.8.205"]
                 [com.cognitect/transit-clj "0.8.259"]
                 [ring/ring-defaults "0.1.2"]
                 [secretary "1.2.1"]]
  :plugins [[lein-ring "0.8.13"]
            [lein-cljsbuild "1.0.4"]
            [lein-figwheel "0.2.3-SNAPSHOT"]]
  :ring {:handler mmh-clojure.handler/app}
  :cljsbuild {
               :builds [{:id "mmh-build"
                         :source-paths ["src/mmh_client/"]
                         :compiler {:output-to "resources/public/js/compiled/mmh.js"
                                    :output-dir "resources/public/js/compiled/out"
                                    ;:externs ["resources/public/js/externs/jquery-1.9.js"]
                                    :optimizations :none
                                    :source-map true}}]
               }
  :figwheel {
              :http-server-root "public" ;; this will be in resources/
              :server-port 3000 ;; default

              ;; CSS reloading (optional)
              ;; :css-dirs has no default value
              ;; if :css-dirs is set figwheel will detect css file changes and
              ;; send them to the browser
              :css-dirs ["resources/public/css"]

              ;; Server Ring Handler (optional)
              ;; if you want to embed a ring handler into the figwheel http-kit
              ;; server
              :ring-handler mmh-clojure.handler/app

              ;; To be able to open files in your editor from the heads up display
              ;; you will need to put a script on your path.
              ;; that script will have to take a file path and a line number
              ;; ie. in  ~/bin/myfile-opener
              ;; #! /bin/sh
              ;; emacsclient -n +$2 $1
              ;;
              :open-file-command "myfile-opener"

              ;; if you want to disable the REPL
              ;; :repl false

              ;; to configure a different figwheel logfile path
              ;; :server-logfile "tmp/logs/figwheel-logfile.log"

              }
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]]}})
