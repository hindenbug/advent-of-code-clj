(:Require 'leiningen.exec)
(require '[clojure.string :as str])
(require '[clojure.java.io :as io])

(def input
  (line-seq (io/reader "5.txt")))

(defn nice-string [s]
  (and
    (<= 3 (count (re-matches #".*(a|e|i|o|u).*" (str %)) s))
    (not (re-matches #".*(ab|cd|pq|xy).*" s))
    (boolean (re-find #"(.)\1+" s))))

(println (count (filter nice-string input)))

(defn new-nice-string [s]
  (and
   (boolean (re-find #"([a-z][a-z]).*\1" s))
   (boolean (re-find #"(.).\1" s))))

(defn part2 [input]
  (count (filter new-nice-string input)))

(part2 input)
