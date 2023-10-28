pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from your version control system (e.g., Git)
                checkout scm
            }
        }

        stage('Build Spring Boot App') {
            steps {
                // Use Apache Maven to build the Spring Boot application
                sh 'mvn clean package'
            }
        }
    }

    post {
        success {
            // Add post-build actions here, such as archiving artifacts
            archiveArtifacts(allowEmptyArchive: true, artifacts: 'target/*.jar')
        }
    }
}

