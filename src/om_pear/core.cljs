(ns om-pear.core
  (:require
    [om.core :as om :include-macros true]
    [om.dom :as dom :include-macros true]
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
                        }))

(defn ^:export om-pear [app-state owner]
  (om/component (let [g (js/pear.ui.Grid.) 
                      dom-ele (dom/div #js {:id "omdom-grid"})]
                  (.setColumns g (:columns app-state))
                  (.setDataRows g (:data app-state))
                  (.setConfiguration g (:config app-state) )
                  (.setWidth g (:width app-state))
                  (.setHeight g (:height app-state))
                  (.setTitle g (:title app-state))
                  (.render g (. js/document (getElementById "omdom-grid")))
                  ;; (js/console.log g)
                  (js/console.log dom-ele)
                  dom-ele)))

(om/root ; again for testing/dev.. remove when rdy
         ; could include this in docs
         om-pear
         my-testdev-grid
         {:target (. js/document (getElementById "here"))})

