package exceptions;

public class IllegalArguments extends IllegalArgumentException {

	public IllegalArguments() {
		super("Illegaal Argument");
	}

	public IllegalArguments(String s) {
		super(String.format("%s", s));

	}

}
