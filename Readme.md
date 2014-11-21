# Sbt line

```scala
//libraryDependencies += "io.github.morgaroth" %% "morgaroth-utils-PROPER_NAME" % "1.2.4"
libraryDependencies += "io.github.morgaroth" %% "morgaroth-utils-base" % "1.2.4"
libraryDependencies += "io.github.morgaroth" %% "morgaroth-utils-crypto" % "1.2.4"
libraryDependencies += "io.github.morgaroth" %% "morgaroth-utils-mongodb" % "1.2.4"
libraryDependencies += "io.github.morgaroth" %% "morgaroth-utils-spray" % "1.2.4"
```


# CHANGELOG

* v1.2.4:

    * more
    * added SalatDAOConf class (extending SalatDAO) which provides reading database access from application configuration
    * added two new modules: spray for utils for applications based on spray and mongodb for mondodb support

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

