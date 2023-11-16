FROM maven:3.9-amazoncorretto-8 AS build
WORKDIR /usr/src/app
COPY src ./src
COPY .env .
COPY pom.xml .
RUN mvn -f pom.xml clean package

FROM amazoncorretto:8-alpine-jdk
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/drawl_soap-jar-with-dependencies.jar ./target/
COPY .env .
EXPOSE 8083
CMD ["java","-jar","./target/drawl_soap-jar-with-dependencies.jar"]