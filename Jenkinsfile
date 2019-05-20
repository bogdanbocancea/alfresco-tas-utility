properties([
    	buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '1', numToKeepStr: '3'))
    ])

node('tas-node') {     
    configFileProvider([configFile(fileId: 'alfresco-maven', variable: 'MAVEN_SETTINGS')]) {

        stage('Checkout') {
            sh 'git config --global http.sslVerify false'
            checkout scm
        }

        stage('Compile') {
            sh 'mvn -s $MAVEN_SETTINGS -U clean install -DskipTests'
        }
        
        stage('Deploy') {
            sh 'mvn -s $MAVEN_SETTINGS -DskipTests deploy'
        }
    }
}