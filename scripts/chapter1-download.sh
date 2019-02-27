#!/usr/bin/env bash

echo "Goto datasets"
cd ${BASH_SOURCE%/*}/..
mkdir -p datasets
cd datasets

echo "Downloading Stack Overflow Survey Data"
curl -L https://rebrand.ly/mlspark-survey -o stackoverflow_survey_2018.zip
unzip stackoverflow_survey_2018.zip -d stackoverflow_survey_2018
rm stackoverflow_survey_2018.zip
rm mlspark-survey