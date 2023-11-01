package com.amx.conectio.evento;

public class UbicacionUtil {
	
    public static double calculateHaversineDistance(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6371; // Radius of the Earth in kilometers

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }

}
