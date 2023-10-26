pipeline {
    agent any
    stages {
         stage('Build - Node.js Application') {
            steps {
                dir('DevOps_FrontEnd/') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }
        stage('Build - Java Application') {
            steps {
                dir('DevOps_Project/') {
                    sh 'mvn -B -DskipTests clean package'
                }
            }
        }
       
    }
}
