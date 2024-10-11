pipeline {
    agent any
    tools {
        maven 'jenkins-maven'
    }

    stages {
        stage('Test Docker Access') {
            steps {
                sh 'docker --version'
            }
        }

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

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t adimunawar31/cashier-spring .'
                    echo 'Build Docker Image Completed'
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhub-password')]) {
                        sh '''docker login -u adimunawar31 -p "$dockerhub-password"'''
                    }
                    sh 'docker push adimunawar31/cashier-spring'
                }
            }
        }

        stage('Docker Run') {
            steps {
                script {
                    sh 'docker run -d --name cashier-spring -p 5000:5000 adimunawar31/cashier-spring'
                    echo 'Docker Run Completed'
                }
            }
        }
    }

    post {
        always {
            sh 'docker logout'
        }
    }
}
