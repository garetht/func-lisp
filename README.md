# FuncLisp

FuncLisp is a small language which can only perform one computation: addition:


```clojure
(+ 1 2 (+ 3 (+ 4 5 6 7)))
```

However, it is meant to support user function definition

```clojure
(defn doubler [x]
    (+ x x))
```

as well as first-class functions:

```clojure
(defn curry [x]
    (defn curry-inner [y]
      (+ x y)))
      
((curry 1) 2)
```

The latter two features have not yet been implemented, although there is currently syntactic support.
