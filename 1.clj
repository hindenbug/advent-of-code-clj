(:require 'leiningen.exec)
(require '[clojure.java.io :as io])

(def floor (atom 0))
(def position (atom 0))

(defn char-seq
  [^java.io.Reader rdr]
  (let [chr (.read rdr)]
    (if (>= chr 0)
      (cons chr (lazy-seq (char-seq rdr))))))

(defn travel [direction]
  (swap! position inc)

  (if (= 40 direction)
    (swap! floor inc)
    (swap! floor dec))

  (if (= -1 @floor)
    (println @position)))

(defn part1 []
  (with-open [rdr (io/reader "1.txt")]
    (doseq [chr (char-seq rdr)]
     (travel chr))))

(part1)

