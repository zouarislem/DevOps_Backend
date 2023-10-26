pipeline {
    agent any

        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }


}
