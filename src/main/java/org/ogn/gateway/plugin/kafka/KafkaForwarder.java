package org.ogn.gateway.plugin.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.ogn.commons.beacon.AircraftBeacon;
import org.ogn.commons.beacon.AircraftDescriptor;
import org.ogn.commons.beacon.forwarder.OgnAircraftBeaconForwarder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ExecutionException;


/**
 * Forwards AircraftBeacons into Kafka
 */
public class KafkaForwarder implements OgnAircraftBeaconForwarder {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaForwarder.class);
    private static final String KAFKA_TOPIC_NAME = "aircraftBeacon";

    private boolean initialized;
    private Producer<Long, AircraftBeacon> kafkaProducer;


    public KafkaForwarder() {
        this.initialized = false;
        LOG.info("KafkaForwarder instance created");
    }


    @Override
    public void onBeacon(final AircraftBeacon aircraftBeacon, final Optional<AircraftDescriptor> optionalAircraftDescriptor) {
        final ProducerRecord<Long, AircraftBeacon> record = new ProducerRecord<>(KAFKA_TOPIC_NAME, aircraftBeacon);

        try {
            this.kafkaProducer.send(record).get();
            LOG.info("AircraftBeacon of " + aircraftBeacon.getId() + " sent into kafka");
        } catch (InterruptedException e) {
            LOG.warn("Could not send aircraftBeacon of " + aircraftBeacon.getId() + " into Kafka", e);
        } catch (ExecutionException e) {
            LOG.warn("Could not send aircraftBeacon of " + aircraftBeacon.getId() + " into Kafka", e);
        }
    }

    @Override
    public void init() {
        if (!this.initialized) {
            final KafkaProducerCreator producerCreator = new KafkaProducerCreator();
            this.kafkaProducer = producerCreator.create();
            this.initialized = true;
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public String getName() {
        return "kafka forwarder";
    }

    @Override
    public String getVersion() {
        return "0.1.0";
    }

    @Override
    public String getDescription() {
        return "Forwards OGN aircraft beacons into Apache Kafka";
    }
}
