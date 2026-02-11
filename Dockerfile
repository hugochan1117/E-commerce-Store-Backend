FROM eclipse-temurin:21-jre-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ./build/libs/lab_project_backend-3.0.2.jar Project_Backend.jar
ENTRYPOINT ["java","-jar","/Project_Backend.jar"]