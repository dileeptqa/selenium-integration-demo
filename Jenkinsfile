pipeline {
    agent any

    tools {
        maven 'Maven 3'  // Replace with your Jenkins Maven tool name
        jdk 'JDK 11'     // Replace with your Jenkins JDK name
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/dileeptqa/selenium-integration-demo.git' // Update with actual repo
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
