pipeline {
    agent any

    stages {
        stage('Build Backend (Spring Boot)') {
            steps {
                script {
                    // Define any specific environment setup for the backend build
                    sh 'DevOps_Backend/DevOps_Project'  // Navigate to the Spring Boot project directory
                    sh 'mvn clean package'    // Use Maven to build the Spring Boot application
                }
            }
        }

        stage('Build Frontend (Angular)') {
            steps {
                script {
                    // Define any specific environment setup for the frontend build
                    sh 'https://github.com/zouarislem/DevOps_FrontEnd.git'   // Navigate to the Angular project directory
                    sh 'npm install'             // Install Angular project dependencies
                    sh 'ng build --prod'         // Build the Angular frontend application
                }
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

