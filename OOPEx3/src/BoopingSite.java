import java.util.Arrays;
import java.util.Comparator;

import oop.ex3.searchengine.Hotel;
import oop.ex3.searchengine.HotelDataset;

public class BoopingSite {
	
	/**
	 * The size of the empty array returned when an invalid input
	 * is given to any of the methods
	 */
	public static final int EMPTY_ARRAY_SIZE = 0;
	
	/**
	 * The longitude cap (in absolute value)
	 */
	public static final double LONGITUDE_CAP = 180.0;
	
	/**
	 * The latitude cap (in absolute value)
	 */
	public static final double LATITUDE_CAP = 90.0;
	
	/**
	 * The dataset file name used by this booping site instance
	 */
	private String dataSetName;

	
	public BoopingSite(String name) {
		this.dataSetName = name;
	}
	
	
	/**
	 * A method to get an array of hotels in the given city sorted by star rating
	 * @param city The city in which the hotels should be
	 * @return A sorted array by star rating containing all of the hotels in the given city
	 */
	public Hotel[] getHotelsInCityByRating(String city) {
		Hotel[] hotels = HotelDataset.getHotels(dataSetName);
		Hotel[] hotelsInCity = getHotelsInCity(hotels, city);
		Comparator<Hotel> cmp = new StarRatingComparator();
		// The array needs to be sorted
		if(hotelsInCity.length >= 2) {
			Arrays.sort(hotelsInCity, cmp.reversed());
		}
		return hotelsInCity;
	}
	
	private Hotel[] getHotelsInCity(Hotel[] hotels, String city) {
		int size = 0;
		for(Hotel hotel : hotels) {
			if(hotel.getCity().equals(city))
				size++;
		}
		if(size == 0)
			return new Hotel[BoopingSite.EMPTY_ARRAY_SIZE];
		Hotel[] hotelInCity = new Hotel[size];
		int index = 0;
		for(int i = 0; i < hotels.length; i++) {
			if(hotels[i].getCity().equals(city)) {
				hotelInCity[index] = hotels[i];
				index++;
			}
		}
		return hotelInCity;
	}
	
	/**
	 * A method to get an array of all of the hotels sorted by distance to the given 
	 * location
	 * @param latitude Location's latitude
	 * @param longitude Location's longitude
	 * @return An array containing all of the hotels sorted by distance to the given location
	 */
	public Hotel[] getHotelsByProximity(double latitude, double longitude) {
		Hotel[] hotels = HotelDataset.getHotels(dataSetName);
		if(Math.abs(latitude) >= BoopingSite.LATITUDE_CAP || 
				Math.abs(longitude) >= BoopingSite.LONGITUDE_CAP)
			return new Hotel[BoopingSite.EMPTY_ARRAY_SIZE];
		Hotel[] byProximity = new Hotel[hotels.length];
		for(int i = 0; i < byProximity.length; i++)
			byProximity[i] = hotels[i];
		Comparator<Hotel> cmp = new DistancePoiComparator(longitude, latitude);
		Arrays.sort(byProximity, cmp);
		return byProximity;
	}
	
	/**
	 * A method to get an array of all of the hotels in the given city sorted by distance to the
	 * given location
	 * @param city City in which the hotels resides
	 * @param latitude Location's latitude
	 * @param longitude Location's longitude
	 * @return An array containing all of the hotels in the given city sorted by distance to the
	 * give location
	 */
	public Hotel[] getHotelsInCityByProximity(String city, double latitude, double longitude) {
		Hotel[] hotels = HotelDataset.getHotels(dataSetName);
		if(Math.abs(latitude) >= BoopingSite.LATITUDE_CAP || 
				Math.abs(longitude) >= BoopingSite.LONGITUDE_CAP)
			return new Hotel[BoopingSite.EMPTY_ARRAY_SIZE];
		Hotel[] inCity = getHotelsInCity(hotels, city);
		// The array needs to be sorted
		if(inCity.length >= 2) {
			Comparator<Hotel> cmp = new DistancePoiComparator(longitude, latitude);
			Arrays.sort(inCity, cmp);
		}
		return inCity;
	}
}
