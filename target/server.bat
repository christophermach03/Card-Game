@echo off
REM Navigate to the folder where this BAT file is located
cd /d "%~dp0"

REM Run the server JAR
java -jar ExplodingPuppiesServer-1.0-SNAPSHOT.jar

REM Keep the terminal open after exit
pause
