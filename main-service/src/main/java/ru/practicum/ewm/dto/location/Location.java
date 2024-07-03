package ru.practicum.ewm.dto.location;

import lombok.Data;

@Data
public class Location {

    private float lat;
    private float lon;

    public Location(Float locationLat, Float locationLon) {
        lat = locationLat;
        lon = locationLon;
    }

}
