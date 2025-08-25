FROM amazoncorretto:21
WORKDIR /app
COPY build/libs/benford-validiator-all.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]