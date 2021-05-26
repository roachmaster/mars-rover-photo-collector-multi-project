#!/bin/bash

source ./scripts/commonScriptFunctions.sh

echo "Removing commonGradleFiles from the following directories"
listGradleProjects
rmGradleFilesToProject
