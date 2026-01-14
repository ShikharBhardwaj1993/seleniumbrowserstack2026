pipeline {
    agent any

    tools {
        maven 'maven3'
        jdk 'Java17'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/ShikharBhardwaj1993/seleniumbrowserstack2026.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }
    }
}
