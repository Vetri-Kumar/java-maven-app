#!/usr/bin/env groovy

library identifier:"jenkins-shared-library@master", retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'https://github.com/Vetri-Kumar/jenkins-shared-library.git',
    credentialsId:'github-Id']
)
def gv

pipeline {   
    agent any
    tools {
        maven 'maven-jenkins-3.9.12'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        // stage("test"){
        //     steps {
        //         script {
        //             echo "testing the application"
        //         }
        //     }
        // }
        stage("build jar") {
            // when {
            //     expression {
            //         BRANCH_NAME == 'main'
            //     }
            // }
            steps {
                script {
                    // gv.buildJar()
                    echo "building the application"
                    buildJar()
                }
            }
        }

        stage("build image") {
            steps {
                script {
                    // gv.buildImage()
                    echo "building the image"
                    buildImage 'vetri18/not-public:jma-9.6.9.6'
                    dockerLogin()
                    dockerPush 'vetri18/not-public:jma-9.6.9.6'
                }
            }
        }

        stage("deploy") {
            // when {
            //     expression {
            //         BRANCH_NAME == 'main'
            //     }
            // }
            steps {
                script {
                    // gv.deployApp()
                    echo "deploying the application"
                }
            }
        }               
    }
} 
