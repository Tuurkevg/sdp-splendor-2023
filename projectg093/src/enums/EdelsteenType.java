package enums;

public enum EdelsteenType {
	WIT, ROOD, BLAUW, GROEN, ZWART;

	public String getKleurCode() {
		return switch (this) {
			case WIT -> "#FFFFFF";
			case ROOD -> "#FF0000";
			case BLAUW -> "#000AFF";
			case GROEN -> "#10F319";
			case ZWART -> "#000000";
		};
	}
}