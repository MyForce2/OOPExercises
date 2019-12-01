import java.util.ArrayList;
import java.util.Comparator;

import oop.ex3.searchengine.Hotel;
import oop.ex3.searchengine.HotelDataset;

public class BoopingSite {
	
	private String dataSetName;
	
	private final static Comparator<Hotel> STAR_RATING_COMPARATOR = new Comparator<Hotel>() {

		@Override
		public int compare(Hotel arg0, Hotel arg1) {
			return 0;
		}
	};
	
	public BoopingSite(String name) {
		this.dataSetName = name;
	}
	
	
	
	public Hotel[] getHotelsInCityByRating(String city) {
		Hotel[] hotels = HotelDataset.getHotels(dataSetName);
		ArrayList<Hotel> hotelsInCity = new ArrayList<Hotel>();
		for(Hotel hotel : hotels) {
			if(hotel.getCity().equals(city))
				hotelsInCity.add(hotel);
		}
		if(hotelsInCity.isEmpty())
			return new Hotel[0];
		return null;
	}
	
	
	public Hotel[] getHotelsByProximity(double latitude, double longitude) {
		
	}
	
	public Hote[] getHotelsInCityByProximity(String city, double latitude, double longitude) {
		
	}
}
