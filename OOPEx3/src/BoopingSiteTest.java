import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import oop.ex3.searchengine.Hotel;
import oop.ex3.searchengine.HotelDataset;

public class BoopingSiteTest {
	
	/**
	 * Path for whole data set file
	 */
	private static final String WHOLE_DATASET = "hotels_dataset.txt";
	
	/**
	 * Path for data set one (reduced data set)
	 */
	private static final String DATASET_ONE = "hotels_tst1.txt";
	
	
	/**
	 * Path for data set two (empty data set)
	 */
	private static final String DATASET_TWO = "hotels_tst2.txt";
	
	/**
	 * A size of an empty array
	 */
	private static final int EMPTY_ARRAY_SIZE = 0;
	
	/**
	 * Booping site latitude cap
	 */
	private static final int LATITUDE_CAP = 90;
	
	/**
	 * Booping site longitude cap
	 */
	private static final int LONGITUDE_CAP = 180;

	
	/**
	 * A booping site class using the whole data set
	 */
	private static BoopingSite bsWholeDataset;
	
	/**
	 * A booping site class using the reduced data set (set one)
	 */
	private static BoopingSite bsDatasetOne;
	
	/**
	 * A booping site class using the empty data set (set two)
	 */
	private static BoopingSite bsDataSetTwo;


	@BeforeClass
	public static void createObjects() {
		bsWholeDataset = new BoopingSite(BoopingSiteTest.WHOLE_DATASET);
		bsDatasetOne = new BoopingSite(BoopingSiteTest.DATASET_ONE);
		bsDataSetTwo = new BoopingSite(BoopingSiteTest.DATASET_TWO);
	}



	@Test
	/**
	 * A test function for getHotelsInCityByRating method in the booping
	 * site class
	 */
	public void testHotelsByRating() {
		BoopingSite[] sets = { bsWholeDataset, bsDatasetOne, bsDataSetTwo };
		String[] setFiles = { WHOLE_DATASET, DATASET_ONE, DATASET_TWO };
		for(int i = 0; i < sets.length; i++) {
			BoopingSite set = sets[i];
			String setFile = setFiles[i];
			assertTrue("BS class hotels by rating doesn't return an empty array for a non valid input", 
					set.getHotelsInCityByRating("").length == EMPTY_ARRAY_SIZE);
			Hotel[] hot = HotelDataset.getHotels(setFile);
			if(hot.length == 0)
				return;
			String cityName = hot[0].getCity();
			assertTrue("BS class hotels by rating returns an empty array for a valid city name"
					, set.getHotelsInCityByRating(cityName).length != EMPTY_ARRAY_SIZE);
			Hotel[] hotels = set.getHotelsInCityByRating(cityName);
			Hotel prev = hotels[0];
			for(int j = 1; j < hotels.length; j++) {
				assertTrue("BS class hotels by rating isn't sorted correctly", 
						prev.getStarRating() >= hotels[j].getStarRating());
				if(prev.getStarRating() == hotels[j].getStarRating())
					assertTrue("BS class hotels by rating isn't sorted correctly when"
							+ " hotels star rating are the same", 
							prev.getPropertyName().compareTo(hotels[j].getPropertyName()) >= 0);
				prev = hotels[j];
			}
		}
	}

