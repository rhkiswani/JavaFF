JavaFF: Java Facade/Factories
=============================

[![Build Status](https://travis-ci.org/rhkiswani/JavaFF.svg?branch=develop)](https://travis-ci.org/rhkiswani/JavaFF)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.rhkiswani/javaff.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22io.github.rhkiswani%22)

We all know the Golden Object Oriented rule **Don't talk to strangers**

We all know that you will never find the API/frameworks defects or magic or limitations the at begging of the development.
 
Imagine yourself using API in all your projects for years. Everybody else is using it as well, it is famous and mature. Suddenly you have a huge production issue.  Changing that API is too hard and costly at this stage! 

What now?

Or you decided to change any API/framework for new requirments.
Lets take  logging as an example, now we have log4j, logback, JDK logging.
At the beginning of the project you decide to go with the logback since it's so famous and faster than log4j and it's bundled with Spring , etc.

After 1 year of the project you got a new requirements for the logging which are:
- All logs should be localized 
- FATAL errors should be sent to a specific email 

Disaster right !!

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

Main Features
-------------- 
- This project offers a standard/clear API for the most used API's in the Java Applications like : Exceptions, Locale, Beans, Formatter's, Json Handlers, Loggers, ReflectionHelpers, etc. 

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
 
- Centralized and Configurable Exception Handling by the class type below a full example  
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
    
        private static class ConsoleException extends RuntimeException{
    
        }
    
        private static class SubConsoleException extends ConsoleException{
    
        }
    
        private static class MailException extends RuntimeException{
    
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

- Many Utilities, below is just examples
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
            System.out.println(JsonHandlerFactory.getJsonHandler(TestMain.class).toJson(new Employee(1000)));
            output : {"id":0,"name":null,"empId":1000}
       ``` 
       
      ```java
            System.out.println(JsonHandlerFactory.getJsonHandler(TestMain.class).fromJson("{\"id\":100,\"name\":null,\"empId\":1000}", Employee.class));
            output: Employee[id=100]   
       ```  

Prerequisites 
-------------
Requires JDK 1.7 or higher.

Usage & Examples 
-------------
[Usage & Examples](https://github.com/rhkiswani/JavaFF-Samples/tree/master)
