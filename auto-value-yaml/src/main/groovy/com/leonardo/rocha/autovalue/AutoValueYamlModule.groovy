package com.leonardo.rocha.autovalue

import groovy.io.FileType
import org.gradle.api.Project
import org.gradle.api.tasks.Delete
import com.github.mustachejava.DefaultMustacheFactory
import com.github.mustachejava.Mustache
import com.github.mustachejava.MustacheFactory
import org.yaml.snakeyaml.Yaml

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

            doFirst {
                generateCode(project)
            } 
        }  

        project.task("cleanGenDirPlugin", type:Delete) {
            delete getGeneratedDir(project)
            delete "${project.getBuildDir()}/tmp"
        }

        project.apply plugin: 'java'
        project.tasks.generateCodePlugin.dependsOn(project.tasks.cleanGenDirPlugin)
        project.tasks.compileJava.dependsOn(project.tasks.generateCodePlugin)
        
    }

    private static String getGeneratedDir(Project project){
        return project.extensions.autoYamlExt.generatedDir ?: "${project.getBuildDir()}/generatecode/src/main/java"
    }

    private static void generateCode(Project project){
        String templatesDir = getTemplatesDir(project)        
        println(templatesDir)
        project.file(templatesDir).eachFileRecurse (FileType.FILES) {
           println(it.path)
        }
        String yamlDir = getYamlDir(project) 
        println(yamlDir)
        project.file(yamlDir).eachFileRecurse (FileType.FILES) {
           println(it.path)
        }

        project.file(templatesDir).eachFile { fileName ->
            def templateType = getTemplateType(fileName) 
            def templateYamlDir = "${yamlDir}/${templateType}"
            project.file(templateYamlDir).eachFile { yamlName ->
                def generatedFileInfo = generateFile("${fileName.absolutePath}", "${yamlName.absolutePath}")
                saveFile(generatedFileInfo, project)
            }
            def genFilesDir = getGeneratedDir(project)
            project.file(genFilesDir).eachFileRecurse (FileType.FILES) {
                println(it.path)
            }
        } 
    }

    private static String getTemplatesDir(Project project){
        return "${project.getProjectDir()}/src/main/resources/templates"
    } 

    private static String getYamlDir(Project project){
        return project.extensions.autoYamlExt.yamlDir ?: "${project.getProjectDir()}/src/main/resources/schemas"
    }

    private static def getTemplateType(def fileName){
        return fileName.getName().split(/\./)[0]
    }

    private static Tuple2 generateFile(def templateFile, def yamlFile){
        Map<String, Object> map = convertYamlToMap(yamlFile)
        Mustache mustache = compileTemplate(templateFile);
        def generatedString = createTemplateInstance(mustache, map)
        def canonicalName = getGeneratedClassName(map)
        return new Tuple2(canonicalName, generatedString)
    }

    private static def convertYamlToMap(def yamlFile){
        InputStream input = new FileInputStream(new File(yamlFile));
        Yaml yaml = new Yaml();
        return (Map<String, Object>) yaml.load(input);
    }

    private static def compileTemplate(def templateFile){
        MustacheFactory mf = new DefaultMustacheFactory();
        File f = new File(templateFile);
        return mf.compile(new InputStreamReader(new FileInputStream(f),"UTF-8"),f.getName());
    }

    private static def createTemplateInstance(def mustache, def yamlInfo){
        StringWriter writer = new StringWriter();
        mustache.execute(writer, yamlInfo).flush();
        return writer.toString()
    }

    private static def getGeneratedClassName(def map){
        return "${map.get('basePackage')}.${map.get('relativePackage')}.${map.get('beanName')}";
    }

    private static def saveFile(def tuple, Project project){
        def path = getGeneratedFilePath(tuple) 
        def fileName = getGeneratedModelName(path) 
        def relativePathDir = getGeneratedFileRelativePath(path, fileName)
        def fileDir =  project.file("${getGeneratedDir(project)}/${relativePathDir}")
        String genCode = tuple[1]
        createDir(fileDir)
        project.file("${fileDir}/${fileName}.java").text = genCode
        def autoValueDir = getAutoValueDir(project)
        saveGenFileInfo(project,"${autoValueDir}/${relativePathDir}/AutoValue_${fileName}.java", "${fileDir}")
    }

    private static getGeneratedFilePath(def tuple) {
        return ((GString) tuple[0]).replace(".","/")
    }

    private static getGeneratedModelName(def path){
        return path.split(/\//).last()
    }

    private static getGeneratedFileRelativePath(def path, def fileName) {
        return (path.split(/\//) - fileName).join('/')
    }

    private static def createDir(def fileDir){
        if(!fileDir.exists()){
            fileDir.mkdirs()
        }
    }

    private static getAutoValueDir(Project project){
        return "${project.getBuildDir()}/classes/java/main"
    }

    private static def saveGenFileInfo(Project project, def filePath, def dest) {
        File newDir = new File("${project.getBuildDir()}/tmp/")
        createDir(newDir)
        updateFile("${project.getBuildDir()}/tmp/AutoValueFile.txt", "${filePath},${dest}\n")
    }

    private static def updateFile(def fileName, def fileContent){
        def fileToBeUpdated = new File(fileName)
        if(!fileToBeUpdated.exists()){
            fileToBeUpdated.text = fileContent
        } else {
            fileToBeUpdated.text += fileContent
        }
    }
}
