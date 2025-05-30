# FROM maven:3.9.6-eclipse-temurin-21
#
# WORKDIR /app
#
# COPY pom.xml .
# COPY src ./src
#
# RUN mvn dependency:go-offline
#
# EXPOSE 3001
#
# CMD ["mvn", "spring-boot:run"]


FROM maven:3.9.6-eclipse-temurin-21

WORKDIR /app

# Only copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Do NOT copy source files here — use volume mount in docker-compose instead

EXPOSE 3001

CMD ["mvn", "spring-boot:run"]

