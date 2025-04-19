# Stage 1: Build
FROM maven:3-openjdk-21 AS build
WORKDIR /app

# Sao chép toàn bộ mã nguồn vào container
COPY . .

# Build ứng dụng Spring Boot và tạo file .jar
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM openjdk:21-jdk-slim
WORKDIR /app

# Sao chép file .jar vào container
COPY --from=build /app/target/*.jar /app/app.jar

# Mở cổng 8080 để container có thể giao tiếp
EXPOSE 8080

# Lệnh để chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
