## Prerequisites

### Java
Please verify if you have downloaded and installed Java.

Open Console/Terminal and execute following command:
```
java --version
```

Output should be similar to (depending on Java version installed on computer): 
```
java 9.0.4
Java(TM) SE Runtime Environment (build 9.0.4+11)
Java HotSpot(TM) 64-Bit Server VM (build 9.0.4+11, mixed mode)
```

If nothing was returned please install Java and verify JAVA_HOME path:

[JAVA_HOME](https://java.com/en/download/help/path.xml)

[Java 9 Installation](https://docs.oracle.com/javase/9/install/overview-jdk-9-and-jre-9-installation.htm)

### Maven
[Maven installation](https://maven.apache.org/install.html)

## Running tests
1. Checkout code from this repository.
2. There are 2 modes of running those:
  - headless 
    - Go to BaseTest class and set testchromeOptions.setHeadless(true)
  - inside Chrome browser 
    - Download and setup [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/getting-started)
    - Go to BaseTest class chromeOptions.setHeadless(false)
    - Set CHROMEDRIVER_PATH e.g. 
      ```
      CHROMEDRIVER_PATH = "/Users/Wojtek/chromedriver/chromedriver";
      ```
3. Open Console/terminal.
4. Execute following command: 
 ```
 mvn clean test -Dsurefire.suiteXmlFiles=[PATH_TO_TEST_SUITE]
 e.g.
 mvn clean test -Dsurefire.suiteXmlFiles=./src/main/java/pro/klos/testing/suites/WikipediaTestSuite.xml
 mvn clean test -Dsurefire.suiteXmlFiles=./src/main/java/pro/klos/testing/suites/TravelexTestSuite.xml
 ```

## Notes
I was following Page Object Model(POM) approach. 
Which helps to make the code more readable, maintainable and reusable.
Each web page has corresponding page class, which contains WebElements of that page 
and methods to perform operations on them. 
Operations on UI are separated from verification. Page objects are independent of test cases.

Libraries used:
 - Maven + Surefire Plugin (project structure and HTML reports (inside /target directory),
 - TestNG (suites, assertions),
 - ChromeDriver (as Chrome is the most popular browser).
 
 
Coded and executed on MacOS.

WKlos
