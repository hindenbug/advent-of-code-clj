(:require 'leiningen.exec)
(require '[clojure.string :as str])
(require '[clojure.java.io :as io])


(defn deliver-present [direction]
  (condp = direction
    \v [0 -1]
    \^ [0 1]
    \< [-1 0]
    \> [1 0]))


(def input (slurp "3.txt"))

;; part 1 ;; reductions a new learning
(println (count (set
                  (reductions
                    #(map + %1 %2)
                    (list 0 0)
                    (map deliver-present input)))))

;;part 2
(println (count (set (concat
                       (reductions
                         #(map + %1 %2)
                         (list 0 0)
                         (map deliver-present (map first (partition 2 input))))
                       (reductions
                         #(map + %1 %2)
                         (list 0 0)
                         (map deliver-present (map second (partition 2 input))))))))

