#!/usr/bin/env bash

if ! type unzip > /dev/null 2>1; then
    echo "'unzip' not installed. Use 'brew install unzip' (Mac)"
    exit 1
fi
if ! type curl > /dev/null 2>1; then
    echo "'curl' not installed. Use 'brew install curl' (Mac)"
    exit 1
fi

echo "Goto datasets"
cd ${BASH_SOURCE%/*}/..
mkdir -p datasets
cd datasets

echo "Downloading Stack Overflow Survey Data from https://insights.stackoverflow.com/survey"
curl -L "http://packt.lous.info/mlspark-survey" -o stackoverflow_survey_2018.zip
unzip -o stackoverflow_survey_2018.zip -d stackoverflow_survey_2018
rm stackoverflow_survey_2018.zip 2> /dev/null
rm mlspark-survey 2> /dev/null