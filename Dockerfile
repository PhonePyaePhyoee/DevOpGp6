FROM openjdk:17

COPY ./target/DevOpsnew-1.0-SNAPSHOT-jar-with-dependencies.jar ./temp/
WORKDIR ./temp
ENTRYPOINT ["java", "-jar", "DevOpsnew-1.0-SNAPSHOT-jar-with-dependencies.jar"]