FROM openjdk:17-alpine
ADD {application_file} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]