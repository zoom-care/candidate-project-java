FROM openjdk:8-windowsservercore

LABEL maintainer="general@richabaker.com"
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=5006,server=y,suspend=n
COPY challenge-0.0.1-SNAPSHOT.jar /challenge.jar
EXPOSE 8080 5006

CMD ["java", "-jar", "challenge.jar"]