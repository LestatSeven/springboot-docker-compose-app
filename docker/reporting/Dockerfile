FROM openjdk:16-alpine
EXPOSE 8082
COPY reporting-0.0.1-SNAPSHOT.jar application.jar
RUN mkdir -p /reporting/src/main/resources/static/reports/
CMD java -jar application.jar