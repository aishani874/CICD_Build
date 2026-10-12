pipeline {
    agent any

    environment {
        IMAGE_NAME     = 'complete-build-app'
        CONTAINER_NAME = 'complete-build-container'
        DOCKER_BIN     = '"C:\\Program Files\\Docker\\Docker\\resources\\bin\\docker.exe"'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code from Git repository...'
                checkout scm
            }
        }
    tools {
        maven 'Maven3'
        }
        stage('Compile') {
            steps {
                echo 'Compiling Java source files via Maven...'
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running automated JUnit unit tests via Surefire...'
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging application into executable JAR file...'
                bat 'mvn package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker container image...'
                bat "${DOCKER_BIN} build -t ${IMAGE_NAME}:${BUILD_NUMBER} -t ${IMAGE_NAME}:latest ."
            }
        }

        stage('Docker Deploy') {
            steps {
                echo 'Deploying application as a Docker container...'
                bat """
                    ${DOCKER_BIN} rm -f ${CONTAINER_NAME} 2>nul
                    ${DOCKER_BIN} run -d --name ${CONTAINER_NAME} ${IMAGE_NAME}:${BUILD_NUMBER}
                """
            }
        }
    }

    post {
        success {
            echo "Pipeline completed successfully! All stages executed."
        }
        failure {
            echo "Pipeline execution failed. Review the console logs."
        }
    }
}