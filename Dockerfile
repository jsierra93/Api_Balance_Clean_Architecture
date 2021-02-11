FROM adoptopenjdk/openjdk11-openj9
VOLUME /tmp
COPY applications/app-service/build/libs/app-service.jar app.jar
EXPOSE 8081
CMD ["java","-jar","app.jar"]
