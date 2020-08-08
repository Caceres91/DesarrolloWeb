FROM openjdk:8-jdk-alpine
COPY "./target/api-0.0.1-SNAPSHOT.jar" "app.jar"
RUN apt-get update
RUN apt-get install nano net-tools
EXPOSE 2020
ENTRYPOINT [ "java", "-jar", "app.jar" ]