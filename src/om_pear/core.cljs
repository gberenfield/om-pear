(ns om-pear.core
  (:require
    [om.core :as om :include-macros true]
    [om.dom :as dom :include-macros true]
    [domina.events :refer [listen! capture! prevent-default dispatch! current-target]]
    [pear]))

(def my-testdev-grid (atom
                       ; this should be defined where used.. this is for dev/testing really. remove when rdy
                       ; could include this in docs
                       {:columns
                        (clj->js [(js/pear.data.Column. "Name" "name" "name" 100 js/pear.data.Column.DataType.TEXT js/pear.data.Column.Align.CENTER)
                                  (js/pear.data.Column. "Address" "address" "address" 300 js/pear.data.Column.DataType.TEXT js/pear.data.Column.Align.CENTER)])
                        :data
                        (clj->js [{:name "Bob Fleming" :address "230 Oak St., NJ USA"}
                                  {:name "Rick Rearden" :address "91 Windings Way, Clement OK 28174"}])
                        :config (clj->js {:AllowColumnResize true :ShowCellBorder true :AllowAlternateRowHighlight true})
                        :width 500 :height 150 :title "My Test Grid"
                        :events {:on-column-resize (fn [e] (.log js/console "resizing column"))}
                        }))

(defn ^:export om-pear [app-state owner]
  (om/component (let [g (js/pear.ui.Grid.)]
                  (.setColumns g (:columns app-state))
                  (.setDataRows g (:data app-state))
                  (.setConfiguration g (:config app-state) )
                  (.setWidth g (:width app-state))
                  (.setHeight g (:height app-state))
                  (.setTitle g (:title app-state))
                  (.registerPlugin g (js/pear.plugin.TitleBar.))
                  (doseq [k (keys (:events app-state)) :let [f (get (:events app-state) k)]]
                    (listen! g k f))
                  (dom/div {} (.render g)) ; 25-33ms
                  )))

;; (.time js/console "cljs")
(om/root ; again for testing/dev.. remove when rdy
         ; could include this in docs
         om-pear
         my-testdev-grid
         {:target (. js/document (getElementById "here"))})
;; (.timeEnd js/console "cljs")

