(ns adv.six
  (:require [clojure.string :as str]
            [adv.utils :as u]
            [clojure.math.numeric-tower :as m]))

;; Part 1

(def examples-pt1
  "A map of example input and their answers for part 1"
  {"0\t2\t7\t0" 5})

(defn parse
  "Parse the input into a vector"
  [input]
  (-> input
      (str/split #"\t")
      (->> (mapv u/str->int))))

(defn remainders
  "Work out the where the remainders need to be added"
  [filled length index]
  (u/rotr (inc index)
          (concat (repeat filled 1)
                  (repeat (- length filled) 0))))

(defn redistribute
  "Find the max value in the banks, then redistribute it's value to the other banks."
  [banks]
  (let [m (apply max banks)
        length (count banks)
        idx (.indexOf banks m)]
    (mapv +
          banks
          (concat (repeat idx 0) ;; Clear the max value
                  [(- m)]
                  (repeat 0))
          (repeat length ;; Redistribute to all
                  (quot m length))
          (remainders (mod m length) ;; Redistribute the remainder
                      length
                      idx))))

(defn steps-to-loop
  "Work out how many steps you have to take until you start a loop.
  i.e. until you hit the same number"
  [banks]
  (reduce (fn [configs next]
            (if (contains? configs next)
              (reduced (count configs))
              (conj configs next)))
          #{}
          (iterate redistribute banks)))

(defn solve
  "Parse the input then solve it"
  [input]
  (steps-to-loop (parse input)))

;; Test the test inputs
(u/test-examples solve examples-pt1)
;; => true

;; Now for the actual input
(def input
  "My input for this challenge"
  "14\t0\t15\t12\t11\t11\t3\t5\t1\t6\t8\t4\t9\t1\t8\t4")


(solve input)
;; => 11137

;;; Part 2

(def examples-pt2
  "A map of example input and their answers for part 2"
  {"0\t2\t7\t0" 4})

(defn steps-in-loop
  "Work out how many steps there are in the loop in the given input"
  [banks]
  (reduce (fn [configs next]
            (if-not (= -1 (.indexOf configs next))
              (reduced (- (count configs) (.indexOf configs next)))
              (conj configs next)))
          []
          (iterate redistribute banks)))

(defn solve2
  "Parse the input then solve it"
  [input]
  (steps-in-loop (parse input)))

;; Test the test inputs
(u/test-examples solve2 examples-pt2)
;; => true

;; Now for the actual input
(solve2 input)
;; => 1037
