# Sbt line

```scala
libraryDependencies += "io.github.morgaroth" %% "morgaroth-utils-base" % "1.2.5"
```


# CHANGELOG

* v1.2.6:

    * no changes, version update to keep the same version in all libraries

* v1.2.5:

    * no changes

* v1.2.4:

    * replaced reflective objects with explicit wrapper classes
    * added printToFile function as method as extension method to String

* v1.2.3:
    
    * added printToFile function as method as extension method to String


* v1.2.2:
    
    * moved classes to another namespace (cause no backward compatibility)
    * added randomDSL with syntax:
    
        ```scala
         10 ~ 40 random()
         ```
    * added k,kk methods to double to express thousands
        
       ``` scala
       (200 ~ 1.5 k) milliseconds
       ```
       
    * added randomDouble, randDouble, randInt, randInt(max)

* v1.1.1:

    * added object/null to Option implicit wrapper

* v1.1.0:

    * initial

