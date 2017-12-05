(ns adv.three
  (:require [clojure.string :as str]
            [adv.utils :as u]
            [clojure.math.numeric-tower :as math]))

;; Part 1

(def examples-pt1
  "A map of example input and their answers for part 1"
  {1 0
   12 3
   23 2
   1024 31})

(defn square-width
  "Given a number, work out the width of it's layer in the Ulam Spiral"
  [n]
  (let [width* (long (math/ceil (math/sqrt n)))]
    (if (even? width*)
      (+ 1 width*)
      width*)))

;; This function works thanks to two observations:
;; 1. The bottom right hand corner of a square is an odd number squared
;;   1) Because of this you can take any number the outer layer of a square
;;      divide it by two, round it up, if it's even then add one and that
;;      should be the width of the outer square
;;   2) Using this width you can square it to work out the bottom right hand
;;      number
;; 2. You can split the outer layer of a square into 4 parts, each having the
;;    width minus one numbers in it
;;   1) Using this and the difference between the current number and the bottom
;;      right hand number we can work out how far from a corner of the square a
;;      given number is

;; 17  16  15  14  13
;; 18   5   4   3  12
;; 19   6   1   2  11
;; 20   7   8   9  10
;; 21  22  23  24  25

(defn distance-to-port
  "Given a number, work out the manhattan distance from it to the center of the Ulam Spiral"
  [input]
  (let [width (square-width input)
        last-number (math/expt width 2)
        numbers-since (- last-number input)
        numbers-from-end (mod numbers-since (max (- width 1) 1))
        max-distance (- width 1)
        min-distance (/ max-distance 2)]
    (+ min-distance
       (math/abs (- min-distance
                    numbers-from-end)))))

;; Test the test inputs
(u/test-examples distance-to-port examples-pt1)
;; => true

;; Now for the actual input
(def input
  "My input for this challenge"
  312051)

(distance-to-port input)
;; => 430

;;; Part 2

(def examples-pt2
  "A map of example input and their answers for part 2"
  {1 1
   2 1
   3 2
   4 4
   5 5
   6 10
   7 11
   8 23
   9 25
   10 26
   11 54
   12 57
   13 59
   14 122})

(defn grid
  ([]
   (grid 1))
  ([n]
   (lazy-seq (cons n (grid (inc n))))))

;; Test the test inputs
#_
(u/test-examples sum-halfway-inputs examples-pt2)
;; => true

;; Now for the actual input
#_
(sum-halfway-inputs input)
;; => 1284
