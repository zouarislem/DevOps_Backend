pipeline {
    agent any

    environment {
        MAVEN_HOME = tool name: 'Maven', type: 'Maven'
        JAVA_HOME = tool name: 'JDK', type: 'JDK'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh "${tool(name: 'JDK', type: 'JDK')}/bin/java -version"
                sh "${tool(name: 'Maven', type: 'Maven')}/bin/mvn clean install"
            }
        }

        stage('Unit Tests') {
            steps {
                sh "${tool(name: 'Maven', type: 'Maven')}/bin/mvn test" // Execute unit tests
            }
        }

        stage('Package') {
            steps {
                sh "${tool(name: 'Maven', type: 'Maven')}/bin/mvn package"
            }
        }

        stage('Deploy') {
            steps {
                sh 'ssh user@server "cd /path/to/deployment/directory && ./deploy.sh"'
            }
        }
    }

    post {
        success {
            echo 'The pipeline succeeded!'
        }
        failure {
            echo 'The pipeline failed.'
        }
    }
}
