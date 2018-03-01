FROM java
MAINTAINER abirdatta
COPY ./target/user-api-0.0.1-SNAPSHOT.jar /usr/local/user-api/
RUN ls -al /usr/local/user-api
EXPOSE 9000 9001
VOLUME /logs
WORKDIR /usr/local/user-api
ENTRYPOINT java -jar user-api-0.0.1-SNAPSHOT.jar
