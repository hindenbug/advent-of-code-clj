(:require 'leiningen.exec)
(require '[clojure.string :as str])
(require '[clojure.java.io :as io])

(def gift-paper (atom ()))
(def ribbon (atom ()))

(defn gift-wrap-area [dimensions]
  (let [l (Integer/parseInt (first dimensions))
        w (Integer/parseInt (nth dimensions 1))
        h (Integer/parseInt (last dimensions))]
    (swap! gift-paper conj (+ (* 2 l w) (* 2 w h) (* 2 h l) (apply * (take 2 (sort [l w h])))))))

(defn ribbon-wrap-area [dimensions]
  (let [l (Integer/parseInt (first dimensions))
        w (Integer/parseInt (nth dimensions 1))
        h (Integer/parseInt (last dimensions))]
    (swap! ribbon conj (+ (apply + (map #(* % 2) (take 2 (sort [l w h]))))
                          (* l w h)))))

(defn part1 []
  (with-open [rdr (io/reader "2.txt")]
    (doseq [line (line-seq rdr)]
     (gift-wrap-area (str/split (str line) #"x"))
     (ribbon-wrap-area (str/split (str line) #"x")))))

(part1)
(println (apply + @gift-paper))
(println (apply + @ribbon))
