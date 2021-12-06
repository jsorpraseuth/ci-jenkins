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
                sh 'cd lib/ : sudo wget https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.0/junit-platform-console-standalone-1.7.0-all.jar'
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
        always {
            emailext body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
                subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}",
                to: "sorpr002@cougars.csusm.edu"
        }
    }

}