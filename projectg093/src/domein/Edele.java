package domein;

import enums.EdelsteenType;
import exceptions.IllegalArguments;
import utils.AantallenPerKleur;

/**
 * 
 * Deze klasse representeert een Edele in het spel Splendor en bevat informatie
 * over de prijs van de edele en het aantal prestige punten dat deze oplevert.
 * 
 * @author Arthur
 */
public class Edele {

	private static final int prestigePunten = 3;
	private final AantallenPerKleur aantalBonusPrijs;

	/**
	 * Constructor voor Edele
	 *
	 * @param aantalGroen Het aantal groene edelstenen die de edelstenen kost
	 * @param aantalWit   Het aantal witte edelstenen die de edelstenen kost
	 * @param aantalBlauw Het aantal blauwe edelstenen die de edelstenen kost
	 * @param aantalZwart Het aantal zwarte edelstenen die de edelstenen kost
	 * @param aantalRood  Het aantal rode edelstenen die de edelstenen kost
	 * @throws IllegalArguments als het aantal van een kleur negatief is
	 */
	public Edele(int aantalGroen, int aantalWit, int aantalBlauw, int aantalZwart, int aantalRood) {
		aantalBonusPrijs = new AantallenPerKleur();
		setAantalGroen(aantalGroen);
		setAantalWit(aantalWit);
		setAantalBlauw(aantalBlauw);
		setAantalZwart(aantalZwart);
		setAantalRood(aantalRood);
	}

	/**
	 * Geeft het aantal prestige punten van de edelstenen
	 *
	 * @return Het aantal prestige punten van de edelstenen
	 */
	public static int getPrestigePunten() {
		return prestigePunten;
	}

	/**
	 * Stelt het aantal groene edelstenen van de prijs van de edelstenen in
	 *
	 * @param aantalGroen Het aantal groene edelstenen van de prijs van de
	 *                    edelstenen
	 * @throws IllegalArguments als het aantal van de kleur negatief is
	 */
	public void setAantalGroen(int aantalGroen) {
		controleerAantalKleur(aantalGroen);
		aantalBonusPrijs.setAantal(EdelsteenType.GROEN, aantalGroen);
	}

	/**
	 * Geeft het aantal witte edelstenen van de prijs van de edele
	 *
	 * @return Het aantal witte edelstenen van de prijs van de edele
	 */
	public int getAantalWit() {
		return aantalBonusPrijs.getAantal(EdelsteenType.WIT);
	}

	/**
	 * Geeft het aantal groene edelstenen van de prijs van de edelstenen
	 *
	 * @return Het aantal groene edelstenen van de prijs van de edelstenen
	 */
	public int getAantalGroen() {
		return aantalBonusPrijs.getAantal(EdelsteenType.GROEN);
	}

	/**
	 * Stelt het aantal witte edelstenen van de prijs van de edele in
	 *
	 * @param aantalWit Het aantal witte edelstenen van de prijs van de edele
	 * 
	 * @throws IllegalArguments Indien een negatief aantal edelstenen wordt
	 *                          meegegeven
	 */
	public void setAantalWit(int aantalWit) {
		controleerAantalKleur(aantalWit);
		aantalBonusPrijs.setAantal(EdelsteenType.WIT, aantalWit);
	}

	/**
	 * 
	 * Geeft het aantal blauwe edelstenen van de prijs van de edelstenen terug.
	 * 
	 * @return Het aantal blauwe edelstenen van de prijs van de edelstenen.
	 */
	public int getAantalBlauw() {
		return aantalBonusPrijs.getAantal(EdelsteenType.BLAUW);
	}

	/**
	 * 
	 * Stelt het aantal blauwe edelstenen van de prijs van de edelstenen in.
	 * 
	 * @param aantalBlauw Het aantal blauwe edelstenen van de prijs van de
	 *                    edelstenen.
	 */
	public void setAantalBlauw(int aantalBlauw) {
		controleerAantalKleur(aantalBlauw);
		aantalBonusPrijs.setAantal(EdelsteenType.BLAUW, aantalBlauw);
	}

	/**
	 * 
	 * Geeft het aantal zwarte edelstenen van de prijs van de edelstenen terug.
	 * 
	 * @return Het aantal zwarte edelstenen van de prijs van de edelstenen.
	 */
	public int getAantalZwart() {
		return aantalBonusPrijs.getAantal(EdelsteenType.ZWART);
	}

	/**
	 * 
	 * Stelt het aantal zwarte edelstenen van de prijs van de edelstenen in.
	 * 
	 * @param aantalZwart Het aantal zwarte edelstenen van de prijs van de
	 *                    edelstenen.
	 */
	public void setAantalZwart(int aantalZwart) {
		controleerAantalKleur(aantalZwart);
		aantalBonusPrijs.setAantal(EdelsteenType.ZWART, aantalZwart);
	}

	/**
	 * 
	 * Geeft het aantal rode edelstenen van de prijs van de edelstenen terug.
	 * 
	 * @return Het aantal rode edelstenen van de prijs van de edelstenen.
	 */
	public int getAantalRood() {
		return aantalBonusPrijs.getAantal(EdelsteenType.ROOD);
	}

	/**
	 * 
	 * Stelt het aantal rode edelstenen van de prijs van de edelstenen in.
	 * 
	 * @param aantalRood Het aantal rode edelstenen van de prijs van de edelstenen.
	 */
	public void setAantalRood(int aantalRood) {
		controleerAantalKleur(aantalRood);
		aantalBonusPrijs.setAantal(EdelsteenType.ROOD, aantalRood);
	}

	/**
	 * 
	 * Controleert of het aantal edelstenen van een bepaalde kleur positief is.
	 * 
	 * @param aantalKleur Het aantal edelstenen van een bepaalde kleur.
	 * @throws IllegalArguments als het aantalKleur negatief is of groter dan 7.
	 */
	private void controleerAantalKleur(int aantalKleur) {
		if (aantalKleur < 0 || aantalKleur > 7) {
			throw new IllegalArguments("aantalKleur moet een positief geheel getal zijn, niet groter dan 6.");
		}
	}

	/**
	 * 
	 * Geeft de aantallen van bonus edelstenen per kleur van de prijs van de
	 * edelstenen terug.
	 * 
	 * @return AantallenPerKleur object met de aantallen van bonus edelstenen per
	 *         kleur van de prijs van de edelstenen.
	 */
	public AantallenPerKleur getAantalBonusPrijs() {
		return aantalBonusPrijs;
	}
}