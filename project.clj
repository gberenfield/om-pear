(defproject om-pear "0.1.0-SNAPSHOT"
  :description "om-pear: An om-component/clojurescript library for google-closure-grid"
  :url "https://github.com/gberenfield/om-pear"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2234"] ; namespace not provided for pol.jqgrid when brepl eval of (ns pol.orders
                 ;; [domina "1.0.2"]
                 [om "0.6.2"]
                 ]

  :source-paths ["src/clj" "src/cljs"]
  :repl-options {:init-ns pol.repl}
  :plugins [[lein-ring "0.8.10"]
            [lein-environ "0.4.0"]
            [lein-cljsbuild "1.0.3"]
            ]
  :cljsbuild {:builds {:prod
                       {:source-paths ["src/cljs"]
                        :compiler {:output-to "resources/public/js/cljs.js"
                                   :optimizations :advanced
                                   :pretty-print false}}}}
  :profiles
  {:production {:ring {:open-browser? false
                       :stacktraces?  false
                       :auto-reload?  false}}
   :dev {:dependencies [[environ "0.5.0"]
                        ]
         :ring {:handler om-pear.handler/app
                :init    om-pear.handler/init
                :destroy om-pear.handler/destroy}

         :env {:selmer-dev true}
         :source-paths ["src/brepl"] ; FOCUS here! should be shared.. see modern-cljs example.. & om-tut.. need to redo clj ns to be in-line with those examples...
         :test-paths ["target/test/cljs"]
         :clean-targets ["out"]

         :plugins [
                   [com.cemerick/clojurescript.test "0.3.0"]]
         :cljsbuild {
                     :builds
                     {:dev
                      {:source-paths ["src/brepl" "src/cljs"]
                       :compiler {:output-to "resources/public/js/cljs_dbg.js"
                                  :optimizations :whitespace
                                  :pretty-print true}}
                      }}
         }}
  :min-lein-version "2.0.0")
