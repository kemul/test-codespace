#!/bin/bash

# Test script for CRUD Application
# Verifies all components are properly installed

echo "======================================"
echo "  CRUD Application - Build Verification"
echo "======================================"
echo ""

# Check Java version
echo "[1/4] Checking Java version..."
if command -v java &> /dev/null; then
    JAVA_VERSION=$(java -version 2>&1 | grep 'version' | head -1)
    echo "✓ $JAVA_VERSION"
else
    echo "✗ Java not found"
    exit 1
fi

# Check Maven
echo ""
echo "[2/4] Checking Maven..."
if command -v mvn &> /dev/null; then
    MVN_VERSION=$(mvn -v 2>&1 | head -1)
    echo "✓ $MVN_VERSION"
else
    echo "✗ Maven not found"
    exit 1
fi

# Build project
echo ""
echo "[3/4] Building project..."
mvn clean install -q
if [ $? -eq 0 ]; then
    echo "✓ Build successful"
else
    echo "✗ Build failed"
    exit 1
fi

# Verify files
echo ""
echo "[4/4] Verifying project structure..."
files=(
    "src/main/java/com/crud/model/User.java"
    "src/main/java/com/crud/repository/UserRepository.java"
    "src/main/java/com/crud/Main.java"
    "src/main/java/com/crud/ui/CrudGuiApplication.java"
    "src/main/java/com/crud/example/CrudExample.java"
    "pom.xml"
)

all_exist=true
for file in "${files[@]}"; do
    if [ -f "$file" ]; then
        echo "✓ $file"
    else
        echo "✗ $file not found"
        all_exist=false
    fi
done

echo ""
if [ "$all_exist" = true ]; then
    echo "======================================"
    echo "✓ All checks passed!"
    echo "======================================"
    echo ""
    echo "To run the GUI application:"
    echo "  mvn javafx:run"
    echo ""
    echo "To run the CLI application:"
    echo "  mvn exec:java -Dexec.mainClass=\"com.crud.Main\""
    echo ""
else
    echo "✗ Some files are missing"
    exit 1
fi

