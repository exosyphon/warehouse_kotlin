pipeline {
  agent none
  stages {
    stage('Build') {
      agent { label 'build' }
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
      agent { label 'build' }
      steps {
      echo 'testing'
      }
    }
    stage('E2E') {
      agent { label 'build' }
      steps {
        echo 'E2Eing'
      }
    }
    stage('DeployDev') {
      agent { label 'build' }
      steps {
                wrapCommands(
                {
                echo 'Deploying to Dev'
                //pushToCloudFoundry cloudSpace: 'playground', credentialsId: '6ea07003-dece-40c1-98fc-a5818957fca2', organization: 'pivot-acourter', target: 'api.run.pcfbeta.io'
                },
                "http://autobotmonitor.cfapps.io/projects/0a389362-c1da-4c6d-821b-6ca396d97f2a/status"
)
      }
    }
    stage('RequestDeployStaging') {
      agent none
      steps {
        script {
        env.DEPLOY_TO_STAGING =  input(message: 'Deploy to Staging?', ok: 'Yes',
                                                                                       parameters: [booleanParam(defaultValue: true,
                                                                                       description: 'Deploy this build?',name: 'Yes?')])
        }
      }
    }
    stage('DeployStaging') {
        agent { label 'build' }
        when {
          environment name: 'DEPLOY_TO_STAGING', value: 'yes'
        }
        steps {
          echo 'Deploying to Staging'
        }
    }
    stage('RequestDeployProduction') {
      agent none
      steps {
              script {
  env.DEPLOY_TO_PRODUCTION = input(message: 'Deploy to Production?', ok: 'Yes',
                                                      parameters: [booleanParam(defaultValue: true,
                                                      description: 'Deploy this build?',name: 'Yes?')])
      }
      }
    }
    stage('DeployProduction') {
        agent { label 'build' }
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
