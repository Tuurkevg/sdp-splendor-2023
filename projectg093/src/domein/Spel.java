package domein;

import enums.EdelsteenType;
import exceptions.IllegalArguments;
import javafx.util.Pair;
import utils.AantallenPerKleur;
import utils.KaartenMapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 
 * De klasse Spel bevat alle informatie en methodes om een spel van Splendor te
 * spelen.
 * 
 * @author Arthur
 */
public class Spel {

	private final AantallenPerKleur edelsteenFiches;
	private final List<Speler> spelers;
	private final List<Edele> edelen;
	private final List<Ontwikkelingskaart> ontwikkelingskaarten;
	private final GedekteStapel[] gedekteStapels;
	private final List<Speler> winnaars;
	private int startSpeler;
	private int spelerAanBeurt;
	private boolean gestart;
	private boolean actieGebeurt;

	/**
	 * Constructor voor Spel
	 */
	public Spel() {
		edelsteenFiches = new AantallenPerKleur();
		spelers = new ArrayList<>();
		edelen = new ArrayList<>(KaartenMapper.initialiseerEdelen().subList(0, 5));
		ontwikkelingskaarten = new ArrayList<>();
		winnaars = new ArrayList<>();
		gedekteStapels = KaartenMapper.initialiseerGedekteStapels();
		gestart = false;
	}

	/**
	 * Voegt een speler toe aan het spel.
	 *
	 * @param speler de speler die wordt toegevoegd.
	 * @throws IllegalArguments indien het maximum aantal spelers reeds is bereikt.
	 */
	public void voegSpelerToe(Speler speler) {
		if (spelers.size() == 4) {
			throw new IllegalArguments("Maximum aantal spelers bereikt");
		}
		spelers.add(speler);
	}

	/**
	 * Vult de edelsteenfiches van het spel op met fiches van de juiste kleuren en
	 * aantallen. Dit gebeurt pas nadat de spelers zijn opgevuld.
	 *
	 * @throws UnsupportedOperationException het spel is nog niet gestart.
	 */
	public void vulEdelsteenFiches() {
		if (!gestart)
			throw new UnsupportedOperationException("Het spel is nog niet gestart.");
		edelsteenFiches.clear();
		final int max = spelers.size() > 2 ? spelers.size() * 2 - 1 : 4;

		// add foreach for each EdelsteenType
		for (EdelsteenType type : EdelsteenType.values()) {
			edelsteenFiches.setAantal(type, max);
		}

	}

	/**
	 * Start het spel door de fiches te vullen, de ontwikkelingskaarten op te
	 * vullen, de spelers te sorteren op geboortejaar en het spel als gestart te
	 * markeren.
	 *
	 * @throws UnsupportedOperationException indien er minder dan 2 spelers zijn.
	 */
	public void startSpel() {
		if (spelers.size() < 2)
			throw new UnsupportedOperationException("Er moeten minstens 2 spelers zijn.");
		startSpeler = spelers
				.indexOf(spelers.stream().sorted(Comparator.comparingInt(Speler::getGeboortejaar).reversed()).toList().get(0));
		spelerAanBeurt = startSpeler;
		gestart = true;
		vulEdelsteenFiches();
		vulOntwikkelingskaarten();
	}

	/**
	 *
	 * Vult de ontwikkelingskaarten aan door voor elke gedekte stapel vier kaarten
	 * te nemen.
	 */
	private void vulOntwikkelingskaarten() {
		for (GedekteStapel stapel : gedekteStapels) {
			ontwikkelingskaarten.addAll(IntStream.range(0, 4).mapToObj(i -> stapel.neemOntwikkelingskaart()).toList());
		}
	}

	/**
	 *
	 * Bepaalt of het spel afgelopen is door te kijken of er winnaars zijn.
	 *
	 * @throws IllegalArgumentException als het spel nog niet gestart is.
	 * @return true als er winnaars zijn, anders false.
	 */
	public boolean bepaalEindeSpel() {
		if (!gestart)
			throw new IllegalArguments("Spel is nog niet gestart.");
		return !getWinnaars().isEmpty();
	}

	/**
	 *
	 * Verhoogt de spelerAanBeurt met 1 mod het aantal spelers, zodat de volgende
	 * speler aan beurt komt.
	 *
	 * @throws IllegalArgumentException als het spel nog niet gestart is.
	 * @throws IllegalArgumentException als de speler meer dan 10 fiches heeft.
	 */
	public void volgendeSpeler() {
		if (!gestart)
			throw new IllegalArguments("Spel is nog niet gestart.");
		if (spelers.get(spelerAanBeurt).getEdelsteenfiches().geefSomAlleEdelsteenfiches() > 10)
			throw new IllegalArguments("Speler heeft meer dan 10 fiches.");
		spelerAanBeurt = (spelerAanBeurt + 1) % spelers.size();
		actieGebeurt = false;
	}

