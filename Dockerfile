FROM amazoncorretto:20
MAINTAINER MFV
COPY target/mfv-0.0.1-SNAPSHOT.jar mfv-app.jar
ENTRYPOINT ["java","-jar","/mfv-app.jar"]
EXPOSE 8080