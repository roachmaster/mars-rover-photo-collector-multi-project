node {
    stage("Setting Up Gradle Environment"){
        setUpGradleProjects name:"test"
    }
    def project = "auto-value-yaml"
    stage("Building ${project} gradle project"){
        buildGradleProject name: project
    }
    stage("Publishing ${project} maven artifacts"){
        publishMavenArtifacts name: project
    }
}
