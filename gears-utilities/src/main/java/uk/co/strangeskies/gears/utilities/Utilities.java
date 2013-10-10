package uk.co.strangeskies.gears.utilities;

public class Utilities {
	public static boolean areEqual(Object first, Object second) {
		if (first == null) {
			if (second != null) {
				return false;
			}
		} else if (second == null || !first.equals(second)) {
			return false;
		}
		return true;
	}
}
