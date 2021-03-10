FROM openjdk:8
WORKDIR /home
COPY ./target/tarificador-rest-0.0.1-SNAPSHOT.jar /usr/src/app/tarificador-rest-0.0.1-SNAPSHOT.jar
#EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/src/app/tarificador-rest-0.0.1-SNAPSHOT.jar"]