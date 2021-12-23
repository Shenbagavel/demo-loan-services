From tomcat:9.0.56-jdk8
EXPOSE 8080
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/demo-loan-services-1.0.0.war  /usr/local/tomcat/webapps/demo-loan-services.war
CMD ["catalina.sh","run"]
