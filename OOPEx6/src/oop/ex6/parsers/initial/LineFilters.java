package oop.ex6.parsers.initial;

import java.util.function.Predicate;

public class LineFilters {
	
	
	private static final String EMPTY_STR = "";
	
	private static final String COMMENT_LINE = "//";
	
	private static final String WHITE_SPACE_REGEX = "\\s";
	
	private static final String CODE_BLOCK_OPEN = "{";
	
	private static final String END_OF_CODE_LINE = ";";
	
	private static final String CODE_BLOCK_CLOSE = "}";
	
	private static final char UNDER_SCORE = '_';
	
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
	
	private static Predicate<String> getEmptyLineFilter() {
		return new Predicate<String>() {
			@Override
			public boolean test(String test) {
				return test.replaceAll(LineFilters.WHITE_SPACE_REGEX, LineFilters.EMPTY_STR).equals(EMPTY_STR);
			}
		};
	}
	
	private static Predicate<String> getCommentLineFilter() {
		return new Predicate<String>() {
			@Override
			public boolean test(String test) {
				return test.startsWith(LineFilters.COMMENT_LINE);
			}
		};
	}
	
	private static Predicate<String> getCodeLineFilter(char character) {
		return new Predicate<String>() {
			@Override
			public boolean test(String test) {
				String removedSpaces = test.replaceAll(LineFilters.WHITE_SPACE_REGEX,
						LineFilters.EMPTY_STR);
				if(removedSpaces.length() == 0)
					return false;
				boolean startsWith = Character.isLetter(removedSpaces.charAt(0)) || 
						removedSpaces.charAt(0) == LineFilters.UNDER_SCORE;
				boolean endsWith = removedSpaces.endsWith(String.valueOf(character));
				boolean count = countLetterAppearences(removedSpaces, character) == 1;
				return endsWith && count;
			}
		};
	}
	

	private static int countLetterAppearences(String str, char ch) {
		int count = 0;
		for(int i = 0; i < str.length(); i++) 
			count = str.charAt(i) == ch ? count + 1 : count;
		return count;
	}
}
