package com.leonardo.rocha

import com.leonardo.rocha.autovalue.AutoValueYamlModule
import org.gradle.api.Plugin
import org.gradle.api.Project

public class AutoValueYamlPlugin implements Plugin<Project>{
    void apply(Project project) {
        AutoValueYamlModule.load(project)
    }
}
