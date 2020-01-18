package oop.ex6.parsers.initial;

import java.util.function.Predicate;

/**
 * A LineFilters class, holds all of the filters for the code lines
 * used when attempting to to find the type of a given text line
 */
public class LineFilters {
	
	
	/**
	 * An empty string constant
	 */
	private static final String EMPTY_STR = "";
	
	/**
	 * A comment line starts with this value
	 */
	private static final String COMMENT_LINE = "//";
	
	/**
	 * The white space regex constant
	 */
	private static final String WHITE_SPACE_REGEX = "\\s";
	
	/**
	 * A code block open line ends with this value
	 */
	private static final String CODE_BLOCK_OPEN = "{";
	
	/**
	 * A code line ends with this value
	 */
	private static final String END_OF_CODE_LINE = ";";
	
	/**
	 * A code block closing line ends with this value
	 */
	private static final String CODE_BLOCK_CLOSE = "}";

	
	/**
	 * @param line A Given line type of the LineTypes enum
	 * @return The right predicate for the given type,
	 * when an actual comment line is tested using the comment line
	 * predicate it should always return true
	 */
	public static Predicate<String> getFilter(LineTypes line) {
		switch(line) {
		case EMPTY:
			return getEmptyLineFilter();
		case COMMENT:
			return getCommentLineFilter();
		case CODE:
			return getCodeLineFilter(Character.valueOf(END_OF_CODE_LINE.charAt(0)));
		case CODE_CLOSE:
			return getCodeLineFilter(Character.valueOf(CODE_BLOCK_CLOSE.charAt(0)));
		case CODE_OPEN:
			return getCodeLineFilter(Character.valueOf(CODE_BLOCK_OPEN.charAt(0)));
		default:
			return null;
		}
	}
	
	/**
	 * @return The predicate function that tests whether a line is an empty line
	 */
	private static Predicate<String> getEmptyLineFilter() {
		return new Predicate<String>() {
			@Override
			public boolean test(String test) {
				return test.replaceAll(LineFilters.WHITE_SPACE_REGEX, LineFilters.EMPTY_STR).equals(EMPTY_STR);
			}
		};
	}
	
	/**
	 * @return The predicate function that tests whether a line is a comment line
	 */
	private static Predicate<String> getCommentLineFilter() {
		return new Predicate<String>() {
			@Override
			public boolean test(String test) {
				return test.startsWith(LineFilters.COMMENT_LINE);
			}
		};
	}
	
	/**
	 * @param character The end of line character for a code line, class constants
	 * @return The correct predicate function for the CODE_LINE, CODE_BLOCK_OPEN,
	 * CODE_BLOCK_CLOSE line type
	 */
	private static Predicate<String> getCodeLineFilter(char character) {
		return new Predicate<String>() {
			@Override
			public boolean test(String test) {
				String removedSpaces = test.replaceAll(LineFilters.WHITE_SPACE_REGEX,
						LineFilters.EMPTY_STR);
				if(removedSpaces.length() == 0)
					return false;
				boolean endsWith = removedSpaces.endsWith(String.valueOf(character));
				boolean count = countLetterAppearences(removedSpaces, character) == 1;
				return endsWith && count;
			}
		};
	}
	
	/**
	 * Counts a letter appearance in the given string
	 * @param str Given string
	 * @param ch Given character
	 * @return How many times the given character appears in the given
	 * string
	 */
	private static int countLetterAppearences(String str, char ch) {
		int count = 0;
		for(int i = 0; i < str.length(); i++) 
			count = str.charAt(i) == ch ? count + 1 : count;
		return count;
	}
}
