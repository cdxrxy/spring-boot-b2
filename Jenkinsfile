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
        stage('deploy k8s') {
            steps {
                sh 'helm repo update metrics-server'
                sh 'helm repo add metrics-server https://kubernetes-sigs.github.io/metrics-server/'
                sh 'helm install metrics-server metrics-server/metrics-server -f ~/metrics-values.yml'
                sh 'kubectl apply -f kube'
            }
        }
    }
}
