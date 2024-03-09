pipeline {
    agent any
    options { 
        // 기본적으로 체크아웃을 하지 않는 옵션
        skipDefaultCheckout(true) 
    }
    stages {
        stage('Build and test') {
            agent {
                // JDK 17과 Gradle을 사용하기 위한 Docker 에이전트
                docker {
                    image 'adoptopenjdk:17-jdk-hotspot'
                    args '-v /root/.m2:/root/.m2' // Gradle 캐시 디렉토리 마운트
                }
            }
            steps {
                // Gradle을 사용하여 Spring Boot 애플리케이션 빌드
                sh 'gradle clean build -x test' // 테스트를 실행하지 않고 빌드
            }
        }
        stage('Docker build and push') {
            agent any
            steps {
                // Docker 이미지 빌드
                sh 'docker build -t {container_name}:latest .'
                
                // AWS ECR로 Docker 이미지를 푸시하기 위한 AWS 인증 정보 사용
                withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws-credentials-id']]) {
                    // AWS ECR에 로그인
                    sh 'aws ecr get-login-password --region {aws_region} | docker login --username AWS --password-stdin {aws_account_id}.dkr.ecr.{aws_region}.amazonaws.com'
                    
                    // Docker 이미지를 AWS ECR로 푸시
                    sh 'docker tag {container_name}:latest {aws_account_id}.dkr.ecr.{aws_region}.amazonaws.com/{repository_name}:latest'
                    sh 'docker push {aws_account_id}.dkr.ecr.{aws_region}.amazonaws.com/{repository_name}:latest'
                }
            }
        }
    }
}
