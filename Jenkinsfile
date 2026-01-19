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

        stage('Run Tests on BrowserStack') {
            steps {
                // Inject credentials into environment for Windows batch
                withEnv([
                    "BROWSERSTACK_USERNAME=${BROWSERSTACK_USERNAME}",
                    "BROWSERSTACK_ACCESS_KEY=${BROWSERSTACK_ACCESS_KEY}"
                ]) {
                    bat 'mvn clean test -DrunOn=browserstack'
                }
            }
        }
    }

    post {
        always {
            echo 'Test execution completed'
        }
        success {
            echo 'BrowserStack tests passed'
        }
        failure {
            echo 'BrowserStack tests failed'
        }
    }
}
