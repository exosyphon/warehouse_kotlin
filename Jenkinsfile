pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh '''netstat -l

./gradlew clean build'''
      }
    }
  }
}