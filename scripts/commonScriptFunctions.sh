#!/bin/bash

function isGradleProject(){
    [[ -d "$1" && "$1" != "commonGradleFiles" && "$1" != "scripts" ]]
}

function isEclipseProject(){
    grep -q eclipse build.gradle
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
           cp -rfv commonGradleFiles/* $f ;
    done
}

function rmGradleFilesToProject() {
    for f in * ;
        do isGradleProject $f &&
            rm -rfv $f/gradle* && 
	    rm -rfv $f/.gradle;
    done
}

