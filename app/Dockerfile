# First build the demo app

FROM maven:3.6.3-openjdk-11 as build

ARG BUILD_ID=0
ENV BUILD_ID=${BUILD_ID}
LABEL stage=demo-app-builder
LABEL build=${BUILD_ID}

WORKDIR /greensight-demo/app

COPY ./pom.xml /greensight-demo/app/pom.xml
COPY ./src /greensight-demo/app/src

RUN mvn -Pdemo -Dmaven.test.skip clean package spring-boot:repackage


## Second, package the demo app

FROM openjdk:12-alpine as package

WORKDIR /user/local/app

ARG JAR_NAME=greensight-demo-0.0.1-SNAPSHOT.war
COPY --from=build /greensight-demo/app/target/${JAR_NAME} /user/local/app/app.war

EXPOSE 8888

ENTRYPOINT ["java","-jar","/user/local/app/app.war"]
