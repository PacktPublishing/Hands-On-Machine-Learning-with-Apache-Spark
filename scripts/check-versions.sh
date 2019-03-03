#!/usr/bin/env bash

echo -n "Java version ... "
JAVA=$(java -version 2>&1 | sed -n ';s/.* version "\(.*\)\.\(.*\)\..*"/\1.\2/p;' | bc -l)
if (( $(echo "$JAVA < 1.8" | bc -l) )); then echo "$JAVA is too old"; else echo "OK"; fi

echo -n "Spark version ... "
SPARK=$(spark-shell --version 2>&1 | sed -n ';s/.* version \([0-9]*\)\.\([0-9]*\)\.[0-9]*$/\1.\2/p;' | bc -l)
if (( $(echo "$SPARK < 2.4" | bc -l) )); then echo "$SPARK is too old"; else echo "OK"; fi

echo -n "Scala version ... "
SCALA=$(scala -version 2>&1 | awk '{print $5}' | sed -n ';s/\(.*\)\.\(.*\)\..*/\1.\2/p;' | bc -l)
if (( $(echo "$SCALA < 2.11" | bc -l) )); then echo "$SCALA is too old"; else echo "OK"; fi

echo -n "Python version ... "
PYTHON=$(python3 --version 2>&1| awk '{print $2}' |  sed -n ';s/\(.*\)\.\(.*\)\..*/\1.\2/p;' | bc -l)
if (( $(echo "$PYTHON < 3.4" | bc -l) )); then echo "$PYTHON is too old"; else echo "OK"; fi

echo -n "sbt version ... "
SBT=$(sbt sbtVersion 2>&1 | grep "info" | cat -v | awk '{ print  substr($2,11,length($2)-15) }' | sed -n ';s/\(.*\)\.\(.*\)\..*/\1.\2/p;'| bc -l)
if (( $(echo "$SBT < 1.0" | bc -l) )); then echo "$SBT is too old"; else echo "OK"; fi
