def buildJar() {
    echo 'building the application...'
    sh 'mvn package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub-id', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t vetri18/not-public:jma-9.9.9 .'
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'docker push vetri18/not-public:jma-9.9.9'
    }
}

def deployApp() {
    echo 'deploying the application...'
    echo "YEP yep YEP yep YEP yep ......."
}

// def buildApp(){
//   echo "building the application"
// }
// def testApp(){
//   echo "testing the application"
// }
// def deployApp(){
//   echo "deploying the application in version ${NEW_VERSION}"
// }

return this
