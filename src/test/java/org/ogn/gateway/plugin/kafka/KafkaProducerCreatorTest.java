package org.ogn.gateway.plugin.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;


public class KafkaProducerCreatorTest {

    @Test
    public void create() {
        final Properties properties = new Properties();
        properties.setProperty("client.id", "ogn-gateway-test");
        properties.setProperty("bootstrap.servers", "localhost:1234");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.LongSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        final KafkaProducerCreator creator = new KafkaProducerCreator(properties);

        assertTrue(creator.create() instanceof KafkaProducer);
    }
}