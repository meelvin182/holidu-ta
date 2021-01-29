FROM openjdk:14-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=build
RUN echo ${DEPENDENCY}
COPY ${DEPENDENCY}/libs/tree-radius-1.0.0.jar /app/lib/tree-radius.jar
ENTRYPOINT ["java","-jar","/app/lib/tree-radius.jar"]