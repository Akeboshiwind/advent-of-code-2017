(def project 'adv)
(def version "0.1.0-SNAPSHOT")

(set-env! :resource-paths #{"resources" "src"}
          :source-paths   #{"test"}
          :dependencies   '[[org.clojure/clojure "1.9.0-alpha17"]])

(task-options!
 aot {:namespace   #{'adv.core}}
 pom {:project     project
      :version     version
      :description "My solutions to 2017's advent of code"
      :url         ""
      :scm         {:url "https://github.com/akeboshiwind/adv"}
      :license     {"MIT License"
                    "https://opensource.org/licenses/MIT"}}
 jar {:main        'adv.core
      :file        (str "adv-" version "-standalone.jar")})

(deftask build
  "Build the project locally as a JAR."
  [d dir PATH #{str} "the set of directories to write to (target)."]
  (let [dir (if (seq dir) dir #{"target"})]
    (comp (aot) (pom) (uber) (jar) (target :dir dir))))

(deftask run
  "Run the project."
  [a args ARG [str] "the arguments for the application."]
  (require '[adv.core :as app])
  (apply (resolve 'app/-main) args))
