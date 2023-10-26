pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }
  stage("Compilation") {
    sh "./mvnw clean install -DskipTests"
  }

  stage("Tests and Deployment") {
    stage("Runing unit tests") {
      sh "./mvnw test -Punit"
    }
}
