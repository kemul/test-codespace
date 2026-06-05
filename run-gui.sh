#!/bin/bash
# Quick start script for CRUD GUI Application

echo "======================================"
echo "  CRUD GUI Application - Quick Start"
echo "======================================"
echo ""

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed. Please install Maven first."
    exit 1
fi

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 11 or higher."
    exit 1
fi

echo "✓ Prerequisites found"
echo ""

# Clean and build
echo "Building the project..."
mvn clean install -q

if [ $? -ne 0 ]; then
    echo "❌ Build failed. Please check the error messages above."
    exit 1
fi

echo "✓ Build successful"
echo ""

# Run the GUI application
echo "Launching CRUD GUI Application..."
echo ""
mvn javafx:run

