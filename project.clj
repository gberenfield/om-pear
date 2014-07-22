(defproject om-pear "0.1.0-SNAPSHOT"
  :description "om-pear: An om-component/clojurescript library for google-closure-grid"
  :url "https://github.com/gberenfield/om-pear"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2268" :scope "provided"]
                 [om "0.6.2" :scope "provided"]]
  :plugins [[lein-cljsbuild "1.0.3"]]
  :profiles {:dev {:clean-targets ["out"]
                   :cljsbuild {:builds {:dev
                                        {:source-paths ["src"]
                                         :compiler {
                                                    :output-to "resources/public/js/om-pear.js"
                                                    :optimizations :simple
                                                    :output-dir "out"
                                                    :libs ["lib"]
                                                    }}}}}})