	/**
	 *
	 * Geeft de edelen terug die de speler kan kopen op basis van de
	 * ontwikkelingskaarten die hij bezit.
	 *
	 * @throws IllegalArgumentException als het spel nog niet gestart is.
	 * @return een lijst van edelen die de speler kan kopen.
	 */
	public List<Edele> geefKoopbareEdelen() {
		if (!gestart)
			throw new IllegalArguments("Spel is nog niet gestart.");
		Speler tempSpeler = geefSpelerAanBeurt();
		AantallenPerKleur totaalBonussenSpeler = AantallenPerKleur.bonussenToAantallenPerKleur(
				tempSpeler.getOntwikkelingskaarten().stream().map(Ontwikkelingskaart::getBonus).toList());
		return edelen.stream().filter(x -> totaalBonussenSpeler.kanBetalen(x.getAantalBonusPrijs())).toList();
	}

	/**
	 *
	 * Geeft de ontwikkelingskaarten terug die de speler kan kopen op basis van de
	 * edelsteenfiches en bonussen die hij bezit.
	 *
	 * @throws IllegalArgumentException als het spel nog niet gestart is.
	 * @return een lijst van ontwikkelingskaarten die de speler kan kopen.
	 */
	public List<Ontwikkelingskaart> geefKoopbareOntwikkelingskaarten() {
		if (!gestart)
			throw new IllegalArguments("Spel is nog niet gestart.");
		Speler tempSpeler = geefSpelerAanBeurt();
		AantallenPerKleur totaalEdelFichesEnBonussenSpeler = tempSpeler.geefTotaalEdelFichesBonussen();
		return ontwikkelingskaarten.stream()
				.filter(x -> totaalEdelFichesEnBonussenSpeler.kanBetalen(x.getAantalFiches())).toList();

	}

	/**
	 *
	 * Geeft de winnaars terug, als er spelers zijn met 15 of meer prestige punten.
	 *
	 * @throws IllegalArgumentException als het spel nog niet gestart is.
	 * @return een lijst van winnaars, anders een lege lijst.
	 */
	public List<Speler> getWinnaars() {
		if (!gestart)
			throw new IllegalArguments("Spel is nog niet gestart.");
		winnaars.clear();
		for (Speler sp : spelers) {
			if (sp.bepaalPrestigePunten() >= 15) {
				winnaars.add(sp);
			}
		}
		return winnaars;
	}

	/**
	 *
	 * Neemt edelsteenfiches van de algemene voorraad en voegt ze toe aan de
	 * edelsteenfiches van de speler aan beurt.
	 *
	 * @param ak de aantallen edelsteenfiches per kleur die worden afgetrokken van
	 *           de algemene voorraad en toegevoegd aan de speler aan beurt.
	 * @throws IllegalArguments als het spel nog niet gestart is.
	 * @throws IllegalArguments als de speler al een actie heeft gedaan.
	 */
	public void neemEdelsteenfiches(AantallenPerKleur ak) {
		if (!gestart)
			throw new IllegalArguments("Spel is nog niet gestart.");
		if (actieGebeurt)
			throw new IllegalArguments("Er is al een actie gebeurt.");
		if (ak.size() == 1) {
			if (ak.get(0).getValue() > 2)
				throw new IllegalArgumentException("Invalid argument: " + ak);
		} else if (ak.size() <= 3) {
			for (Pair<EdelsteenType, Integer> pair : ak) {
				if (pair.getValue() > 1) {
					throw new IllegalArgumentException("Invalid argument: " + ak);
				}
			}
		} else {
			throw new IllegalArgumentException("Invalid argument: " + ak);
		}

		edelsteenFiches.trekAantallenPerKleurAf(ak);
		geefSpelerAanBeurt().voegEdelsteenfichesToe(ak);
		if (geefSpelerAanBeurt().getEdelsteenfiches().geefSomAlleEdelsteenfiches() > 10) {
			throw new IllegalArguments("Je mag niet meer dan 10 edelsteenfiches in je inventaris hebben");
		}
		actieGebeurt = true;
	}

	/**
	 *
	 * Koopt een ontwikkelingskaart van de gedekte stapels en voegt deze toe aan de
	 * kaarten van de speler aan beurt.
	 *
	 * @param ok de index van de ontwikkelingskaart die wordt gekocht.
	 * @throws IllegalArguments als het spel nog niet is gestart, of als de speler
	 *                          niet genoeg edelsteenfiches en/of bonussen heeft om
	 *                          de kaart te kopen.
	 */
	public void koopOntwikkelingskaart(int ok) {
		if (!gestart)
			throw new IllegalArguments("Spel is nog niet gestart.");
		if (actieGebeurt)
			throw new IllegalArguments("Er is al een actie gebeurt.");

		if (geefSpelerAanBeurt().geefTotaalEdelFichesBonussen()
				.kanBetalen(ontwikkelingskaarten.get(ok).getAantalFiches())) {
			if (!gedekteStapels[ontwikkelingskaarten.get(ok).getNiveau() - 1].getGedekteKaarten().isEmpty()) {
				ontwikkelingskaarten
						.add(gedekteStapels[ontwikkelingskaarten.get(ok).getNiveau() - 1].neemOntwikkelingskaart());
			}
			AantallenPerKleur terugInHetSpelTeSteken = geefSpelerAanBeurt()
					.voegOntwikkelingsKaartToe(ontwikkelingskaarten.remove(ok));
			edelsteenFiches.voegAantallenPerKleurSamen(terugInHetSpelTeSteken);
		} else {
			throw new IllegalArguments(
					"De speler heeft niet genoeg edelsteenFiches en/of bonussen om de kaart te kopen");
		}
		actieGebeurt = true;
	}

