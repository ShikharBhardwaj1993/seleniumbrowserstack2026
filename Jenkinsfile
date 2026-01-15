pipeline {
    agent any

    tools {
        maven 'maven3'
        jdk 'Java17'
    }

    environment {
        BROWSERSTACK_USERNAME = credentials('BROWSERSTACK_USERNAME')
        BROWSERSTACK_ACCESS_KEY = credentials('BROWSERSTACK_ACCESS_KEY')
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/ShikharBhardwaj1993/seleniumbrowserstack2026.git'
            }
        }

        stage('Run Tests on BrowserStack') {
            steps {
                bat 'mvn clean test -DrunOn=browserstack'
            }
        }
    }
}
