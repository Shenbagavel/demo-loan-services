node{

   def tomcatWeb = 'D:\\softwares\\aTomcat_Server_9.0.56_Server\\apache-tomcat-9.0.56\\webapps'
   def tomcatBin = 'D:\\softwares\\aTomcat_Server_9.0.56_Server\\apache-tomcat-9.0.56\\bin'
   def tomcatStatus = ''
   stage('GIT CHECKOUT'){
   
     git 'https://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@github.com/Shenbagavel/demo-loan-services.git'
   }
   stage('COMPILE_BUILD'){
      // Get maven home path
      def mvnHome =  tool name: 'Maven_3.8.4', type: 'maven'   
      bat "${mvnHome}/bin/mvn package"
      }
/*   stage ('Stop Tomcat Server') {
               bat ''' @ECHO OFF
               wmic process list brief | find /i "tomcat" > NUL
               IF ERRORLEVEL 1 (
                    echo  Stopped
               ) ELSE (
               echo running
                  "${tomcatBin}\\shutdown.bat"
                  sleep(time:10,unit:"SECONDS") 
               )
'''
   }*/
   stage('Deploy to Tomcat'){
     bat "copy target\\demo-loan-services-1.0.0.war \"${tomcatWeb}\\demo-loan-services-1.0.0.war\""
   }
      stage ('Start Tomcat Server') {
         sleep(time:5,unit:"SECONDS") 
         bat "${tomcatBin}\\startup.bat"
         sleep(time:100,unit:"SECONDS")
   }
   
}