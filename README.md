JavaFF: Java Facade/Factories
=============================

[![Build Status](https://travis-ci.org/rhkiswani/JavaFF.svg?branch=develop)](https://travis-ci.org/rhkiswani/JavaFF)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.rhkiswani/javaff.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22io.github.rhkiswani%22)

We all know the Golden Object Oriented rule **Don't talk to strangers**

We all know that you will never find the API/frameworks defects or magic or limitations the begging of the development.
 
So imagine you are using an API in all your projects and after spending months or years that API you got a production issue because of it even if it so famous and mature, 
changing the that API would be soo hard and costy !!!

Examples for famous bugs in very famous frameworks 
--------------------------------------------------- 
Memory Leak
- [(POI) OOM caused by Memory Leak in FileBackedDataSource ](https://bz.apache.org/bugzilla/show_bug.cgi?id=60140)
- [(MySql JDBC connector) Memory leak in ResultSet](https://bugs.mysql.com/bug.php?id=5022)
- [(Gson) Memory Leak in web application](https://github.com/google/gson/issues/402) 

Performance 
- [(LOG4J) log4j performance tuning in production config](https://bugzilla.redhat.com/show_bug.cgi?id=778690)
- [(Guava) performance problem in LinkedHashMultimap](https://github.com/google/guava/issues/1013)

Deadlocks
- [(Oracle JDK) deadlock in SSLSocketImpl between between write and close](http://bugs.java.com/view_bug.do?bug_id=8013809)

So, we should always protect our project and noy use a framwork or API directly and this is the main idea here  

Main Features
-------------- 
- This project offers a standard/clear API for the most used API's in the Java Applications like : Exceptions, Locale, Beans, Formatter's, Json Handlers, Loggers, ReflectionHelpers ...etc 
- **You can control the implementation's through the class path without changing line of code**
- Smart Exception handling mechanism
- Default Implementations
- Many Utilises 
  
Prerequisites 
-------------
Requires JDK 1.7 or higher.

