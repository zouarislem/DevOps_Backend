pipeline {

	agent any

  stage("Clone the project") {
    git branch: 'main', url: 'https://github.com/nkchauhan003/jenkins-demo.git'
  }

  stage("Compilation") {
    sh "./mvnw clean install -DskipTests"
  }
}
