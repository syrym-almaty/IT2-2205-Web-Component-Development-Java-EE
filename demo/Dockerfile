# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim


# Set the working directory inside the container
WORKDIR /app

# Copy the Maven build file and dependencies
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application will run on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
