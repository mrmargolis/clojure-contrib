;; Test routines for set.clj

;; by Matthew Margolis
;; created January 25, 2010

;; Copyright (c) Matthew Margolis. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

(ns clojure.contrib.test-contrib.set
    (:use clojure.test
          clojure.contrib.set))


(deftest test-subset?
  (are [set1 set2] (true? (subset? set1 set2))
       #{} #{}
       #{} #{1}
       #{1} #{1}
       #{1} #{1 2}
       #{1 2} #{1 2}
       #{1 2} #{1 2 3}
       #{1 3} #{1 2 3})
  (are [set1 set2] (false? (subset? set1 set2))
       #{1} #{}
       #{1} #{2}
       #{1 2} #{1}
       #{1 2 3} #{1 2}
       #{1 2 3} #{1 3}))

(deftest test-superset?
  (are [set1 set2] (true? (superset? set1 set2))
       #{} #{}
       #{1} #{}
       #{1} #{1}
       #{1 2} #{1}
       #{1 2} #{1 2}
       #{1 2 3} #{1 2}
       #{1 2 3} #{1 3})
  (are [set1 set2] (false? (superset? set1 set2))
       #{} #{1}
       #{1} #{2}
       #{1} #{1 2}
       #{1 2} #{1 2 3}
       #{1 3} #{1 2 3}))


(deftest test-proper-subset?
  (are [set1 set2] (true? (proper-subset? set1 set2))
       #{} #{1}
       #{1} #{1 2}
       #{1 2} #{1 2 3}
       #{1 3} #{1 2 3})
  (are [set1 set2] (false? (proper-subset? set1 set2))
       #{} #{}
       #{1} #{1}
       #{1} #{2}
       #{1 2} #{1 2}
       #{1 2} #{1}
       #{1 2 3} #{1 2}
       #{1 2 3} #{1 3}))


(deftest test-proper-superset?
  (are [set1 set2] (true? (proper-superset? set1 set2))
       #{1} #{}
       #{1 2} #{1}
       #{1 2 3} #{1 2}
       #{1 2 3} #{1 3})
  (are [set1 set2] (false? (proper-superset? set1 set2))
       #{} #{1}
       #{1} #{2}
       #{1} #{1 2}
       #{1 2} #{1 2 3}
       #{1 3} #{1 2 3}))
