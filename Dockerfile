# syntax=docker/dockerfile:1

FROM openjdk:11 as prod
WORKDIR /app
RUN echo App is ready
ENV SPRING_PROFILES_ACTIVE=prod
COPY /spring-app/target/body-builder.jar .
CMD ["java", "-jar", "body-builder.jar"]


FROM prod as dev
ENV SPRING_PROFILES_ACTIVE=dev
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000", "-jar", "body-builder.jar"]