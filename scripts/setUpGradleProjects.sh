#!/bin/bash

function isGradleProject(){
    [[ -d "$1" && "$1" != "commonGradleFiles" && "$1" != "scripts" ]]
}

function listGradleProjects(){
    for f in * ;
        do isGradleProject $f && 
	   echo $f ; 
    done
}

function copyGradleFilesToProject() {
    for f in * ;
        do isGradleProject $f &&
           cp -r commonGradleFiles/* $f ;
    done
}

echo "Moving files from commonGradleFiles to the following directories"
listGradleProjects
copyGradleFilesToProject