	@Test
	/**
	 * A test function for the getHotelsIsInCityByProximity in the booping
	 * site class
	 */
	public void testHotelsInCityByProximity() {
		BoopingSite[] sets = { bsWholeDataset, bsDatasetOne, bsDataSetTwo };
		String[] setFiles = { WHOLE_DATASET, DATASET_ONE, DATASET_TWO };
		for(int i = 0; i < sets.length; i++) {
			BoopingSite set = sets[i];
			String setFile = setFiles[i];
			assertTrue("BS class hotels by proximity doesn't return an empty array for a non valid input",
					set.getHotelsInCityByProximity("", 0, 0).length
					== EMPTY_ARRAY_SIZE);
			Hotel[] hot = HotelDataset.getHotels(setFile);
			if(hot.length == 0)
				return;
			String cityName = hot[0].getCity();
			assertTrue("BS class hotels by proximity in city  doesn't return an empty array for a non valid input",
					set.getHotelsInCityByProximity(cityName, LATITUDE_CAP + 1, 0).length
					== EMPTY_ARRAY_SIZE);
			assertTrue("BS class hotels by proximity in city doesn't return an empty array for a non valid input",
					set.getHotelsInCityByProximity(cityName, -LATITUDE_CAP - 1, 0).length
					== EMPTY_ARRAY_SIZE);
			assertTrue("BS class hotels by proximity in city doesn't return an empty array for a non valid input",
					set.getHotelsInCityByProximity(cityName, 0, LONGITUDE_CAP + 1).length
					== EMPTY_ARRAY_SIZE);
			assertTrue("BS class hotels by proximity in city doesn't return an empty array for a non valid input",
					set.getHotelsInCityByProximity(cityName, 0, -LONGITUDE_CAP - 1).length
					== EMPTY_ARRAY_SIZE);
			assertTrue("BS class hotels by proximity in city doesn't return a valid array for valid values", 
					set.getHotelsInCityByProximity(cityName, 0, 0).length 
					!= EMPTY_ARRAY_SIZE);
			Hotel[] hotels = set.getHotelsInCityByProximity(cityName, 0.0, 0.0);
			if(hotels.length == 0)
				return;
			Hotel prev = hotels[0];
			String city = prev.getCity();
			double prevDistance = DistancePoiComparator.distance(0.0, 0.0, prev.getLatitude(), prev.getLongitude());
			for(int j = 1; j < hotels.length; j++) {
				Hotel h = hotels[j];
				double distance = DistancePoiComparator.distance(0.0, 0.0, h.getLatitude(), h.getLongitude());
				assertTrue("BS class hotels by proximity in city has different cities in it", city.equals(h.getCity()));
				assertTrue("BS class hotels by proximity in city isn't sorted correctly", distance >= prevDistance);
				if(distance == prevDistance) 
					assertTrue("BS class hotels by proximity in city isn't sorted correctly when "
							+ " distances are the same", prev.getNumPOI() <= h.getNumPOI());
				prevDistance = distance;
				prev = h;
			}
		}
	}
	
	@Test
	/**
	 * A test function for the getHotelsByProximity method in
	 * the booping site class
	 */
	public void testHotelsByProximity() {
		BoopingSite[] sets = { bsWholeDataset, bsDatasetOne, bsDataSetTwo };
		for(int i = 0; i < sets.length; i++) {
			BoopingSite set = sets[i];
			assertTrue("BS class hotels by proximity doesn't return an empty array for a non valid input",
					set.getHotelsByProximity(LATITUDE_CAP + 1, 0).length
					== EMPTY_ARRAY_SIZE);
			assertTrue("BS class hotels by proximity doesn't return an empty array for a non valid input",
					set.getHotelsByProximity(-LATITUDE_CAP - 1, 0).length
					== EMPTY_ARRAY_SIZE);
			assertTrue("BS class hotels by proximity doesn't return an empty array for a non valid input",
					set.getHotelsByProximity(0, LONGITUDE_CAP + 1).length
					== EMPTY_ARRAY_SIZE);
			assertTrue("BS class hotels by proximity doesn't return an empty array for a non valid input",
					set.getHotelsByProximity(0, -LONGITUDE_CAP - 1).length
					== EMPTY_ARRAY_SIZE);
			Hotel[] hotels = set.getHotelsByProximity(0.0, 0.0);
			if(hotels.length == EMPTY_ARRAY_SIZE)
				return;
			Hotel prev = hotels[0];
			double prevDistance = DistancePoiComparator.distance(0.0, 0.0, prev.getLatitude(), prev.getLongitude());
			for(int j = 1; j < hotels.length; j++) {
				Hotel h = hotels[j];
				double distance = DistancePoiComparator.distance(0.0, 0.0, h.getLatitude(), h.getLongitude());
				assertTrue("BS class hotels by proximity isn't sorted correctly", distance >= prevDistance);
				if(distance == prevDistance) 
					assertTrue("BS class hotels by proximity isn't sorted correctly when "
							+ " distances are the same", prev.getNumPOI() <= h.getNumPOI());
				prevDistance = distance;
				prev = h;
			}
		}
	}
}