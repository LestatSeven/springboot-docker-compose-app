FROM openjdk:16-alpine
EXPOSE 8080
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar
CMD java -jar application.jar