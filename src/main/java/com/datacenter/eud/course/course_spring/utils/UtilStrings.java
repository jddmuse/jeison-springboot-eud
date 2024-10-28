package com.datacenter.eud.course.course_spring.utils;

public final class UtilStrings {
	
	private UtilStrings() {};
	
	public static String capitalize(String str) {
		
	    return (str == null || str.isEmpty()) ? str 
	        : str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}
	
	public static Character repeatedLetter(String str) {
        if (str == null || str.length() < 2) {
            return null;
        }

        String lowerStr = str.toLowerCase();

        for (int i = 0; i < lowerStr.length() - 1; i++) {
            char currentChar = lowerStr.charAt(i);
            char nextChar = lowerStr.charAt(i + 1);

            if (currentChar == nextChar) {
                return str.charAt(i);
            }
        }

        return null;
    }
	
}
