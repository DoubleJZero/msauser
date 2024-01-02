pipeline {
    agent any
    environment {
        DOCKER = 'sudo docker'
    }

    stages {
        stage('Clone Repository') {
            steps {
                checkout scm
                echo 'Checkout Scm'
            }
        }

        stage('Build image') {
            steps {
                sh 'ls -al'
                sh 'chmod +x ./gradlew'
                sh './gradlew build'
                sh 'docker build -t jandb:msauser .'
                echo 'Build image...'
            }
        }

        stage('Remove Previous image') {
            steps {
                script {
                    try {
                        sh 'docker stop userService'
                        sh 'docker rm userService'
                    } catch (e) {
                        echo 'fail to stop and remove container'
                    }
                }
            }
        }
        stage('Run New image') {
            steps {
                sh 'docker run --name userService -d -p 8200:8200 -e USE_PROFILE=dev jandb:msauser'
                echo 'Run New member image'
            }
        }
    }
}