FROM openjdk:14-alpine
RUN apk update \
  && apk upgrade \
  && apk --no-cache add --update npm
COPY backend/build/libs/backend-0.1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]