node{

   def tomcatWeb = '/opt/apache-tomcat-9.0.56/webapps'
   def tomcatBin = '/opt/apache-tomcat-9.0.56/bin'
   def tomcatStatus = ''
   stage('GIT Checkout'){
   
     git branch: 'main', url:'https://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@github.com/Shenbagavel/demo-loan-services.git'
   }
   stage('COMPILE_BUILD'){
      // Get maven home path
      def mvnHome =  tool name: 'Maven_3.8.4', type: 'maven'   
      sh "${mvnHome}/bin/mvn package"
      }
/*   stage ('Stop Tomcat Server') {
               bat ''' @ECHO OFF
               wmic process list brief | find /i "tomcat" > NUL
               IF ERRORLEVEL 1 (
                    echo  Stopped
               ) ELSE (
               echo running
                sh  "${tomcatBin}/shutdown.sh"
                  sleep(time:10,unit:"SECONDS") 
               )
'''
   }*/
   /*
   stage('Deploy to Tomcat'){
     sh "cp target/demo-loan-services-1.0.0.war \"${tomcatWeb}/demo-loan-services-1.0.0.war\""
   }
      stage ('Start Tomcat Server') {
         sleep(time:5,unit:"SECONDS") 
         sh "${tomcatBin}/startup.sh"
         //sleep 50s
   }
   */
   
   stage('Docker Build and Tag') {
           steps {
              
                sh 'docker build -t demo-loan-services:latest .' 
                //sh 'docker tag demo-loan-services gshenbagavel/demo-loan-services:latest'
          }
        }
	
	stage('Run Docker Demo Web App Container') {
             
            steps 
			{
                sh 'docker run -d -p 0.0.0.0:9002:9000 demo-loan-services'
 
            }
        }
}