package util;

public class StringUtil {

	public static boolean isValid(String str) {
		if(str == null || str.equals("")) {
			return false;
		}
		return true;
	}
	
}
