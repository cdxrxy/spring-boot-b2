pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {
        stage('build maven') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/cdxrxy/spring-boot-b2']])
                sh 'mvn clean install'
            }
        }
        stage('build docker') {
            steps {
                sh 'docker rmi -f cdxray/spring-boot-app'
                sh 'docker build -t cdxray/spring-boot-app .'
            }
        }
        stage('push docker') {
            steps {
                withCredentials([string(credentialsId: 'DOCKER_HUB', variable: 'DOCKER_HUB')]) {
                    sh 'docker login -u cdxray -p ${DOCKER_HUB}'
                    sh 'docker push cdxray/spring-boot-app'
                }
            }
        }
    }
}
