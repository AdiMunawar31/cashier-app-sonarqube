pipeline {
    agent any
    tools {
        maven 'jenkins-maven'
    }

    stages {
        stage('Git Checkout') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/AdiMunawar31/cashier-app-sonarqube']])
                bat 'mvn clean install'
                echo 'Git Checkout Completed'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat 'mvn clean package'
                    bat ''' mvn clean verify sonar:sonar -Dsonar.projectKey=cashier-spring   -Dsonar.host.url=http://localhost:9000 '''
                    echo 'SonarQube Analysis Completed'
                }
            }
        }
        stage("Quality Gate") {
            steps {
                waitForQualityGate abortPipeline: true
                echo 'Quality Gate Completed'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    bat 'docker build -t adimunawar31/cashier-spring .'
                    echo 'Build Docker Image Completed'
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhub-password')]) {
                        bat ''' docker login -u adimunawar31 -p "%dockerhub-password%" '''
                    }
                    bat 'docker push adimunawar31/cashier-spring'
                }
            }
        }

        stage ('Docker Run') {
            steps {
                script {
                    bat 'docker run -d --name cashier-spring -p 5000:5000 adimunawar31/cashier-spring'
                    echo 'Docker Run Completed'
                }
            }
        }

    }
    post {
        always {
            bat 'docker logout'
        }
    }
}