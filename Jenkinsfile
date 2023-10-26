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
        stage('Build - Node.js Application') {
            steps {
               
                    sh 'npm install'  // Exécutez les commandes Node.js appropriées
                    sh 'npm run build'
            }
        }
    }
}

