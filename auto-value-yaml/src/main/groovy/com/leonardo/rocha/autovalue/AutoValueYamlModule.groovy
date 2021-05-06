package com.leonardo.rocha.autovalue

import org.gradle.api.Project
import org.gradle.api.tasks.Delete

/**
 * The "module" isn't anything Gradle specific.
 * It's just a way of grouping tasks and configuration that share a certain theme.
 * The module's "load" method is called in the plugin's entry point at {@link com.praqma.demo.DemoPlugin}
 */
class AutoValueYamlModule {
    static void load(Project project) {
        /*
        * Register a 'greeting' extension, with the properties defined in GreetingExtension
        * Reference:
        * https://docs.gradle.org/5.5.1/userguide/build_environment.html#sec:gradle_configuration_properties
        */
        project.extensions.create("autoYamlExt", AutoValueYamlExtension)

        /*
        * Clever trick so users don't have to reference a custom task class by its fully qualified name.
        * Reference:
        * https://discuss.gradle.org/t/how-to-create-custom-gradle-task-type-and-not-have-to-specify-full-path-to-type-in-build-gradle/6059/4
        */
        //project.ext.AutoValueYamlTask = AutoValueYamlTask

        /*
        * A task that uses an extension for configuration.
        * Reference:
        * https://docs.gradle.org/5.5.1/userguide/custom_plugins.html#sec:getting_input_from_the_build
        */
        project.task('generateCodePlugin') {
            group = "AutoValueYaml"
            description = "Generates the Autovalue Yaml into java code. If AltYamlDir is set it will use that location"

            String generatedDir = getGeneratedDir(project) 
            String templatesDir = getTemplatesDir(project) 
            String yamlDir = getYamlDir(project) 

            doLast {
                println "Placing generating Code in ${generatedDir}"
                println "AutoValue templates are in ${templatesDir}"
                println "Autovalue Schemas are in ${yamlDir}"
            }

/*            doFirst {
                file(templatesDir).eachFile { fileName ->
                    def templateType = fileName.getName().split(/\./)[0]
                    def templateYamlDir = "${yamlDir}/${templateType}"
                    file(templateYamlDir).eachFile { yamlName ->
                        def pair = generateFile "${fileName.absolutePath}", "${yamlName.absolutePath}"
                        saveFile pair
                    }  
                }
            } 
*/
        }  

        project.task("cleanGenDir", type:Delete) {
            delete getGeneratedDir(project)
            delete "${project.getBuildDir()}/tmp"
        }

        project.apply plugin: 'java'
        project.tasks.generateCodePlugin.dependsOn(project.tasks.cleanGenDir)
        project.tasks.compileJava.dependsOn(project.tasks.generateCodePlugin)
        
    }

    private static String getGeneratedDir(Project project){
        return project.extensions.autoYamlExt.generatedDir ?: "${project.getBuildDir()}/generatecode/src/main/java"
    }

    private static String getTemplatesDir(Project project){
        return "${project.getProjectDir()}/src/main/resources/templates"
    } 

    private static String getYamlDir(Project project){
        return project.extensions.autoYamlExt.yamlDir ?: "${project.getProjectDir()}/src/main/resources/schemas"
    }
}
