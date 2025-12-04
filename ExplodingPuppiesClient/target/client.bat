@echo off
REM Navigate to the folder where this BAT file is located
cd /d "%~dp0"

REM Run the client JAR
java -jar ExplodingPuppiesClient-1.0-SNAPSHOT.jar

REM Keep the terminal open after the program exits
pause
