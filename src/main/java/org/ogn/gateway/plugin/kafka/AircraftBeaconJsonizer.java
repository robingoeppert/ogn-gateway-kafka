package org.ogn.gateway.plugin.kafka;

import org.ogn.commons.beacon.AircraftBeacon;
import org.ogn.commons.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AircraftBeaconJsonizer {

    private static final Logger LOG = LoggerFactory.getLogger(AircraftBeaconJsonizer.class);


    public static String toJson(final AircraftBeacon aircraftBeacon) {
        if (aircraftBeacon != null) {
            return JsonUtils.toJson(aircraftBeacon).replaceAll("\\r\\n", "");
        } else {
            LOG.warn("Cannot jsonize a null AircraftBeacon");
            return null;
        }
    }
}
