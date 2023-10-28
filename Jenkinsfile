pipeline {
    agent any

  stage('Build Backend (Spring Boot)') {
    steps {
        dir('/DevOps_Project') {
            sh 'mvn clean package'
        }
    }
}

    
    post {
        success {
            echo 'Both backend and frontend builds were successful.'
        }
        failure {
            echo 'One or both builds failed. Please check the logs for details.'
        }
    }
}

