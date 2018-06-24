pipeline {
  agent { node { checkout scm  } }
  stages {
    stage('Build') {
      steps {
sh '''./gradlew clean assemble'''
archiveArtifacts artifacts: '**/build/libs/**/*.jar', fingerprint: true
      }
    },
    stage('Test') {
      steps {
        echo 'testing'
      }
    },
    stage('E2E') {
      steps {
        echo 'E2E'ing'
      }
    },
    stage('DeployDev') {
      steps {
        echo 'Deploying'
      }
    }
  }
}
