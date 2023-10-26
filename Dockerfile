FROM maven:3-amazoncorretto-8 AS build
WORKDIR /app
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM amazoncorretto:8-alpine-jdk
EXPOSE 8082
COPY --from=build /usr/src/app/target/drawl_soap-jar-with-dependencies.jar /usr/src/app/drawl_soap.jar
CMD ["java","-jar","/usr/src/app/service_soap.jar"]