FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY build/libs/*.jar user-management-1.0.0.jar
ENTRYPOINT ["java", "-jar", "user-management-1.0.0.jar"]