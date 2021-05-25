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
    project = "mars-rover-photo-collector-models"
    stage("Building ${project} gradle project"){
        buildGradleProject name: project
    }
    stage("Publishing ${project} maven artifacts"){
        publishMavenArtifacts name: project
    }
    project = "mars-rover-photo-collector-app"
    stage("Building ${project} gradle project"){
        buildGradleProject name: project
    }
    stage("Publishing ${project} maven artifacts"){
        publishMavenArtifacts name: project
    }
    project = "mars-rover-photo-collector-docker"
    stage("Building ${project} docker image"){
        buildDockerProject name: project
    }
}
