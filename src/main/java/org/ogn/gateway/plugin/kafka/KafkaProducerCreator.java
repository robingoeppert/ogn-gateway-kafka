package org.ogn.gateway.plugin.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class KafkaProducerCreator {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaProducerCreator.class);

    private Properties properties;


    public KafkaProducerCreator() {
        try {
            properties = loadProperties();
        } catch (Exception e) {
            properties = new Properties();
            LOG.warn("Properties for Kafka Producer could not be loaded from file kafka.properties", e);
        }
    }

    public KafkaProducerCreator(final Properties properties) {
        this.properties = properties;
    }


    public Producer create() {
        //Thread.currentThread().setContextClassLoader(null);
        return new KafkaProducer<Long, String>(properties);
    }


    private Properties loadProperties() throws IOException {
        InputStream propertyStream = this.getClass().getResourceAsStream("/kafka.properties");
        Properties properties = new Properties();
        properties.load(propertyStream);

        final String envKafkaServers = System.getenv("KAFKA_SERVERS");
        if (envKafkaServers != null) properties.setProperty("bootstrap.servers", envKafkaServers);

        final StringBuilder allProps = new StringBuilder();
        properties.forEach((k, v) -> allProps.append(k).append("=").append(v).append(", "));
        LOG.info("Kafka producer properties: " + allProps.toString());

        return properties;
    }
}
