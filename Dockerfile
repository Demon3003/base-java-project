# syntax=docker/dockerfile:1

FROM openjdk:11 as dev
WORKDIR /app
RUN echo App is ready
COPY /spring-app/target/base-app.jar .
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000", "-jar", "base-app.jar"]


FROM dev as prod
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "base-app.jar"]
