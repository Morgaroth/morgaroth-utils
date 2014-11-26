# Sbt line

```scala
libraryDependencies += "io.github.morgaroth" %% "morgaroth-utils-spray" % "1.2.4"
```


# CHANGELOG

* v1.2.5:

    * added traits with loggers which simplifies working with loggers outside actors, when is implicit actorSystem available in scope

* v1.2.4:

    * added full stack spray authentication with authentication route to append do app routes
