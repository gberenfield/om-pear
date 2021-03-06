(defproject om-pear "0.1.0-SNAPSHOT"
  :description "om-pear: An om-component/clojurescript library for google-closure-grid"
  :url "https://github.com/gberenfield/om-pear"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2268" :scope "provided"]
                 [domina "1.0.2"]
                 [om "0.6.5" :scope "provided"]]
  :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]]
  :profiles {:dev {:clean-targets ["out"]
                   :cljsbuild {:builds {:dev
                                        {:source-paths ["src"]
                                         :compiler {
                                                    :output-to "om-pear.js"
                                                    :optimizations :simple
                                                    :output-dir "out"
                                                    :source-map "om-pear.js.map"
                                                    :libs ["lib"]
                                                    }}}}}})
