run:
    docker pull openjdk:8-jdk-alpine
    docker pull mongo:latest
    docker run -d -p 27017:27017 --name mongodb mongo:latest
    chmod 777 mvnw
    ./mvnw clean packace -B
    docker build -t hikingapp .
    docker run -p 8080:8080 --name hikingapp --link mongodb:mongo -d hikingapp:latest