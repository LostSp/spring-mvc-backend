FROM maven:3.9.6-eclipse-temurin-21

WORKDIR /app

#copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

EXPOSE 3001

CMD ["mvn", "spring-boot:run"]

