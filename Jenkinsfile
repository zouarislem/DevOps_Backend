pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                dir('DevOps_Backend/DevOps_Project/') {
                    sh 'mvn -B -DskipTests clean package'
                }
            }
        }
    }
}

