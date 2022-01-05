node{

   def tomcatWeb = '/opt/apache-tomcat-9.0.56/webapps'
   def tomcatBin = '/opt/apache-tomcat-9.0.56/bin'
   def tomcatStatus = ''
   
   
  
      stage('Git Checkout') {
             
                git branch: 'main', url:'https://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@github.com/Shenbagavel/demo-loan-services.git'
            }
	 
        
    stage('Compile and Build'){
      // Get maven home path
      def mvnHome =  tool name: 'Maven_3.8.4', type: 'maven'   
      sh "${mvnHome}/bin/mvn package"
      }
      
    stage('Remove Old Image Docker Build and Tag') {
        try {
            sh 'docker stop $(docker ps -aq)'
            }
        catch (exc) {
              echo 'No Process to stop'
        }
         try {
            sh 'docker rm $(docker ps -aq)'
            }
        catch (exc) {
              echo 'No process to remove'
        }
         try {
            sh 'docker rmi $(docker images -q)'
            }
        catch (exc) {
              echo 'No image to remove'
        }        
        
            sh 'docker build -t demo-loan-services:latest .' 
            sh 'docker tag demo-loan-services:latest gshenbagavel/demo-loan-services:latest'
          // sh 'docker tag demo-loan-services gshenbagavel/demo-loan-services:latest'
        }
        
    stage('Publish image to Docker Hub') {
        withDockerRegistry([ credentialsId: "DOCKERHUB", url: "" ]) {
        try {
            sh  'docker push gshenbagavel/demo-loan-services:latest'
        //  sh  'docker push demo-loan-services:$BUILD_NUMBER' 
            }
        catch (exc) {
        }        
          echo 'Docker image push failed'
        } 
       } 
       
    stage('Run Docker Image') {
               //sh 'docker run --network=host -d -p 0.0.0.0:9002:9000 demo-loan-services'
               sh 'docker run  -d -p 0.0.0.0:9002:9000 demo-loan-services'
 
        }
        
    stage('Deploy in Kubenetes') {
    
        try{  
                sh 'kubectl delete deployments demo-loan-services-deployment'
             
            }
        catch (exc) {
        echo 'Kubernetes Delete deployment Failed CHeck'
        }        
                   
        
        try{  
               
                sh 'kubectl delete services demo-loan-services-deployment'
            }
        catch (exc) {
        echo 'Kubernetes Delete Services Failed CHeck'
        }        
          
        
        
        try{  
               sh 'kubectl delete pods --all'
               
            }
        catch (exc) {
        echo 'Kubernetes Delete pods Failed CHeck'
        }        
          
       
        
        try{  
                 
                sh 'kubectl delete daemonset --all'
               
            }
        catch (exc) {
         echo 'Kubernetes Delete daemon Failed CHeck'
        }        
         
               
        try{ 
                sh 'kubectl --token=eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJqZW5raW5zIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImplbmtpbnMtdG9rZW4tOHgyeDciLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiamVua2lucyIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6IjMyN2FhYTcyLTZkY2MtMTFlYy04ZmUxLTAyMDAxNzAwM2U2OCIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpqZW5raW5zOmplbmtpbnMifQ.IY-jqxlPTndCA1C2Y1kdTHH7LimfxDR4IS3DTwSEZNVot4_c_nps3t2PocrBBNQKyuThqAdHw71ziNu-4hKIE-BT0VqLN2lN3suTKp8E9QLWwzESO7CK4bF1KNmaAp_chLmMhVudVwleEz7zfSkTiO4sxcNQlgZ3tp5KY2JpNVHUBNIbqVYdzvX6eNz0GHI72Pl6yTYIBJwYcexmlo1P8nINVvhLK30gEqRTS6fOamIVOMbCfUas33hNpmZxCnE9ksWTEUD8wlGJcOEizXAKL13C1cRglwOkt1-9EbT1K3Y-noZ7jncEefVLYwqcxGhooE4LnOAmtIfsBXSibr6pqA
 version'
                sh 'kubectl apply -f deployment.yml'
                sh 'kubectl expose deployment/demo-loan-services-deployment'
            }
        catch (exc) {
          echo 'Kubernetes Deployment Failed CHeck'
          
        }     
    }
       
}