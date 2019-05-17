FROM openjdk:13-alpine

# Create directory for deployment
WORKDIR /opt
RUN mkdir ogn-gateway-kafka
WORKDIR /opt/ogn-gateway-kafka

EXPOSE 61628

# Add the service itself
ARG JAR_FILE
ADD target/${JAR_FILE} /opt/ogn-gateway-kafka/ogn-gateway-kafka.jar

ENTRYPOINT ["java", "-jar", "/opt/ogn-gateway-kafka/ogn-gateway-kafka.jar"]




#WORKDIR /opt
#RUN mkdir ogn-gateway-kafka
#WORKDIR /opt/ogn-gateway-kafka
#COPY target/ogn-gateway-kafka-*-jar-with-dependencies.jar .
#CMD java -jar