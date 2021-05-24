#!/bin/bash
source ./scripts/commonScriptFunctions.sh

function cleanEclipseProject() {
    currentDir=$(pwd)

    for f in * ; 
    do 
        if isGradleProject $f; then
            cd $f
	    pwd
	    if isEclipseProject; then
	        ./gradlew clean cleanEclipse
		rm -rvf bin
            fi
	    cd $currentDir
	fi
    done
}

echo Cleaning up the following Eclipse projects
listGradleProjects
cleanEclipseProject
