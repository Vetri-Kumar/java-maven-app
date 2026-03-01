// def gv

// pipeline {   
//     agent any
//     tools {
//         maven 'maven-jenkins-3.9.12'
//     }
//     stages {
//         stage("init") {
//             steps {
//                 script {
//                     gv = load "script.groovy"
//                 }
//             }
//         }
//         stage("test"){
//             steps {
//                 script {
//                     echo "testing the application"
//                 }
//             }
//         }
//         stage("build jar") {
//             when {
//                 expression {
//                     BRANCH_NAME == 'main'
//                 }
//             }
//             steps {
//                 script {
//                     gv.buildJar()
//                     echo "building the application"
//                 }
//             }
//         }

//         stage("build image") {
//             steps {
//                 script {
//                     gv.buildImage()
//                 }
//             }
//         }

//         stage("deploy") {
//             when {
//                 expression {
//                     BRANCH_NAME == 'main'
//                 }
//             }
//             steps {
//                 script {
//                     gv.deployApp()
//                     echo "deploying the application"
//                 }
//             }
//         }               
//     }
// } 

pipeline {
    agent any

    tools {
        maven 'maven-jenkins-3.9.12'
    }

    stages {
        stage('increment version'){
            steps {
                script {
                    echo "incremention the application version"
                    sh "mvn build-helper:parse-version versions:set \
                    -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                    versions:commit"
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0[[1]
                    IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }
        }
        stage('build Jar'){
            steps {
                script {
                    echo 'building the application'
                    sh 'mvn clearn package'
                }
            }
        }
                                            
        stage ('build image'){
            steps {
                script {
                    echo "building the docker image..."
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-id', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh "docker build -t vetri18/not-public:${IMAGE_NAME} ."
                        sh 'echo $PASS | docker login -u $USER --password-stdin'
                        sh "docker push vetri18/not-public:${IMAGE_NAME}"
                    }
                }
            }
        }
        stage("deploy") {        
            steps {
                script {
                    echo "deploying the application"
                }
            }
        }
    }
}
