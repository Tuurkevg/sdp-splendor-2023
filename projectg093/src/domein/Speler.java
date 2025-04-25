package domein;

import exceptions.IllegalArguments;
import utils.AantallenPerKleur;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * De klasse Speler stelt een speler voor van het spel Splendor. Een speler
 * heeft een gebruikersnaam, een geboortejaar, een lijst van edelen, een lijst
 * van ontwikkelingskaarten en een aantal edelsteenfiches in een
 * AantallenPerKleur. Er kunnen edelen en ontwikkelingskaarten aan toegevoegd
 * worden en er kunnen prestige-punten bepaald worden.
 * 
 * @author Arthur
 */
public class Speler {

	private final String gebruikersnaam;
	private final int geboortejaar;
	private final List<Edele> edelen;
	private final List<Ontwikkelingskaart> ontwikkelingskaarten;
	private final AantallenPerKleur edelsteenfiches;

	/**
	 * Constructor voor Speler
	 * 
	 * @param gebruikersnaam De gebruikersnaam van de speler
	 * @param geboortejaar   Het geboortejaar van de speler
	 * @throws IllegalArguments als de gebruikersnaam of het geboortejaar ongeldig
	 *                          is
	 */
	public Speler(String gebruikersnaam, int geboortejaar) {
		controleerGeboortejaar(geboortejaar);
		controleerGebruikersnaam(gebruikersnaam);
		edelen = new ArrayList<>();
		ontwikkelingskaarten = new ArrayList<>();
		edelsteenfiches = new AantallenPerKleur();
		this.gebruikersnaam = gebruikersnaam;
		this.geboortejaar = geboortejaar;
	}

	/**
	 * Geeft de gebruikersnaam van de speler
	 * 
	 * @return De gebruikersnaam van de speler
	 */
	public String getGebruikersnaam() {
		return this.gebruikersnaam;
	}

	/**
	 * Stelt de gebruikersnaam van de speler in
	 * 
	 * @param gebruikersnaam De gebruikersnaam van de speler
	 * @throws IllegalArguments als de gebruikersnaam ongeldig is
	 */
	public void controleerGebruikersnaam(String gebruikersnaam) {
		// !gebruikersnaam.matches("^[a-zA-Z][\\w\\s]*$") geeft true als de
		// gebruikersnaam er een speciaal karakter wordt gebruikt die geen spatie of
		// underscore is
		// als de eerste letter geen letter is (mag lowercase of uppercase zijn)
		// `^`: komt overeen met het begin van de string
		// `[a-zA-Z]`: komt overeen met een letter (lowercase of uppercase)
		// `[\\w\\s]*`: komt overeen met nul of meer woordtekens (letters, cijfers en
		// onderstrepingstekens) en spaties
		// `$`: komt overeen met het einde van de string

		if (gebruikersnaam == null || !gebruikersnaam.matches("^[a-zA-Z][\\w\\s]*$")) {
			throw new IllegalArguments(
					"Ongeldige Gebruikersnaam (mag enkel letters, cijfers, spaties en underscores bevatten)");
		}
	}

	/**
	 * Geeft het geboortejaar van de speler
	 * 
	 * @return Het geboortejaar van de speler
	 */
	public int getGeboortejaar() {
		return this.geboortejaar;
	}

	/**
	 * Stelt het geboortejaar van de speler in
	 * 
	 * @param geboortejaar Het geboortejaar van de speler
	 * @throws IllegalArguments als het geboortejaar ongeldig is
	 */
	public void controleerGeboortejaar(int geboortejaar) {
		if (geboortejaar < 1900) {
			throw new IllegalArguments("Ongeldig Geboortejaar");
		} else if (geboortejaar > LocalDate.now().getYear() - 6) {
			throw new IllegalArguments("Speler moet minstens 6 jaar oud zijn");
		}
	}

	/**
	 * 
	 * Bepaalt het aantal prestige-punten dat de speler heeft
	 * 
	 * @return Het aantal prestige-punten dat de speler heeft
	 */
	public int bepaalPrestigePunten() {
		int prestigePunten = 0;
		prestigePunten += edelen.size() * Edele.getPrestigePunten();
		for (Ontwikkelingskaart ontwikkelingskaart : ontwikkelingskaarten) {
			prestigePunten += ontwikkelingskaart.getPrestigePunten();
		}
		return prestigePunten;
	}

