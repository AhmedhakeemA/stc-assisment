FROM openjdk:17-jdk-slim
LABEL authors="AhmedHakeem"

ENV SPRING_DATASOURCE_URL jdbc:postgresql://localhost:5432/stc_assissment
ENV SPRING_DATASOURCE_USERNAME postgres
ENV SPRING_DATASOURCE_PASSWORD 123456789
ENV SPRING_JPA_HIBERNATE_DDL_AUTO update

COPY target/stc-assessment.jar /app.jar
EXPOSE 8080 5432
CMD ["java", "-jar", "/app.jar"]