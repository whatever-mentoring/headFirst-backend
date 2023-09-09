# Stage 1: Build the application
FROM openjdk:17-jdk AS builder
WORKDIR /app
COPY . .
RUN ./gradlew build -x test

# Stage 2: Create the final image
FROM openjdk:17-jdk
WORKDIR /app
EXPOSE 8080
COPY --from=builder /app/build/libs/headfirst-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java", "-jar", "app.jar"]