pipeline {

	agent any

  stage("Clone the project") {
    git branch: 'backend', url: 'https://github.com/zouarislem/DevOps_Backend.git'
  }

  stage("Compilation") {
    sh "./mvnw clean install -DskipTests"
  }
}
