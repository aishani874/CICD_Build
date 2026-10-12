pipeline {
    agent any

    // Automatically injects Maven binaries into the PATH for all stages
    tools {
        maven 'Maven3'
    }

    environment {
        // Path to Docker CLI binary
        DOCKER_BIN     = '"C:\\Users\\tuhi8\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe"'
        
        // Application & container configuration
        IMAGE_NAME     = 'complete-build-app'
        CONTAINER_NAME = 'complete-build-container'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code from Git repository...'
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                echo 'Compiling Java source files via Maven...'
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running automated JUnit unit tests...'
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
                bat """
                    ${DOCKER_BIN} build -t ${IMAGE_NAME}:${BUILD_NUMBER} -t ${IMAGE_NAME}:latest .
                """
            }
        }

        stage('Docker Deploy') {
            steps {
                echo 'Deploying application as a Docker container...'
                bat """
                    rem Stop and remove any pre-existing container with the same name
                    ${DOCKER_BIN} rm -f ${CONTAINER_NAME} 2>nul || exit 0
                    
                    rem Start the newly built container
                    ${DOCKER_BIN} run -d --name ${CONTAINER_NAME} ${IMAGE_NAME}:${BUILD_NUMBER}
                """
            }
        }
    }

    post {
        success {
            echo "Pipeline completed successfully! All stages executed on Jenkins board."
        }
        failure {
            echo "Pipeline build failed. Please check stage console outputs."
        }
    }
}