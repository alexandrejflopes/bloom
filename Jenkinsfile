def remote = [:]
remote.host = "192.168.160.87"
remote.name = "runtime"

pipeline {

    agent any

    environment {
        detector_app = ""
        manage_app = ""
        sensors_app = ""
        sensors_generator = ""
        frontend_app = ""
    }

    tools {
        maven 'maven36'
    }

    stages{
        stage('Test') {
            steps {
                dir('detectorApp'){
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        sh 'mvn test'
                    }
                }
                dir('manageApp'){
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        sh 'mvn test'
                    }
                }
                dir('sensorsApp'){
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        sh 'mvn test'
                    }
                }
                dir('sensorsGenerator'){
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        sh 'mvn test'
                    }
                }
            }
        }

        stage ('Build') {
            steps {
                dir('detectorApp'){  
                    sh 'mvn -Dmaven.test.failure.ignore=true install' 
                }
                dir('manageApp'){  
                    sh 'mvn -Dmaven.test.failure.ignore=true install' 
                }
                dir('sensorsApp'){  
                    sh 'mvn -Dmaven.test.failure.ignore=true install' 
                }
                dir('sensorsGenerator'){  
                    sh 'mvn -Dmaven.test.failure.ignore=true install' 
                }
            }
            post {
                success {
                    dir('detectorApp'){  
                        junit 'target/surefire-reports/**/*.xml' 
                    }
                    dir('manageApp'){  
                        junit 'target/surefire-reports/**/*.xml' 
                    }
                    dir('sensorsApp'){  
                        junit 'target/surefire-reports/**/*.xml' 
                    }
                    dir('sensorsGenerator'){  
                        junit 'target/surefire-reports/**/*.xml' 
                    }
                }
            }
        }
        
        /*
        stage ('Cucumber Reports') {

            steps {
                dir('webapp'){  
                    cucumber buildStatus: "UNSTABLE",
                        fileIncludePattern: "**/report.json",
                        /* jsonReportDirectory: 'target' */
                }

            }

        }
        */
        

        stage ('Deploy') {
            steps{
                sh 'mvn deploy -f detectorApp/pom.xml -s detectorApp/settings.xml'
                sh 'mvn deploy -f manageApp/pom.xml -s manageApp/settings.xml'
                sh 'mvn deploy -f sensorsApp/pom.xml -s sensorsApp/settings.xml'
                sh 'mvn deploy -f sensorsGenerator/pom.xml -s sensorsGenerator/settings.xml' 
            }
        }


        stage('Build images'){
            steps{
                script{
                    docker.withRegistry('http://192.168.160.48:5000') {
                            detector_app = docker.build("esp50/detectorapp", "./detectorApp")
                    }
                    docker.withRegistry('http://192.168.160.48:5000') {
                            manage_app = docker.build("esp50/manageapp", "./manageApp")
                    }
                    docker.withRegistry('http://192.168.160.48:5000') {
                            sensors_app = docker.build("esp50/sensorsapp", "./sensorsApp")
                    }
                    docker.withRegistry('http://192.168.160.48:5000') {
                            sensors_generator = docker.build("esp50/sensorsgenerator", "./sensorsGenerator")
                    }
                    docker.withRegistry('http://192.168.160.48:5000') {
                            frontend_app = docker.build("esp50/webapp", "./webapp")
                    }
                }
            }
        }

        stage('Publish images'){
            steps{
                script{

                    docker.withRegistry('http://192.168.160.48:5000') {
                        // Push the container to the custom Registry 
                        detector_app.push()
                    }
                    docker.withRegistry('http://192.168.160.48:5000') {
                        // Push the container to the custom Registry 
                        manage_app.push()
                    }
                    docker.withRegistry('http://192.168.160.48:5000') {
                        // Push the container to the custom Registry 
                        sensors_app.push()
                    }
                    docker.withRegistry('http://192.168.160.48:5000') {
                        // Push the container to the custom Registry 
                        sensors_generator.push()
                    }
                    docker.withRegistry('http://192.168.160.48:5000') {
                        // Push the container to the custom Registry 
                        frontend_app.push()
                    }

                    sh 'docker rmi esp50/detectorapp'
                    sh 'docker rmi esp50/manageapp'
                    sh 'docker rmi esp50/sensorsapp'
                    sh 'docker rmi esp50/sensorsgenerator'
                    sh 'docker rmi esp50/webapp'
                    sh 'docker image ls'
                }
            }
        }

        stage('Runtime Deployment') { 
            steps {
                 withCredentials([usernamePassword(credentialsId: 'esp50_ssh_credentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    
                    script {
                      remote.user = USERNAME
                      remote.password = PASSWORD
                      remote.allowAnyHosts = true
                        
                    }

                    sshCommand remote: remote, command: "docker stop esp50-sensorsgenerator"
                    sshCommand remote: remote, command: "docker rm esp50-sensorsgenerator"
                    sshCommand remote: remote, command: "docker rmi 192.168.160.48:5000/esp50/sensorsgenerator"
                    sshCommand remote: remote, command: "docker pull 192.168.160.48:5000/esp50/sensorsgenerator"
                    sshCommand remote: remote, command: "docker create -p 50090:50090 --name esp50-sensorsgenerator 192.168.160.48:5000/esp50/sensorsgenerator"
                    sshCommand remote: remote, command: "docker start esp50-sensorsgenerator"
                    
                    sshCommand remote: remote, command: "docker stop esp50-sensorsapp"
                    sshCommand remote: remote, command: "docker rm esp50-sensorsapp"
                    sshCommand remote: remote, command: "docker rmi 192.168.160.48:5000/esp50/sensorsapp"
                    sshCommand remote: remote, command: "docker pull 192.168.160.48:5000/esp50/sensorsapp"
                    sshCommand remote: remote, command: "docker create -p 50080:50080 --name esp50-sensorsapp 192.168.160.48:5000/esp50/sensorsapp"
                    sshCommand remote: remote, command: "docker start esp50-sensorsapp"

                    sshCommand remote: remote, command: "docker stop esp50-detectorapp"
                    sshCommand remote: remote, command: "docker rm esp50-detectorapp"
                    sshCommand remote: remote, command: "docker rmi 192.168.160.48:5000/esp50/detectorapp"
                    sshCommand remote: remote, command: "docker pull 192.168.160.48:5000/esp50/detectorapp"
                    sshCommand remote: remote, command: "docker create -p 50050:50050 --name esp50-detectorapp 192.168.160.48:5000/esp50/detectorapp"
                    sshCommand remote: remote, command: "docker start esp50-detectorapp"
                     
                    sshCommand remote: remote, command: "docker stop esp50-manageapp"
                    sshCommand remote: remote, command: "docker rm esp50-manageapp"
                    sshCommand remote: remote, command: "docker rmi 192.168.160.48:5000/esp50/manageapp"
                    sshCommand remote: remote, command: "docker pull 192.168.160.48:5000/esp50/manageapp"
                    sshCommand remote: remote, command: "docker create -p 50060:50060 --name esp50-manageapp 192.168.160.48:5000/esp50/manageapp"
                    sshCommand remote: remote, command: "docker start esp50-manageapp"
                     
                    sshCommand remote: remote, command: "docker stop esp50-webapp"
                    sshCommand remote: remote, command: "docker rm esp50-webapp"
                    sshCommand remote: remote, command: "docker rmi 192.168.160.48:5000/esp50/webapp"
                    sshCommand remote: remote, command: "docker pull 192.168.160.48:5000/esp50/webapp"
                    sshCommand remote: remote, command: "docker create -p 50030:3000 --name esp50-webapp 192.168.160.48:5000/esp50/webapp"
                    sshCommand remote: remote, command: "docker start esp50-webapp"

                }
            }
        }

    }

}
