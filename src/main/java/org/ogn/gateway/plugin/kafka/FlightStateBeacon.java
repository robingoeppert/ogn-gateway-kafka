package org.ogn.gateway.plugin.kafka;

import org.ogn.commons.beacon.AircraftBeacon;


/**
 * Flight information of an aircraft to a specific moment.
 * Overall a simplified and anonymized version of OGN's AircraftBeacon
 */
public class FlightStateBeacon {
    private long timestamp;
    private double latitude;
    private double longitude;
    private float altitude;
    private float climbRate;


    public FlightStateBeacon(long timestamp, double latitude, double longitude, float altitude, float climbRate) {
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.climbRate = climbRate;
    }


    public static FlightStateBeacon fromAircraftBeacon(final AircraftBeacon aircraftBeacon) {
        return new FlightStateBeacon(
                aircraftBeacon.getTimestamp(),
                aircraftBeacon.getLat(),
                aircraftBeacon.getLon(),
                aircraftBeacon.getAlt(),
                aircraftBeacon.getClimbRate()
        );
    }
}
