pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                dir('DevOps_Project/') {
                    sh 'mvn -B -DskipTests clean package'
                }
            }
        }
    }
}

