import java.util.Comparator;

import oop.ex3.searchengine.Hotel;


/**
 * A comparator for the Hotel class, compares distance to  a certain location,
 * followed by POI number near the hotel if the distance was equal
 *
 */
public class DistancePoiComparator implements Comparator<Hotel> {
	
	private double longi, lati;
	
	public DistancePoiComparator(double longitude, double latitude) {
		longi = longitude;
		lati = latitude;
	}

	@Override
	public int compare(Hotel h1, Hotel h2) {
			double lati1 = h1.getLatitude(), longi1 = h1.getLongitude();
			double lati2 = h2.getLatitude(), longi2 = h2.getLongitude();
			double distance1 = distance(lati, longi, lati1, longi1);
			double distance2 = distance(lati, longi, lati2, longi2);
			if(distance1 > distance2)
				return 1;
			if(distance1 < distance2)
				return -1;
			if(h1.getNumPOI() > h2.getNumPOI())
				return 1;
			if(h1.getNumPOI() < h2.getNumPOI())
				return -1;
			return 0;
	}
	
	/**
	 * Calculates the euclidean distance between 2 locations
	 * @param lati1 Location's latitude (l1)
	 * @param longi1 Location's longitude (l1)
	 * @param lati2 Location's latitude (l2)
	 * @param longi2 Location's longitude (l2)
	 * @return The distance between the two given location
	 */
	public static double distance(double lati1, double longi1, double lati2, double longi2) {
		return Math.sqrt(Math.pow(lati1 - lati2, 2) + Math.pow(longi1 - longi2, 2));
	}
}
