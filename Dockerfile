FROM openjdk:11-jre
LABEL maintainer="Pablo Rengifo"
ENV spring.application.name java-candidate-challenge-v1
ADD src/main/resources/db/migration src/main/resources/db.migration
COPY target/java-candidate-challenge-0.0.1-SNAPSHOT.jar /opt/java-candidate-challenge.jar
ENTRYPOINT ["java","-Djava.file.encoding=UTF-8","-jar","/opt/java-candidate-challenge.jar"]
