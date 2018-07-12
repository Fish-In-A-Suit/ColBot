package utilities;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
	/**
	 * This method eliminates whitespace characters ("") out of a String array input as parameter
	 * and returns a new array of Strings without these whitespace characters
	 * @param string
	 * @return
	 */
	public static String[] eliminateWhitespaceCharacters(String[] string) {
		List<String> refinedList = new ArrayList<>();
		
		for (String s : string) {
			if (!s.equals("")) {
				refinedList.add(s);
			}
		}
		
		String[] refinedStringArray = new String[refinedList.size()];
		
		for(int i = 0; i < refinedStringArray.length; i++) {
			refinedStringArray[i] = refinedList.get(i);
		}
		
		return refinedStringArray;
	}

}
