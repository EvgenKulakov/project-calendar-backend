FROM gradle:7.5.1-jdk17 as builder

WORKDIR /app

COPY . .

RUN gradle build --no-daemon --exclude-task test

FROM bellsoft/liberica-openjdk-debian:17

RUN mkdir /app

COPY --from=builder /app/build/libs/project-calendar-backend-0.0.1.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
