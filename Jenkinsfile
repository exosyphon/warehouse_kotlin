pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        wrapCommands(
                        {
                        sh '''./gradlew clean assemble'''
                        archiveArtifacts artifacts: '**/build/libs/**/*.jar', fingerprint: true},
  "http://autobotmonitor.cfapps.io/projects/628379d9-20aa-49f0-861c-fec2f2a71d4d/status"
)
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
                wrapCommands(
                {
                echo 'Deploying to Dev'
                #pushToCloudFoundry cloudSpace: 'playground', credentialsId: '6ea07003-dece-40c1-98fc-a5818957fca2', organization: 'pivot-acourter', target: 'api.run.pcfbeta.io'
                },
                "http://autobotmonitor.cfapps.io/projects/0a389362-c1da-4c6d-821b-6ca396d97f2a/status"
)
      }
    }
    stage('RequestDeployStaging') {
      steps {
        env.DEPLOY_TO_STAGING = input message: 'Deploy to Staging?', parameters: [choice(name: 'Deploy', choices: 'no\nyes', description: 'Choose "yes" if you want to deploy this build')]
      }
    }
    stage('DeployStaging') {
        when {
          environment name: 'DEPLOY_TO_STAGING', value: 'yes'
        }
        steps {
          echo 'Deploying to Staging'
        }
    }
    stage('RequestDeployProduction') {
      steps {
        env.DEPLOY_TO_PRODUCTION = input message: 'Deploy to Production?', parameters: [choice(name: 'Deploy', choices: 'no\nyes', description: 'Choose "yes" if you want to deploy this build')]
      }
    }
    stage('DeployStaging') {
        when {
          environment name: 'DEPLOY_TO_PRODUCTION', value: 'yes'
        }
        steps {
          echo 'Deploying to Production'
        }
    }
  }
}

def wrapCommands(commands, jobUrl) {
        postToBuildMonitor("STARTED", "SUCCESS", jobUrl)
        script {
            currentBuild.result = 'SUCCESS'
            try {
              commands.call()
            } catch (e) {
               currentBuild.result = 'FAIL'
               throw e
            } finally {
               postToBuildMonitor("FINALIZED", currentBuild.result, jobUrl)
            }
        }
}

def postToBuildMonitor(phase, isSuccessful, jobUrl) {
  sh(script: "curl -X POST -H \"Content-Type: application/json\" -d '{ \"build\": { \"full_url\": \"\", \"number\": ${env.BUILD_NUMBER}, \"phase\": \"${phase}\", \"status\": \"${isSuccessful}\" }}' ${jobUrl}")
}
