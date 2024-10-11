pipeline {
    agent any
    tools {
        maven 'jenkins-maven'
    }

    stages {
        stage('Git Checkout') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/AdiMunawar31/cashier-app-sonarqube']])
                sh 'mvn clean install'
                echo 'Git Checkout Completed'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn clean package'
                    sh '''mvn clean verify sonar:sonar -Dsonar.projectKey=cashier-spring -Dsonar.host.url=http://localhost:9000'''
                    echo 'SonarQube Analysis Completed'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                waitForQualityGate abortPipeline: true
                echo 'Quality Gate Completed'
            }
        }
    }


}
