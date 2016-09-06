API REST - ANGLE CLOCK
=====================

This project is an API REST to calculate the angle of the clock pointers.

Module Information
========================

It has two modules as described below:

* clock-core (Cache and business rule)
* clock-api (REST API using clock-core as a library)

That project use
========================

* Java 1.8
* Maven 3.x

Dependencies
========================

* JBoss RestEasy (Implementation JAX-RS: http://resteasy.jboss.org/)
* SL4J (Logging: http://www.slf4j.org/)
* JUnit (Test: http://junit.org/junit4/)

How to start project?
========================

1. repository clone;
2. check your Java and Maven versions;
3. go to the project root directory in terminal;
4. run: $ mvn clean install;
5. go to clock-api directory;
6. run: $ mvn tomcat7:run;
7. run in other terminal session: $ curl http://localhost:8080/rest/clock/6/0 or access in your browser. Will return: {"angle":180}

Ref: Clock angle problem
========================

* Clock Angle Problem: https://goo.gl/CgsI5M

License
========================

[MIT License](http://leonardorifeli.mit-license.org/) © Leonardo Rifeli