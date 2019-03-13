package org.ogn.gateway.plugin.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.ogn.commons.beacon.AircraftBeacon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class AircraftBeaconSerializer implements Serializer<AircraftBeacon> {

    private static final Logger LOG = LoggerFactory.getLogger(AircraftBeaconSerializer.class);


    @Override
    public void configure(final Map<String, ?> map, final boolean b) {

    }

    @Override
    public byte[] serialize(final String topic, final AircraftBeacon aircraftBeacon) {

        if (aircraftBeacon != null) {
            try {
                final ObjectMapper objectMapper = new ObjectMapper();
                final String valueString = objectMapper.writeValueAsString(aircraftBeacon);
                return valueString.getBytes();
            } catch (JsonProcessingException e) {
                LOG.warn("Could not serialize AircraftBeacon", e);
                return null;
            }
        } else {
            LOG.warn("Cannot serialize a null AircraftBeacon");
            return null;
        }
    }

    @Override
    public void close() {

    }
}
