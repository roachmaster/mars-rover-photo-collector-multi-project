1. Convert CodeGenerator to a gradle plugin
	move the gradle plugin to it's own dir
	Call plugin AutoValueYaml
	possibly get it published 

2. Create sub project called models that will only have the model yamls
3. Move sping boot app to a subproject that depends on the models directory
4. Create a Docker Subproject that will create a archive the image
5. Create a helm Subproject that will contain the helm charts to deploy the docker container
6. Create a UI sub project
