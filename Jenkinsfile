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
                sh 'cd lib/ : wget https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.0/junit-platform-console-standalone-1.7.0-all.jar'
                sh 'cd src/org/psnbtech/util/ : javac -cp "../lib/junit-platform-console-standalone-1.7.0-all.jar" Clock.java ClockTest.java'
            }
        }

        stage('Test') {
            steps {
                sh 'cd src/org/psnbtech/util/ : java -jar ../lib/junit-platform-console.standalone-1.7.0-all.jar -cp "." --select-class ClockTest --reports-dir="reports"'
                junit 'src/reports/*-jupiter.xml'
            }
        }

        stage('Deploy') {
            steps {
                sh 'cd src/org/psnbtech/ : java Game.java'
            }
        }
    }
    post {
        changed {
            script {
                always {
               // if (currentBuild.currentResult == 'FAILURE') { // Other values: SUCCESS, UNSTABLE
                    // Send an email only if the build status has changed from green/unstable to red
                    emailext subject: '$DEFAULT_SUBJECT',
                        body: '$DEFAULT_CONTENT',
                        recipientProviders: [
                            [$class: 'CulpritsRecipientProvider'],
                            [$class: 'DevelopersRecipientProvider'],
                            [$class: 'RequesterRecipientProvider']
                        ],
                        replyTo: '$DEFAULT_REPLYTO',
                        to: '$DEFAULT_RECIPIENTS'
                }
            }
        }
    }

}