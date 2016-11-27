JavaFF: Java Facade/Factories
=============================

[![Build Status](https://travis-ci.org/rhkiswani/JavaFF.svg?branch=develop)](https://travis-ci.org/rhkiswani/JavaFF)
[![Coverage Status](https://coveralls.io/repos/github/rhkiswani/JavaFF/badge.svg?branch=master)](https://coveralls.io/github/rhkiswani/JavaFF?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/14f56cb2e4a24df0b745943d600c9894)](https://www.codacy.com/app/rhkiswani/JavaFF?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=rhkiswani/JavaFF&amp;utm_campaign=Badge_Grade)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.rhkiswani/javaff.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22io.github.rhkiswani%22)

We all know the Golden Object Oriented rule **Don't talk to strangers**

We all know that you will never find the API/frameworks defects or magic or limitations at the beginning of the development.
 
Imagine yourself using API in all your projects for years. Everybody else is using it as well, it is famous and mature. Suddenly you have a huge production issue.  Changing that API is too hard and costly at this stage! 

What now?

Or you decided to change any API/framework for new requirements.
Lets take  logging as an example, now we have log4j, logback, JDK logging.
At the beginning of the project you decide to go with the logback since it's so famous and faster than log4j and it's bundled with Spring, etc.

After 1 year of the project you got a new requirements for the logging which are:
- All logs should be localized 
- FATAL errors should be sent to a specific email 

Disaster, right?!

Solution 
=========================================================

So how we can solve this issue , simply we need to follow three Design Patterns:
- Factory
- Wrapper
- Facade

And use this flow:

![JavaFF](https://media.licdn.com/mpr/mpr/AAEAAQAAAAAAAAepAAAAJGVmYjQzMWM2LTU3MmYtNDUyYy05MjAzLTM0OTUwNjUyYTg3Zg.png)

Below is an exmple class diagram from the logger used in JavaFF

![JavaFF](https://media.licdn.com/mpr/mpr/AAEAAQAAAAAAAAi1AAAAJDYyZTU2MTk5LWMxMTUtNDFlYi05ZWRkLWMxMGUzMWQ3ZTlkZg.png)

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

So, we should always protect our project and noy use a framework or API directly and this is the main idea here  

Prerequisites 
-------------
Requires JDK 1.7 or higher.

Usage & Examples 
-------------
- [Gson VS Jakson: Example shows how to contol the json implementation through the classpath](https://github.com/rhkiswani/JavaFF-Samples/tree/master/GsonVsJakson)

- [Exception Handling: Example shows how to control the exceptions handling by the type](https://github.com/rhkiswani/JavaFF-Samples/tree/master/Exceptions)

- [Log4j Vs Logback Vs JavaUtillogger: : Example shows how to control the logging through the classpath](https://github.com/rhkiswani/JavaFF-Samples/tree/master/Log4jVsLogbackVsJavaUtillogger)

Main Features
-------------- 
- This project offers a standard/clear API for the most used API's in the Java Applications like: Exceptions, Locale, Beans, Formatter's, Json Handlers, Loggers, ReflectionHelpers, etc. 

- **You can control the implementations through the class path without changing line of code**

The below example shows how the implementation will be changed without changing the code:
Now I have the below dependencies in my pom.xml 
```xml
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>${slf4.version}</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-jdk14</artifactId>
        <version>${slf4.version}</version>
        <scope>provided</scope>
    </dependency>
```
So when I call 
```java
    LogFactory.getLogger(LogFactory.class).info("LOCALIZED_MSG", "Kiswani");  
    
    Output:
    
    Nov 22, 2016 6:54:07 PM io.github.rhkiswani.javaff.log.Slf4jLog info
    INFO: this is localized msg from messages_en.properties thanks for Mr Kiswani
```

When I remove the dependencies from the pom.xml and run the same code I will get 
    
```java
    LogFactory.getLogger(LogFactory.class).info("LOCALIZED_MSG", "Kiswani");
    
    Output:
    
    Nov 22, 2016 7:00:15 PM io.github.rhkiswani.javaff.log.DefaultLog info
    INFO: this is localized msg from messages_en.properties thanks for Mr Kiswani
 ```

- Transparent localization for logs, strings, exceptions
 
- Centralized and Configurable Exception Handling by the class type. See below a full example:

```java
    
    package io.github.rhkiswani.javaff;
    
    import io.github.rhkiswani.javaff.exceptions.ExceptionHandler;
    import io.github.rhkiswani.javaff.exceptions.ExceptionHandlersFactory;
    import io.github.rhkiswani.javaff.exceptions.ExceptionUtil;
    
    public class TestMain {
    
        public static void main(String[] args) {
            //Any instance of ConsoleException will be handled here
            ExceptionHandlersFactory.instance().add(ConsoleException.class, new ExceptionHandler() {
                @Override
                public void handle(Throwable t) {
                    System.out.println("ConsoleException handler");
                }
            });
    
            //Any instance of MailException will be handled here
            ExceptionHandlersFactory.instance().add(MailException.class, new ExceptionHandler() {
                @Override
                public void handle(Throwable t) {
                    System.out.println("MailException handler");
                }
            });
    
    
            ExceptionUtil.handle(new ConsoleException());
            ExceptionUtil.handle(new SubConsoleException());
            ExceptionUtil.handle(new MailException());
            ExceptionUtil.handle(new SubMailException());
    
            //Null is not related to any class from the previous class's, it will be handled by the default handler for Throwable.class
            //which is printing the stack trace by default
            ExceptionUtil.handle(new NullPointerException());
            
            //We decided to override the default implantation for Throwable.class
            ExceptionHandlersFactory.instance().overrideImp(Throwable.class, new ExceptionHandler() {
                @Override
                public void handle(Throwable t) {
                    System.out.println("Overridden handler");
                }
            });
    
            ExceptionUtil.handle(new NullPointerException());
        }
    
        private static class ConsoleException extends RuntimeException {
    
        }
    
        private static class SubConsoleException extends ConsoleException {
    
        }
    
        private static class MailException extends RuntimeException {
    
        }
    
        private static class SubMailException extends MailException {
    
        }
    
    }

```

- Logging with localization out of the box

```java
    LogFactory.getLogger(LogFactory.class).info("LOCALIZED_MSG", "Kiswani");
    
    Output : 
    INFO: this is localized msg from messages_en.properties thanks for Mr Kiswani
``` 

```java
    LogFactory.getLogger(LogFactory.class).info("normal msg num {0} date {1}", Integer.MAX_VALUE, new Date());
    
    Output:
    INFO: normal msg num 2,147,483,647 date 11/22/16 6:06 PM
``` 

- Many Utilities, see below just a few examples:
    - Formatter's
    ```java
        System.out.println(FormatUtil.format("Mr {0} {1}", "Mohamed", "Kiswani"));
        System.out.println(FormatUtil.format(new Date()));
        System.out.println(FormatUtil.format(new Date(), "yyyy-MM-dd"));
        System.out.println(FormatUtil.format(Integer.MAX_VALUE));
        
        Output:
        Mr Mohamed Kiswani
        11/22/16 6:16 PM
        2016-11-22
        2,147,483,647
    ``` 
 
    - Json Handlers
      ```java
        package javaff.samples.beans;
        import io.github.rhkiswani.javaff.json.JsonHandler;
        import io.github.rhkiswani.javaff.json.JsonHandlerFactory;

        public class Main {

            public static void main(String[] args) {
                Employee emp = new Employee();
                emp.setId(100);
                emp.setName("Kiswani");
                emp.setEmpId(1000);

                // comment jakson in pom.xml
                System.out.println("\n======================= Default based on class path ========================");
                // default json handler gson
                System.out.println(JsonHandlerFactory.getJsonHandler(Employee.class).toJson(emp));
                System.out.println(JsonHandlerFactory.getJsonHandler(Employee.class).getImplementation());


                // when you uncomment jakson from the pom.xml
                System.out.println(JsonHandlerFactory.getJsonHandler(Employee.class).toJson(emp));
                System.out.println(JsonHandlerFactory.getJsonHandler(Employee.class).getImplementation());

                System.out.println("\n======================= Force to use Gson By annotation ========================");
                // you want to have jakson for all the classes except the student , student should use Gson
                // you need to add @GsonBean
                Student std = new Student();
                std.setId(100);
                std.setName("Kiswani");
                std.setStdId(1000);
                System.out.println(JsonHandlerFactory.getJsonHandler(Student.class).toJson(emp));
                System.out.println(JsonHandlerFactory.getJsonHandler(Student.class).getImplementation());

                System.out.println("\n======================= Custom Json Handler ========================");
                // you want to add your custom implantation
                // Note : if you set your custom it will be used everywhere
                JsonHandlerFactory.instance().setDefault(new MyJsonHandler());

                System.out.println(JsonHandlerFactory.getJsonHandler(Employee.class).toJson(emp));
                System.out.println(JsonHandlerFactory.getJsonHandler(Employee.class).getImplementation());

                System.out.println(JsonHandlerFactory.getJsonHandler(Student.class).toJson(emp));
                System.out.println(JsonHandlerFactory.getJsonHandler(Student.class).getImplementation());
            }

            private static class MyJsonHandler implements JsonHandler {
                    public <T> T fromJson(String s, Class aClass) {
                        return null;
                    }

                public String toJson(Object o) {
                    return o.toString();
                }

                public Object getImplementation() {
                    return MyJsonHandler.class;
                }
            }
            
            package javaff.samples.beans;

            import io.github.rhkiswani.javaff.json.annotations.GsonBean;

            @GsonBean
            public class Student extends Person{

                private int stdId;

                public int getStdId() {
                    return stdId;
                }

                public void setStdId(int stdId) {
                    this.stdId = stdId;
                }
            }
        }
       ```  