	/**
	 * 
	 * Geeft de AantallenPerKleur van de edelsteenfiches van de speler.
	 * 
	 * @return De AantallenPerKleur van de edelsteenfiches van de speler
	 */
	public AantallenPerKleur getEdelsteenfiches() {
		return edelsteenfiches;
	}

	/**
	 * 
	 * Geeft de lijst van edelen van de speler.
	 * 
	 * @return De lijst van edelen van de speler
	 */
	public List<Edele> getEdelen() {
		return edelen;
	}

	/**
	 * 
	 * Voegt de gegeven AantallenPerKleur aan de edelsteenfiches van de speler toe.
	 * 
	 * @param ak De AantallenPerKleur die aan de edelsteenfiches van de speler moet
	 *           worden toegevoegd
	 * @throws IllegalArguments als er geen kleur en aantal is gekozen om toe te
	 *                          voegen
	 */
	public void voegEdelsteenfichesToe(AantallenPerKleur ak) {
		if (ak == null) {
			throw new IllegalArguments(
					"Er moet een kleur en een aantal gekozen worden voor Edelsteenfiches toe te voegen");
		}
		edelsteenfiches.voegAantallenPerKleurSamen(ak);

	}

	/**
	 * 
	 * Voegt de gegeven Ontwikkelingskaart aan de ontwikkelingskaarten van de speler
	 * toe en trekt de edelsteenfiches af.
	 * 
	 * @param ok De Ontwikkelingskaart die aan de ontwikkelingskaarten van de speler
	 *           moet worden toegevoegd
	 * @return De AantallenPerKleur van de edelsteenfiches die van de speler moeten
	 *         worden afgetrokken
	 */
	public AantallenPerKleur voegOntwikkelingsKaartToe(Ontwikkelingskaart ok) {
		new AantallenPerKleur();
		AantallenPerKleur ak = AantallenPerKleur.trekAantallenPerKleurAfSafe(ok.getAantalFiches(), geefBonussen());
		edelsteenfiches.trekAantallenPerKleurAf(ak);
		ontwikkelingskaarten.add(ok);
		return ak;
	}

	/**
	 * 
	 * Verplaatst de edelsteenfiches die teruggegeven moeten worden naar de
	 * voorraad.
	 * 
	 * @param ak AantallenPerKleur object dat de aantallen van de fiches bevat die
	 *           teruggegeven moeten worden
	 * @throws IllegalArguments indien er niets meegegeven is om terug te geven
	 */
	public void verplaatsTerugTeGevenEdelsteenfiches(AantallenPerKleur ak) {
		if (edelsteenfiches.size() < 1) {
			throw new IllegalArguments("Er is niets meegegeven om terug te geven");
		}
		edelsteenfiches.trekAantallenPerKleurAf(ak);
	}

	/**
	 * 
	 * Voegt een nieuwe Edele toe aan de lijst van edelen van de speler.
	 * 
	 * @param edele de Edele die toegevoegd moet worden
	 * @throws NullPointerException indien de Edele parameter null is
	 */
	public void voegEdeleToe(Edele edele) {
		if (edele == null) {
			throw new NullPointerException("edele moet ingevuld zijn");
		}
		edelen.add(edele);
	}

	/**
	 * 
	 * Geeft een lijst terug van alle ontwikkelingskaarten van de speler.
	 * 
	 * @return een List object met alle ontwikkelingskaarten van de speler
	 */
	public List<Ontwikkelingskaart> getOntwikkelingskaarten() {
		return ontwikkelingskaarten;
	}

	/**
	 * 
	 * Berekent de bonussen van de ontwikkelingskaarten van de speler.
	 * 
	 * @return een AantallenPerKleur object dat de bonussen van de
	 *         ontwikkelingskaarten van de speler bevat
	 */
	public AantallenPerKleur geefBonussen() {
		AantallenPerKleur bonussen = new AantallenPerKleur();
		for (Ontwikkelingskaart ok : ontwikkelingskaarten) {
			bonussen.verhoogAantal(ok.getBonus(), 1);
		}
		return bonussen;

	}

	/**
	 * 
	 * Berekent de totale hoeveelheid edelsteenfiches van de speler, inclusief de
	 * bonussen van de ontwikkelingskaarten.
	 * 
	 * @return een AantallenPerKleur object dat de totale hoeveelheid
	 *         edelsteenfiches van de speler bevat, inclusief bonussen
	 */
	public AantallenPerKleur geefTotaalEdelFichesBonussen() {
		return AantallenPerKleur.voegAantallenPerKleurSamen(edelsteenfiches, geefBonussen());
	}

}