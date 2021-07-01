FROM openjdk:8-jdk-alpine

ADD target/hikingapp.jar hikingapp.jar

ENTRYPOINT ["java","-jar","hikingapp.jar"]

EXPOSE 8080
