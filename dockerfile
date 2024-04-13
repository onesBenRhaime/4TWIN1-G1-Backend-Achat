# Use a base image with JDK 11 installed
FROM openjdk:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Install curl (if not already installed) to download artifacts
RUN apk --no-cache add curl

# Download the Spring Boot application JAR file from Nexus
RUN curl -u admin:nexus "http://192.168.33.10:8081/repository/4TWIN1-G1-Achat/tn/esprit/DevOps_Project/1.0-SNAPSHOT/DevOps_Project-1.0-20240412.230309-1.jar" --output /app/DevOps_Project-1.0-20240412.230309-1.jar
EXPOSE 8082

# Specify the command to run the Spring Boot application when the container starts
ENTRYPOINT ["java", "-jar", "DevOps_Project-1.0-20240412.230309-1.jar"]