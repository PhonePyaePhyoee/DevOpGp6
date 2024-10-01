FROM openjdk:17
COPY ./target/DevOpsnew-0.1.0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "DevOpsnew-0.1.0.1-jar-with-dependencies.jar"]