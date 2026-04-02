pipeline {
    agent any

    environment {
        DOCKER_HUB = "rakeshdocker7"
        APP_NAME = "telecom-soap-backend"
        KUBECONFIG = "C:\\Users\\Welcome\\.kube\\config"
    }

    stages {

        stage('Checkout') {
            steps {
                echo '📥 Checkout backend code'
                checkout scm
            }
        }

        stage('Build (Maven)') {
            steps {
                echo '📦 Building backend'
                bat 'mvnw clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo '🐳 Building Docker image'
                bat 'docker build -t %DOCKER_HUB%/%APP_NAME%:latest .'
            }
        }

        stage('Docker Push') {
            steps {
                echo '📤 Pushing Docker image'

                withCredentials([usernamePassword(
                    credentialsId: 'docker-hub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    bat '''
                    echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                    docker push %DOCKER_HUB%/%APP_NAME%:latest
                    '''
                }
            }
        }

        stage('Kubernetes Deploy') {
            steps {
                echo '🚀 Deploying to Kubernetes'

                bat '''
                kubectl apply -f k8s/deployment.yaml
                kubectl apply -f k8s/service.yaml
                '''
            }
        }
    }

    post {
        success {
            echo '✅ Backend Pipeline Successful 🚀'
        }
        failure {
            echo '❌ Pipeline Failed'
        }
    }
}
