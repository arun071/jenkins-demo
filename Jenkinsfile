pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "arun071/jenkins-demo"
        DOCKER_TAG = "latest"
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/arun071/jenkins-demo.git'
            }
        }
        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t $DOCKER_IMAGE:$DOCKER_TAG .'
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                        sh 'echo $PASSWORD | docker login -u $USERNAME --password-stdin'
                        sh 'docker push $DOCKER_IMAGE:$DOCKER_TAG'
                    }
                }
            }
        }
//         stage('Deploy to Server') {
//             steps {
//                 sshagent(['ssh-key-credentials']) {
//                     sh '''
//                     ssh -o StrictHostKeyChecking=no user@your-server <<EOF
//                     docker pull $DOCKER_IMAGE:$DOCKER_TAG
//                     docker stop spring-boot-app || true
//                     docker rm spring-boot-app || true
//                     docker run -d --name spring-boot-app -p 8080:8080 $DOCKER_IMAGE:$DOCKER_TAG
//                     EOF
//                     '''
//                 }
//             }
//         }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
