(ns om-pear.core
  (:require
    [om.core :as om :include-macros true]
    [om.dom :as dom :include-macros true]
    [pear]))

(def mygrid (atom {:columns
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
                      dom-ele (dom/div #js {:id "grid2"})]
                  (.setColumns g (:columns @mygrid))
                  (.setDataRows g (:data @mygrid))
                  (.setConfiguration g (:config @mygrid) )
                  (.setWidth g (:width @mygrid))
                  (.setHeight g (:height @mygrid))
                  (.setTitle g (:title @mygrid))
                  (.render g (. js/document (getElementById "grid2")))
                  ;; (js/console.log g)
                  dom-ele)))

(om/root
  om-pear
  mygrid
  {:target (. js/document (getElementById "here"))})

