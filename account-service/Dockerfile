FROM openjdk:18
ARG JAR_FILE="*.jar"
COPY target/${JAR_FILE} account-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "account-service.jar"]