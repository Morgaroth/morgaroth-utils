## Żeby opublikować plugin od zera, trzeba:

* ogarnąć build.sbt z pozycjami jak w aktualnym
* ogarnąć plugin do budowania jak w project/plugins.sbt
* dać kod
* zrobić repo GITA, żeby były linki w pomie
* w konsoli:
    * ;clean;compile
    * chyba publishSigned, po wpisaniu hasła publish signed wyśle to na neta
    * pójść na https://oss.sonatype.org/, zalogować się
    * przejść do menu po lewej StagingRepositories
    * znaleźć swoje
    * zaznaczyć, kliknąć Close
    * po chwili odświeżyć stronkę, powinno się dać teraz zaznaczyć nasze i kliknąć Release
    * po chwili pozycja mowinna zniknąć, a lib powinien być dostepny przez sbt