(ns adv.utils)

(defn test-examples
  "True if for each example pair, the output of an example input applied to the
  given function is the same as the example output"
  [f examples]
  (every? true?
          (map (fn [[k v]]
                 (= v (f k)))
               examples)))
