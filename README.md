Programming Exercise – RPN Calculator Solution
===============================

Code Quality
------------
- Using TestNG for unit tests.
- Using Jacoco for code coverage. The code coverage report can be found at target/site/jacoco after mvn test.
- Using FindBugs for code quality.


Code Structures
---------------
There are following classes:

1. Command and XXXCommand: each operation is a Command class. Using TwoOperandsCommand as base class for + - * /
2. CommandReader to read commands from input stream (file or command line)
3. CommandFactory creates Command from string. This class maps command strings to Commands
4. Calculator keeps a stack of executed commands
5. CommandManager execute command on calculator, this class saves the execution history and able to undo commands.
6. CalculatorPrinter to print calculator stack and error messages


Designs
-------
1. Using command pattern to execute and undo
2. Using Babylonian method to calculate sqrt, because of that JDK does not have sqrt method for BigDecimal


Build And Run
--------------------
You can choose either way of the following methods to run the application:

1. By using IDE, you can open maven project by import pom.xml file in your favorite ide(Intellij Idea is suggested), run the Main class

2. To create a executable jar file with dependencies included. then you can run by:
```bash
    cd {PROJECT_ROOT_DIR}
    mvn clean package site
    java -jar target/calculator-1.0-SNAPSHOT-jar-with-dependencies.jar [command file path]
```

Assumptions
-----------
1. There has no requirements for invalid input, so I use slf4j to print logs, currently print to console.
2. The application can read from file if there has a argument when execute application, else read from console.
    The application will read until EOF of the input, or Ctrl+D if read from console.