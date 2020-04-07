FROM maven:3.6.3-jdk-8

COPY . /usr/app
WORKDIR /usr/app

RUN mvn clean install

FROM java:8-jdk-alpine

COPY --from=0 /usr/app/target /usr/app

WORKDIR /usr/app

ENTRYPOINT ["java","-jar","mockymocks.jar"]