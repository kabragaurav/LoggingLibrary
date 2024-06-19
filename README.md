# Logging Library 
by Gaurav Kabra

## Problem Statement
![](./assets/images/logger_lib.png)

## Design
[Inspired from `log4j` and `slf4j`]

![UML](./assets/images/LoggingLib.svg)

1. `Sink` is not concrete. `ConsoleSink` and `TextFileSink` are.
2. `Logger` is using **singleton design pattern** so that multiple loggers do not overwrite.
3. We can use **factory design pattern** to create different loggers
 - In that case remove `sink` from `Logger.java` and make it asbtract
 - Then create two sub classes: `ConsoleLogger` and `TextFileLogger`
4. It is requirement that for any level, all higher level logs must also be logged
 - We have used enum's ability to get higher levels using `getHigherOrEqualLevels()` method
 - In general, we can use **chain of responsibility (cor) design pattern** where we create different levels logger and set next logger. Once one logger is done, it delegates to next logger.
 - But in our impl, cor will lead to complexity - extensibility wise if new level is introduced, we need to create new loggers and set next logger, and if level is between two existing loggers, existing classes will need changes to accommodate new logger as next logger

## Setup
Will require `JUnit5.8.1` to run tests.