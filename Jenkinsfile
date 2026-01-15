pipeline {
    agent any

    tools {
        maven 'maven3'
        jdk 'Java17'
    }

     environment {
        BROWSERSTACK_USERNAME = credentials('Shikhar Bhardwaj')
        BROWSERSTACK_ACCESS_KEY = credentials('LqcxPXqfuCcznNuoXwZX')
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
