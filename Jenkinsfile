pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
      postToBuildMonitor("FINALIZED", "SUCCESS")
sh '''./gradlew clean assemble'''
archiveArtifacts artifacts: '**/build/libs/**/*.jar', fingerprint: true
      }
    }
    stage('Test') {
      steps {
        echo 'testing'
      }
    }
    stage('E2E') {
      steps {
        echo 'E2Eing'
      }
    }
    stage('DeployDev') {
      steps {
        echo 'Deploying'
      }
    }
  }
}

def postToBuildMonitor(phase, isSuccessful) {
  def buildJobUrl = "http://autobotmonitor.cfapps.io/projects/628379d9-20aa-49f0-861c-fec2f2a71d4d/status"
  sh(script: "curl -X POST -H \"Content-Type: application/json\" -d '{ \"build\": { \"full_url\": \"\", \"number\": 9, \"phase\": ${phase}, \"status\": ${isSuccessful} }}' ${buildJobUrl}") 
}
