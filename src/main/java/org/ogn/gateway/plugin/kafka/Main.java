package org.ogn.gateway.plugin.kafka;

import org.ogn.client.AircraftBeaconListener;
import org.ogn.client.OgnClient;
import org.ogn.client.OgnClientFactory;
import org.ogn.commons.beacon.AircraftBeacon;
import org.ogn.commons.beacon.AircraftDescriptor;

import java.util.Optional;


public class Main {

    public static void main(String[] args) throws Exception {
        final OgnClient client = OgnClientFactory.createClient();
        final KafkaForwarder forwarder = new KafkaForwarder();
        forwarder.init();

        System.out.println("connecting...");

        client.connect();

        client.subscribeToAircraftBeacons(new AircraftBeaconListener() {
            @Override
            public void onUpdate(AircraftBeacon aircraftBeacon, Optional<AircraftDescriptor> descriptorOptional) {
                forwarder.onBeacon(aircraftBeacon, descriptorOptional);
            }
        });

        Thread.sleep(Long.MAX_VALUE);
    }
}
