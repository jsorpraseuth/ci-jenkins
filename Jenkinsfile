pipeline {

    agent any
    stages {

        stage('Checkout Codebase') {
            steps {
                cleanWs()
                checkout scm: [$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[credentialsId: 'github-ssh-key', url: 'git@github.com:jsorpraseuth/ci-jenkins.git']]]
            }
        }

        stage('Build') {
            steps {
                sh 'mkdir lib'
                sh 'cd lib/ : wget https://repol.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.0/junit-platform-console-standalone-1.7.0-all.jar'
                sh 'cd src/org/psnbtech/ : javac -cp "../lib/junit-platform-console-standalone-1.7.0-all.jar" WorldPanel.jav Game.java GameTest.java'
            }
        }

        stage('Test') {
            steps {
                sh 'cd src/org/psnbtech/ : java -jar ../lib/junit-platform-console.standalone-1.7.0-all.jar -cp "." --select-class GameTest --reports-dir="reports"'
            }
        }

        stage('Deploy') {
            steps {
                sh 'cd src/org/psnbtech/ : java Game'
            }
        }

    }

}