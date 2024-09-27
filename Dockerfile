FROM openjdk:17

COPY ./target/classes/group6 ./temp/group6
WORKDIR ./temp

ENTRYPOINT ["java","group6.Main"]