#!/bin/bash
source ./scripts/commonScriptFunctions.sh

function setUpEclipseProject() {
    currentDir=$(pwd)

    for f in * ; 
    do 
        if isGradleProject $f; then
            cd $f
	    pwd
	    if isEclipseProject; then
	        ./gradlew clean cleanEclipse
	        ./gradlew build eclipse
            fi
	    cd $currentDir
	fi
    done
}

echo Setting up the following projects for gradle
listGradleProjects
rmGradleFilesToProject
copyGradleFilesToProject
setUpEclipseProject
