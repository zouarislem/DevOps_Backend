pipeline {
    agent any
    
stage('Build Artifact') {
			steps {
				sh "mvn clean package -DskipTests=true"
				archive'target/*.jar'
			}
		}

}
