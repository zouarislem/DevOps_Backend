pipeline {
    agent any

    stages {
        stage('Build Backend (Spring Boot)') {
            steps {
                dir('DevOps_Project') {
                    sh 'mvn clean package'
                }
            }
        }
    }

    post {
        success {
            echo 'Backend build was successful.'
        }
        failure {
            echo 'Backend build failed. Please check the logs for details.'
        }
    }
}
