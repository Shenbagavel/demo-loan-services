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
               sh 'docker run  -d -p 0.0.0.0:9002:9100 demo-loan-services'
 
        }
        
    stage('Deploy in Kubenetes') {
    
     //   try{  
       //         sh 'sudo -i -u opc'
             
         //   }
        //catch (exc) {
        //echo 'Failed in switch to OPC user'
        //}     
    
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
                sh 'echo $USER'
               // sh 'kubectl --token=$JENKINS_KUBER_SA_TOKEN version'
               //sh 'kubectl convert -f deployment.yml --output-version apps/v1 > new-deployment.yml'
                //sh 'kubectl create -f deployment.yaml'
               //  sh 'kubectl apply -f deployment.yaml'
                sh 'kubectl apply -f deployment.yml  --token $JENKINS_DOCKER'
                sh 'kubectl expose deployment/demo-loan-services-deployment --token=$JENKINS_DOCKER'
            }
        catch (exc) {
          echo 'Kubernetes Deployment Failed CHeck'
          echo exc.getMessage()
          
        }    

         //try{  
           //     sh 'sudo -i -u jenkins'
             
            //}
        //catch (exc) {
        //echo 'Failed in switch to jenkins user'
        //}         
    }
       
}