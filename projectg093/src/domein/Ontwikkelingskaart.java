package domein;

import enums.EdelsteenType;
import exceptions.IllegalArguments;
import utils.AantallenPerKleur;

/**
 * 
 * De Ontwikkelingskaart klasse representeert een ontwikkelingskaart uit het
 * spel Splendor.
 * 
 * @author Arthur
 */
public class Ontwikkelingskaart {

	private final int niveau;
	private final EdelsteenType bonus;
	private final int prestigePunten;
	private final AantallenPerKleur aantalFiches;

	/**
	 * De constructor van de klasse Ontwikkelingskaart.
	 *
	 * @param niveau         Het niveau van de ontwikkelingskaart tussen 0 en 3.
	 * @param bonus          De bonus in de vorm van een EdelsteenType die deze
	 *                       kaart geeft.
	 * @param prestigePunten Het aantal prestigepunten dat deze kaart geeft.
	 * @param aantalGroen    Het aantal groene fiches/bonussen dat nodig is om de
	 *                       kaart te kopen.
	 * @param aantalWit      Het aantal witte fiches/bonussen dat nodig is om de
	 *                       kaart te kopen.
	 * @param aantalBlauw    Het aantal blauwe fiches/bonussen dat nodig is om de
	 *                       kaart te kopen.
	 * @param aantalZwart    Het aantal zwarte fiches/bonussen dat nodig is om de
	 *                       kaart te kopen.
	 * @param aantalRood     Het aantal rode fiches/bonussen dat nodig is om de
	 *                       kaart te kopen.
	 * 
	 * @throws IllegalArguments Indien het niveau buiten [1, 3] ligt.
	 * @throws IllegalArguments Indien de gegeven bonus ongeldig is.
	 * @throws IllegalArguments Indien het aantal prestigepunten buiten [0, 5] ligt.
	 */
	public Ontwikkelingskaart(int niveau, EdelsteenType bonus, int prestigePunten, int aantalGroen, int aantalWit,
			int aantalBlauw, int aantalZwart, int aantalRood) {
		// Alle setters oproepen die nodig zijn.
		aantalFiches = aantalToFiches(aantalGroen, aantalWit, aantalBlauw, aantalZwart, aantalRood);
		controleerPrestigePunten(prestigePunten);
		controleerBonus(bonus);
		controleerNiveau(niveau);
		this.prestigePunten = prestigePunten;
		this.bonus = bonus;
		this.niveau = niveau;

	}

	/**
	 * Geeft het niveau van de kaart terug.
	 * 
	 * @return Het niveau van de kaart.
	 */
	public int getNiveau() {
		return this.niveau;
	}

	/**
	 * Setter van het aantal fiches per kleur.
	 * 
	 * @param aantalGroen Het aantal groene fiches.
	 * @param aantalWit   Het aantal witte fiches.
	 * @param aantalBlauw Het aantal blauwe fiches.
	 * @param aantalZwart Het aantal zwarte fiches.
	 * @param aantalRood  Het aantal rode fiches.
	 * @return AantallenPerKleur object dat het aantal fiches per kleur bijhoudt.
	 * @throws IllegalArgumentException wanneer het aantal fiches onder 0 zit.
	 */
	private AantallenPerKleur aantalToFiches(int aantalGroen, int aantalWit, int aantalBlauw, int aantalZwart,
			int aantalRood) {
		controleerAantalKleur(aantalGroen);
		controleerAantalKleur(aantalWit);
		controleerAantalKleur(aantalBlauw);
		controleerAantalKleur(aantalZwart);
		controleerAantalKleur(aantalRood);

		AantallenPerKleur fiches = new AantallenPerKleur();
		if (aantalGroen > 0) {
			fiches.setAantal(EdelsteenType.GROEN, aantalGroen);
		}
		if (aantalWit > 0) {
			fiches.setAantal(EdelsteenType.WIT, aantalWit);
		}
		if (aantalBlauw > 0) {
			fiches.setAantal(EdelsteenType.BLAUW, aantalBlauw);
		}
		if (aantalZwart > 0) {
			fiches.setAantal(EdelsteenType.ZWART, aantalZwart);
		}
		if (aantalRood > 0) {
			fiches.setAantal(EdelsteenType.ROOD, aantalRood);
		}
		return fiches;
	}

