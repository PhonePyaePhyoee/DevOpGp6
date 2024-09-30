FROM openjdk:17

COPY ./target/DevOpsnew-0.1.0.2-jar-with-dependencies.jar ./temp/
WORKDIR ./temp
ENTRYPOINT ["java", "-jar", "DevOpsnew-0.1.0.2-jar-with-dependencies.jar"]
#rr