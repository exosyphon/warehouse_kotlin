pipeline {
  agent none
  stages {
    stage('Build') {
      agent any
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
      agent any
      steps {
      echo 'testing'
      }
    }
    stage('E2E') {
      agent any
      steps {
        echo 'E2Eing'
      }
    }
    stage('DeployDev') {
      agent any
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
        env.DEPLOY_TO_STAGING =  input {
                                                         message "Deploy to Staging?"
                                                         ok "y"
                                                         parameters {
                                                             string(name: 'IS_APPROVED', defaultValue: 'y', description: 'Deploy this build?')
                                                         }
                                                     }
        }
      }
    }
    stage('DeployStaging') {
        agent any
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
  env.DEPLOY_TO_PRODUCTION =  input {
                                                            message "Deploy to Production?"
                                                            ok "y"
                                                            parameters {
                                                                string(name: 'IS_APPROVED', defaultValue: 'y', description: 'Deploy this build?')
                                                            }
                                                        }
      }
      }
    }
    stage('DeployProduction') {
        agent any
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
