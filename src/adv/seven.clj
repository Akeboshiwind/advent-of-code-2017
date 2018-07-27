(ns adv.seven
  (:require [clojure.string :as str]
            [adv.utils :as u]
            [clojure.spec.alpha :as s]))

;; Part 1

(def examples-pt1
  "A map of example input and their answers for part 1"
  {"pbga (66)
xhth (57)
ebii (61)
havc (66)
ktlj (57)
fwft (72) -> ktlj, cntj, xhth
qoyq (66)
padx (45) -> pbga, havc, qoyq
tknk (41) -> ugml, padx, fwft
jptl (61)
ugml (68) -> gyxo, ebii, jptl
gyxo (61)
cntj (57)" "tknk"})

(s/def ::program
  symbol?)

(s/def ::weight
  (s/coll-of int?))

(s/def ::parent
  (s/and :program ::program
         :weight ::weight))

(s/def ::child
  ::program)

(s/def ::children
  (s/* ::child))

(s/def ::layer
  (s/cat :parent ::parent
         :arrow #{'->}
         :children ::children))

(defn parse
  [input])

(defn parent
  [coll child]
  (get child coll))

;; Test the test inputs
#_
(u/test-examples sum-input examples-pt1)
;; => true

;; Now for the actual input
#_
(def input
  "My input for this challenge"
  "")

#_
(sum-input input)
;; => 1223

;;; Part 2

#_
(def examples-pt2
  "A map of example input and their answers for part 1"
  {"1212" 6
   "1221" 0
   "123425" 4
   "123123" 12
   "12131415" 4})

;; Test the test inputs
#_
(u/test-examples sum-halfway-inputs examples-pt2)
;; => true

;; Now for the actual input
#_
(sum-halfway-inputs input)
;; => 1284