	/**
	 *
	 * Verplaatst edelsteenfiches van de speler aan beurt van het terug te geven
	 * vakje naar het algemene voorraadvakje.
	 *
	 * @param ak de aantallen edelsteenfiches per kleur die worden teruggegeven van
	 *           de speler aan beurt naar de algemene voorraad.
	 */
	public void verplaatsTerugTeGevenEdelsteenfiches(AantallenPerKleur ak) {
		geefSpelerAanBeurt().verplaatsTerugTeGevenEdelsteenfiches(ak);
		edelsteenFiches.voegAantallenPerKleurSamen(ak);
	}

	/**
	 *
	 * Geeft een lijst met alle spelers van het spel.
	 *
	 * @return een lijst met alle spelers van het spel.
	 */
	public List<Speler> getSpelers() {
		return spelers;
	}

	/**
	 *
	 * Geeft de startspeler van het spel.
	 *
	 * @return de startspeler van het spel.
	 */
	public Speler geefStartSpeler() {
		return spelers.get(startSpeler);
	}

	/**
	 *
	 * Geeft de speler die op dit moment aan de beurt is.
	 *
	 * @throws IllegalArgumentException als het spel nog niet gestart is.
	 * @return de speler die op dit moment aan de beurt is.
	 */
	public Speler geefSpelerAanBeurt() {
		if (!gestart)
			throw new IllegalArguments("Spel is nog niet gestart.");
		return spelers.get(spelerAanBeurt);
	}

	/**
	 *
	 * Geeft een lijst met alle edelen van het spel.
	 *
	 * @return een lijst met alle edelen van het spel.
	 */
	public List<Edele> getEdelen() {
		return edelen;
	}

	/**
	 *
	 * Geeft een lijst met alle ontwikkelingskaarten van het spel.
	 *
	 * @return een lijst met alle ontwikkelingskaarten van het spel.
	 */
	public List<Ontwikkelingskaart> getOntwikkelingskaarten() {
		return ontwikkelingskaarten;
	}

	/**
	 *
	 * Geeft een array met de gedekte stapels van het spel.
	 *
	 * @return een array met de gedekte stapels van het spel.
	 */
	public GedekteStapel[] getGedekteStapels() {
		return gedekteStapels;
	}

	/**
	 *
	 * Geeft de aantallen van de beschikbare edelsteen fiches in het spel.
	 *
	 * @return de aantallen van de beschikbare edelsteen fiches in het spel.
	 */
	public AantallenPerKleur getEdelsteenFiches() {
		return edelsteenFiches;
	}

	/**
	 *
	 * Koopt een edele voor de speler die op dit moment aan de beurt is.
	 *
	 * @throws IllegalArgumentException als het spel nog niet gestart is.
	 * @param edeleNummer het nummer van de edele die gekocht wordt.
	 */
	public void koopEdele(int edeleNummer) {
		if (!gestart)
			throw new IllegalArguments("Spel is nog niet gestart.");
		if (actieGebeurt)
			throw new IllegalArguments("Er is al een actie gebeurt.");
		Edele edele = this.geefKoopbareEdelen().get(edeleNummer);
		this.edelen.remove(edele);
		this.geefSpelerAanBeurt().voegEdeleToe(edele);
		actieGebeurt = true;
	}

	/**
	 *
	 * Geeft de aantallen van de edelsteen fiches van de speler die op dit moment
	 * aan de beurt is.
	 *
	 * @return de aantallen van de edelsteen fiches van de speler die op dit moment
	 *         aan de beurt is.
	 */
	public AantallenPerKleur geefSpelerAanBeurtEdelsteenFiches() {
		return geefSpelerAanBeurt().getEdelsteenfiches();
	}

	/**
	 * Voert een cheatactie uit in het spel door ontwikkelingskaarten aan de speler
	 * aan de beurt toe te voegen. Voegt 3 nieuwe ontwikkelingskaarten van het type
	 * 'EdelsteenType.GROEN' toe aan de ontwikkelingskaarten van de speler.
	 */
	public void Cheat() {
		for (int i = 0; i < 3; i++) {
			geefSpelerAanBeurt().getOntwikkelingskaarten()
					.add(new Ontwikkelingskaart(1, EdelsteenType.GROEN, 5, 1, 0, 0, 0, 0));
		}
	}

}