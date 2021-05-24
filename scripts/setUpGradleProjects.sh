#!/bin/bash

source ./scripts/commonScriptFunctions.sh

echo "Moving files from commonGradleFiles to the following directories"
listGradleProjects
copyGradleFilesToProject
