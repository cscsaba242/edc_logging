# Use a JDK base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy built jar file (build it with Gradle first)
COPY build/libs/*.jar app.jar

# Expose port (optional, depending on your app)
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]