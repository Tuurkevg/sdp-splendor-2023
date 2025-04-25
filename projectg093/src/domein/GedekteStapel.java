package domein;

import java.util.Stack;

import exceptions.IllegalArguments;

/**
 * 
 * Deze klasse representeert een stapel gedekte ontwikkelingskaarten van een
 * bepaald niveau.
 * 
 * @author Arthur
 */
public class GedekteStapel {

	private final Stack<Ontwikkelingskaart> gedekteKaarten;
	private final int niveau;

	/**
	 * Constructor voor GedekteStapel.
	 * 
	 * @param gedekteKaarten De gedekte kaarten van de stapel.
	 * @param niveau         Het niveau van de stapel.
	 * @throws IllegalArguments Indien de parameters niet voldoen aan de
	 *                          validatievoorwaarden.
	 */
	public GedekteStapel(Stack<Ontwikkelingskaart> gedekteKaarten, int niveau) {

		controleerNiveau(niveau);
		controleerGedekteKaarten(gedekteKaarten, niveau);
		this.gedekteKaarten = gedekteKaarten;
		this.niveau = niveau;

	}

	/**
	 * Geeft het niveau van de stapel terug.
	 * 
	 * @return Het niveau van de stapel.
	 */
	public int getNiveau() {
		return this.niveau;
	}

	/**
	 * Stelt het niveau van de stapel in.
	 * 
	 * @param niveau Het niveau van de stapel.
	 * @throws IllegalArguments Indien het niveau niet binnen het interval [1,3]
	 *                          ligt.
	 */
	private void controleerNiveau(int niveau) {
		if (niveau < 1 || niveau > 3) {
			throw new IllegalArguments("Het niveau moet in het interval [0, 4] liggen.");
		}
	}

	/**
	 * Controleert of de gedekte kaarten voldoen aan de validatievoorwaarden.
	 * 
	 * @param gedekteKaarten De gedekte kaarten van de stapel.
	 * @param niveau         Het niveau van de stapel.
	 * @throws IllegalArguments Indien de gedekte kaarten niet voldoen aan de
	 *                          validatievoorwaarden.
	 */
	private void controleerGedekteKaarten(Stack<Ontwikkelingskaart> gedekteKaarten, int niveau) {
		// fix parameters
		if (gedekteKaarten == null) {
			throw new IllegalArguments("De gedekte kaarten werd niet correct ingesteld");
		}
		if (!gedekteKaarten.stream().allMatch(x -> x.getNiveau() == niveau)) {
			throw new IllegalArguments("De kaart en de stapel is niet de juiste in combinatie");
		}
		switch (niveau) {
		case 1 -> {
			if (gedekteKaarten.size() != 40)
				throw new IllegalArguments("De grootte van de gedekteKaarten is maximum 40 voor niveau 1");
		}
		case 2 -> {
			if (gedekteKaarten.size() != 30)

				throw new IllegalArguments("De grootte van de gedekteKaarten is maximum 30 voor niveau 2");

		}
		default -> {
			if (gedekteKaarten.size() != 20) {
				throw new IllegalArguments("De grootte van de gedekteKaarten is maximum 20 voor niveau 3");
			}
		}
		}

	}

	/**
	 * Neemt de bovenste ontwikkelingskaart van de stapel en verwijdert deze van de
	 * stapel.
	 * 
	 * @return De bovenste ontwikkelingskaart van de stapel.
	 */
	public Ontwikkelingskaart neemOntwikkelingskaart() {

		return gedekteKaarten.remove(0);

	}

	/**
	 * 
	 * de gedekte kaarten van de stapel terug te geven
	 * 
	 * @return De gedekte kaarten van de stapel
	 */
	public Stack<Ontwikkelingskaart> getGedekteKaarten() {
		return gedekteKaarten;
	}
}