	/**
	 * Controleert of het niveau van de kaart binnen de toegestane range ligt.
	 * 
	 * @param niveau Het niveau van de kaart.
	 * @throws IllegalArgumentException wanneer het niveau niet binnen het
	 *                                  toegestane interval [1,3] ligt.
	 */
	public final void controleerNiveau(int niveau) {
		if (niveau < 1 || niveau > 3) {
			throw new IllegalArguments("Het niveau ligt niet in het interval [0, 4]");
		}
	}

	/**
	 * Geeft de bonus van de kaart terug.
	 * 
	 * @return De bonus die verloond wordt voor het hebben van deze kaart.
	 */
	public EdelsteenType getBonus() {
		return this.bonus;
	}

	/**
	 * Controleert of de bonus van de kaart geldig is.
	 * 
	 * @param bonus De bonus die verloond wordt voor het hebben van deze kaart.
	 * @throws IllegalArgumentException wanneer de bonus null is.
	 */
	private void controleerBonus(EdelsteenType bonus) {
		if (bonus == null) {
			throw new IllegalArguments("Ongeldige bonus");
		}
	}

	/**
	 * 
	 * Geeft het aantal prestige punten terug dat deze kaart oplevert.
	 * 
	 * @return Het aantal prestige punten dat deze kaart oplevert.
	 */
	public int getPrestigePunten() {
		return this.prestigePunten;
	}

	/**
	 * 
	 * Controleert of het opgegeven aantal prestige punten binnen het toegestane
	 * interval van 0 tot 5 ligt.
	 * 
	 * @param prestigePunten Het aantal prestige punten dat verloond wordt bij het
	 *                       hebben van deze kaart.
	 * @throws IllegalArgumentException Wanneer het opgegeven aantal prestige punten
	 *                                  niet binnen het toegestane interval van 0
	 *                                  tot 5 ligt.
	 */
	public void controleerPrestigePunten(int prestigePunten) {
		if (0 > prestigePunten || prestigePunten > 5) {
			throw new IllegalArguments("De prestigepunten moeten in het interval ]0;5[ ");
		}
	}

	/**
	 * 
	 * Geeft het aantal fiches van een specifieke kleur terug.
	 * 
	 * @param type De kleur van de fiches waarvan het aantal wordt opgevraagd.
	 * @return Het aantal fiches van de opgegeven kleur.
	 */
	public int getAantalKleur(EdelsteenType type) {
		// check if type is in aantalFiches
		if (aantalFiches.stream().noneMatch(pair -> pair.getKey() == type)) {
			return aantalFiches.stream().filter(pair -> pair.getKey() == type).findFirst().get().getValue();
		}
		return 0;
	}

	/**
	 * 
	 * Geeft het aantal groene fiches terug.
	 * 
	 * @return Het aantal groene fiches.
	 */
	public int getAantalGroen() {
		return getAantalKleur(EdelsteenType.GROEN);
	}

	/**
	 * 
	 * Geeft het aantal witte fiches terug.
	 * 
	 * @return Het aantal witte fiches.
	 */
	public int getAantalWit() {
		return getAantalKleur(EdelsteenType.WIT);
	}

	/**
	 * 
	 * Geeft het aantal blauwe fiches terug.
	 * 
	 * @return Het aantal blauwe fiches.
	 */
	public int getAantalBlauw() {
		return getAantalKleur(EdelsteenType.BLAUW);
	}

	/**
	 * 
	 * Geeft het aantal zwarte fiches terug.
	 * 
	 * @return Het aantal zwarte fiches.
	 */
	public int getAantalZwart() {
		return getAantalKleur(EdelsteenType.ZWART);
	}

	/**
	 * 
	 * Geeft het aantal rode fiches terug.
	 * 
	 * @return Het aantal rode fiches.
	 */
	public int getAantalRood() {
		return getAantalKleur(EdelsteenType.ROOD);
	}

	/**
	 * 
	 * Controleert of het aantal fiches van een specifieke kleur geldig is.
	 * 
	 * @param aantalKleur Het aantal fiches van een specifieke kleur.
	 * @throws IllegalArgumentException Wanneer het aantal fiches niet in het
	 *                                  interval [0;7] ligt.
	 */
	private void controleerAantalKleur(int aantalKleur) {
		if (aantalKleur < 0 || aantalKleur > 7) {
			throw new IllegalArguments("aantalKleur moet interval ]0;7[");
		}
	}

	/**
	 * 
	 * Geeft het aantal fiches van elke kleur terug in een object van het type
	 * AantallenPerKleur.
	 * 
	 * @return Een object van het type AantallenPerKleur dat het aantal fiches van
	 *         elke kleur bevat.
	 */
	public AantallenPerKleur getAantalFiches() {
		return aantalFiches;
	}
}