pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh './mvnw build'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw check'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
            junit 'build/reports/**/*.xml'
        }
    }
}