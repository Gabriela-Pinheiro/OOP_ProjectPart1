//Gabriela Pinheiro - R00225375 - Project_Part2

package validation;

public final class Validation {

	private Validation() {}	
	
	public static boolean hasAnyNumber(String input) {
	    return input.matches(".*\\d.*");
	}

}
