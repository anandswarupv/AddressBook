Addressbook App
===============

This example app is created using Java 8, Maven, Vaadin Framework, Spring and PostgresDB

Running the example from the command line
-------------------
$ mvn jetty:run

Open http://localhost:8080/

Importing in IntelliJ IDEA 14
--------------------
These instructions were tested on IntelliJ IDEA 14 CE. You can get it from https://www.jetbrains.com/idea/

To get the project up and running in IDEA, do:
- File -> New -> Project from Version Control -> Git
- The URL to use is https://github.com/anandswarupv/AddressBook.git
- To start the project, find the "Maven Projects" tab on the right hand side of the screen and navigate to
  - Vaadin Web Application -> Plugins -> jetty -> jetty:run
  - Click the play button or right click and select Run (Select Debug instead to run in debug mode)

You should now have a Jetty server running on localhost:8080.
Navigate to http://localhost:8080 to play with the application