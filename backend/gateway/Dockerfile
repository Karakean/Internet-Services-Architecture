FROM maven:3.8.6-openjdk-18
WORKDIR /app
COPY pom.xml /app/
RUN mvn dependency:resolve
COPY ./src /app/src
CMD ["mvn", "spring-boot:run"]
