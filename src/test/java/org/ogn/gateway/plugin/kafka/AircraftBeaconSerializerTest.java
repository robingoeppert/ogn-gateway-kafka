package org.ogn.gateway.plugin.kafka;

import org.junit.Before;
import org.junit.Test;
import org.ogn.commons.beacon.AircraftBeacon;
import org.ogn.commons.beacon.impl.aprs.AprsLineParser;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


public class AircraftBeaconSerializerTest {

    private final String beacon = "PH-844>APRS,qAS,EHHO:/102536h5244.42N/00632.32E'090/075/A=000813 id06DD82AC +198fpm -0.1rot 19.0dB 0e +0.1kHz gps2x3 hear8222 hear9350 hearA4EC";
    private AircraftBeaconSerializer serializer;


    @Before
    public void setUp() {
        this.serializer = new AircraftBeaconSerializer();
    }


    @Test
    public void serialize_withTopicAndBeacon_shouldNotBeNull() {
        final String givenTopic = "someTopic";
        final AircraftBeacon aircraftBeacon = (AircraftBeacon) AprsLineParser.get().parse(beacon);

        final byte[] result = serializer.serialize(givenTopic, aircraftBeacon);

        assertNotNull(result);
    }

    @Test
    public void serialize_withoutAircraftBeacon_shouldBeNull() {
        final byte[] result = serializer.serialize("someTopic", null);

        assertNull(result);
    }
}