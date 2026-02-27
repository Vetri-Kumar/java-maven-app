#!/usr/bin/env groovy

@Library("jenkins-shared-library")
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
                    buildImage()
                    
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
