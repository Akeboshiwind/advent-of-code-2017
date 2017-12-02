(ns adv.utils)

(defn digit->int
  [digit]
  (Character/digit digit 10))

(defn str->int
  [str]
  (Integer/parseInt str))

(defn test-examples
  "True if for each example pair, the output of an example input applied to the
  given function is the same as the example output"
  [f examples]
  (every? true?
          (map (fn [[k v]]
                 (= v (f k)))
               examples)))

(defn find-first
  "Returns the first element in the column to satisfy the predicate

  Stolen from: https://stackoverflow.com/a/10192733"
  [pred coll]
  (first (filter pred coll))) ;; This is ok because filter is lazy
