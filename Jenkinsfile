pipeline {
  agent { label 'windows' }

  parameters {
    choice(name: 'SUITE', choices: [
      'src/test/resources/suites/smoke.xml',
      'src/test/resources/suites/regression.xml'
    ], description: 'TestNG suite')

    choice(name: 'ENV', choices: ['staging', 'dev'], description: 'Target env')
    booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Run headless')
    string(name: 'THREADS', defaultValue: '2', description: 'Thread count')
  }

  options {
    timestamps()
    disableConcurrentBuilds()
  }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Test Matrix') {
      parallel {
        stage('Chrome') {
          steps {
            dir('chrome') {
              bat 'mvn -q -Denv=%ENV% -Dbrowser=chrome -Dheadless=%HEADLESS% -Dthreads=%THREADS% -DsuiteXmlFile=..\\%SUITE% test'
            }
          }
          post {
            always {
              junit 'chrome/target/surefire-reports/*.xml'
              allure includeProperties: false, jdk: '', results: [[path: 'chrome/target/allure-results']]
              archiveArtifacts artifacts: 'chrome/target/surefire-reports/**, chrome/target/allure-results/**', allowEmptyArchive: true
            }
          }
        }

        stage('Edge') {
          steps {
            dir('edge') {
              bat 'mvn -q -Denv=%ENV% -Dbrowser=edge -Dheadless=%HEADLESS% -Dthreads=%THREADS% -DsuiteXmlFile=..\\%SUITE% test'
            }
          }
          post {
            always {
              junit 'edge/target/surefire-reports/*.xml'
              allure includeProperties: false, jdk: '', results: [[path: 'edge/target/allure-results']]
              archiveArtifacts artifacts: 'edge/target/surefire-reports/**, edge/target/allure-results/**', allowEmptyArchive: true
            }
          }
        }

        stage('Firefox') {
          steps {
            dir('firefox') {
              bat 'mvn -q -Denv=%ENV% -Dbrowser=firefox -Dheadless=%HEADLESS% -Dthreads=%THREADS% -DsuiteXmlFile=..\\%SUITE% test'
            }
          }
          post {
            always {
              junit 'firefox/target/surefire-reports/*.xml'
              allure includeProperties: false, jdk: '', results: [[path: 'firefox/target/allure-results']]
              archiveArtifacts artifacts: 'firefox/target/surefire-reports/**, firefox/target/allure-results/**', allowEmptyArchive: true
            }
          }
        }
      }
    }
  }
}
