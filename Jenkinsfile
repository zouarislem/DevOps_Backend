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
            when {
                // Cette étape ne s'exécute que si l'étape précédente a réussi
                expression { currentBuild.resultIsBetterOrEqualTo('SUCCESS') }
            }
            steps {
                dir('DevOps_Project/') {
                    sh 'mvn -B -DskipTests clean package'
                }
            }
        }
    }
}
