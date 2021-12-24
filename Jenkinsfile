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
      
    stage('Docker Build and Tag') {
                sh 'docker build -t demo-loan-services:latest .' 
               // sh 'docker tag demo-loan-services gshenbagavel/demo-loan-services:latest'
        }
    
     
    stage('Run Docker Image') {
                sh 'docker run -d -p 0.0.0.0:9002:9000 demo-loan-services'
 
        }
        
  
}