import java.util.Comparator;

import oop.ex3.searchengine.Hotel;

/**
 * A comparator for the hotel class, compares star rating followed by property
 * name comparison if the star rating was equal
 *
 */
public class StarRatingComparator implements Comparator<Hotel> {
	
	
	public StarRatingComparator() {}
	
	@Override
	public int compare(Hotel h1, Hotel h2) {
		int rating1 = h1.getStarRating(), rating2 = h2.getStarRating();
		if(rating1 > rating2)
			return 1;
		if(rating1 < rating2)
			return -1;
		return h1.getPropertyName().compareTo(h2.getPropertyName());
	}

}
