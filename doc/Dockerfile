FROM java:8u111-jre-alpine

MAINTAINER Dean dean@gmail.com

ENV SERVER_IP=192.168.43.99

RUN mkdir /workspace

COPY basic-1.0-SNAPSHOT.jar /workspace/basic.war

ENTRYPOINT ["java","-jar","/workspace/basic.war","--docker.ip=${SERVER_IP}"]