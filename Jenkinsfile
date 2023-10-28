pipeline {
    agent any

    stages {
        stage('Build Backend (Spring Boot)') {
            steps {               
                    sh 'mvn clean package'                
            }
        }
    }
}
