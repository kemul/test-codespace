@echo off
REM Quick start script for CRUD GUI Application (Windows)

echo ======================================
echo   CRUD GUI Application - Quick Start
echo ======================================
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo X Maven is not installed. Please install Maven first.
    exit /b 1
)

REM Check if Java is installed
where java >nul 2>nul
if %errorlevel% neq 0 (
    echo X Java is not installed. Please install Java 11 or higher.
    exit /b 1
)

echo OK Prerequisites found
echo.

REM Clean and build
echo Building the project...
call mvn clean install -q

if %errorlevel% neq 0 (
    echo X Build failed. Please check the error messages above.
    exit /b 1
)

echo OK Build successful
echo.

REM Run the GUI application
echo Launching CRUD GUI Application...
echo.
call mvn javafx:run

