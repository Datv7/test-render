# Stage 1: Build (JDK 21 + Maven)
FROM eclipse-temurin:21 AS build
WORKDIR /app

# Cài Maven thủ công
RUN apt-get update && apt-get install -y maven

# Copy project và build
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run (JDK 21)
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
