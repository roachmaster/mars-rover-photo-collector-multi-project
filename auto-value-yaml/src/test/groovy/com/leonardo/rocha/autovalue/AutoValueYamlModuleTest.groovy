package com.leonardo.rocha.autovalue

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.*
import org.junit.rules.TemporaryFolder
import org.apache.commons.io.FileUtils
import static org.gradle.testkit.runner.TaskOutcome.*

/**
 * Contains functional tests that use the GradleRunner to run the plugin's task in a controlled environment.
 * Reference:
 * https://docs.gradle.org/5.5.1/userguide/test_kit.html#sec:functional_testing_with_the_gradle_runner
 */
class AutoValueYamlModuleTest {
    @Rule
    public final TemporaryFolder testProjectDir = new TemporaryFolder();

    private File build_gradle

    @Before
    public void setup() {
        // Prepare build.gradle
        build_gradle = testProjectDir.newFile('build.gradle')
        build_gradle << 'plugins { id "com.leonardo.rocha.AutoValueYamlPlugin" }\n'
        def templatesDir = testProjectDir.newFolder("src/main/resources/templates")
        def yamlDir = testProjectDir.newFolder("src/main/resources/schemas")
        copyResourcesToTestDir(templatesDir, yamlDir)
    }

    private void copyResourcesToTestDir(def templatesDir, def yamlDir){
        ClassLoader classLoader = getClass().getClassLoader();
        def testTemplatesDir = new File(classLoader.getResource("templates").getFile());
        def testYamlDir = new File(classLoader.getResource("schemas").getFile());
        System.out.println(testTemplatesDir.getAbsolutePath());
        System.out.println(testYamlDir.getAbsolutePath());
        try {
            FileUtils.copyDirectory(testTemplatesDir, templatesDir);
            FileUtils.copyDirectory(testYamlDir, yamlDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method that runs a Gradle task in the testProjectDir
     * @param arguments the task arguments to execute
     * @param isSuccessExpected boolean representing whether or not the build is supposed to fail
     * @return the task's BuildResult
     */
    private BuildResult gradle(boolean isSuccessExpected, String[] arguments = ['tasks']) {
        arguments += '--stacktrace'
        def runner = GradleRunner.create()
                .withArguments(arguments)
                .withProjectDir(testProjectDir.root)
                .withPluginClasspath()
                .withDebug(true)
        return isSuccessExpected ? runner.build() : runner.buildAndFail()
    }

    private BuildResult gradle(String[] arguments = ['tasks']) {
        gradle(true, arguments)
    }

    @Test
    public void generateCodePlugin_standard() {
        def result = gradle('generateCodePlugin')
        assert result.task(":generateCodePlugin").outcome == SUCCESS
        println(result.output)
        assert result.output.contains("src/main/resources/templates")
        assert result.output.contains("src/main/resources/schemas")
    }
/*
    @Test
    public void helloWorld_alternative() {
        build_gradle << 'autovalueyaml.alternativeGreeting = "Howdy"'

        def result = gradle('helloWorld')

        assert result.task(":helloWorld").outcome == SUCCESS
        assert result.output.contains("Howdy, world!")
    }

    @Test
    public void helloTarget_standard() {
        def result = gradle('helloTarget')

        assert result.task(":helloTarget").outcome == SUCCESS
        assert result.output.contains("Hello, default-user!")
    }

    @Test
    public void helloTarget_option() {
        def result = gradle('helloTarget', '-Ptarget=Robert')

        assert result.task(":helloTarget").outcome == SUCCESS
        assert result.output.contains("Hello, Robert!")
    }

    @Test
    public void greetingTask_standard() {
        build_gradle << """
            task goodTask(type: AutoValueYamlTask) {
                message = "Ahoy"
                target = "William"
            }
            """

        def result = gradle('goodTask')

        assert result.task(":goodTask").outcome == SUCCESS
        assert result.output.contains("Ahoy, William!")
    }

    @Test
    public void greetingTask_broken() {
        build_gradle << """
            task badTask(type: AutoValueYamlTask) {
                message = "Goodbye"
                target = "William"
            }
            """

        def result = gradle(false, 'badTask')

        assert result.task(":badTask").outcome == FAILED
        assert result.output.contains("I can't let you do that, Starfox.")
    }

   */
}
