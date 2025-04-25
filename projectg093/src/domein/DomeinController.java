package domein;

import java.util.List;

import dto.EdeleDTO;
import dto.GedekteStapelDTO;
import dto.OntwikkelingskaartDTO;
import dto.OverzichtDTO;
import dto.SpelerAanBeurtFichesDTO;
import dto.SpelerDTO;
import exceptions.IllegalArguments;
import utils.AantallenPerKleur;

/**
 * 
 * Deze klasse is verantwoordelijk voor het besturen van het spel. Ze bevat
 * methoden om een speler aan te melden, een nieuw spel te starten, de lijst van
 * koopbare edelen terug te geven, een edele te kiezen, het huidige overzicht
 * van het spel terug te geven, de lijst van winnaars terug te geven, naar de
 * volgende speler te gaan, en edelsteenfiches van het spel te nemen. De klasse
 * maakt gebruik van objecten uit de SpelerRepository, Spel en de DTO klassen.
 * De klasse vangt IllegalArguments en UnsupportedOperationExceptions op en
 * werpt die opnieuw als IllegalArguments.
 * 
 * @author Arthur
 */
public class DomeinController {
	private Spel spel;
	private final SpelerRepository spelerRepo;

	/**
	 * Constructor voor DomeinController
	 */
	public DomeinController() {
		spelerRepo = new SpelerRepository();
		spel = new Spel();
	}

	/**
	 * Voegt een speler toe aan het spel
	 *
	 * @param gebruikersNaam De gebruikersnaam van de speler
	 * @param geboortejaar   Het geboortejaar van de speler
	 * @throws IllegalArguments indien er een fout is opgetreden.
	 */
	public void meldAan(String gebruikersNaam, int geboortejaar) {

		try {
			Speler speler = spelerRepo.geefSpeler(gebruikersNaam, geboortejaar);
			spel.voegSpelerToe(speler);
		} catch (Exception e) {
			throw new IllegalArguments(e.getMessage());
		}
	}

	/**
	 * Start een nieuw spel
	 */
	public void startSpel() {
		try {
			spel.startSpel();
		} catch (UnsupportedOperationException e) {
			throw new UnsupportedOperationException(e.getMessage());
		}
	}

	/**
	 * Geeft een lijst van alle koopbare edelen van het spel terug. Als er slechts 1
	 * koopbare edele is, wordt deze automatisch gekocht en gaat het spel verder.
	 *
	 * @return Een lijst van koopbare edelen.
	 * @throws IllegalArguments indien er een fout is opgetreden.
	 */
	public List<EdeleDTO> geefKoopbareEdelen() {
		List<EdeleDTO> koopbareEdelen;
		try {
			koopbareEdelen = EdeleDTO.EdelenToDTOS(spel.geefKoopbareEdelen());
			if (koopbareEdelen.size() == 1) {
				this.kiesEdele(0);
			}

		} catch (Exception e) {
			throw new IllegalArguments(e.getMessage());
		}
		return koopbareEdelen;
	}

	/**
	 * Kiest een edele.
	 *
	 * @param edeleNummer het nummer van de gekozen edele.
	 * @throws IllegalArguments indien het edele nummer niet geldig is.
	 */
	public void kiesEdele(int edeleNummer) {
		if (edeleNummer > spel.geefKoopbareEdelen().size() - 1) {
			throw new IllegalArguments("Ongeldig edele nummer");
		}
		try {
			spel.koopEdele(edeleNummer);
			spel.volgendeSpeler();
		} catch (Exception e) {
			throw new IllegalArguments(e.getMessage());
		}

	}

	/**
	 * Geeft een DTO van het huidige overzicht van het spel terug.
	 *
	 * @return Een DTO van het huidige overzicht van het spel.
	 * @throws IllegalArguments indien er een fout is opgetreden.
	 */
	public OverzichtDTO geefOverzicht() {
		if (spel == null)
			throw new IllegalArguments("Er is nog geen spel gestart");
		return new OverzichtDTO(EdeleDTO.EdelenToDTOS(spel.getEdelen()),
				OntwikkelingskaartDTO.OntwikkelingskaartenToDTOS(spel.getOntwikkelingskaarten()),
				GedekteStapelDTO.gedekteStapelsToDTOS(spel.getGedekteStapels()), spel.getEdelsteenFiches(),
				SpelerDTO.spelersToDTOS(spel.getSpelers(), spel.geefSpelerAanBeurt(), spel.geefStartSpeler()));
	}

	/**
	 * Geeft een lijst van DTOs van alle spelers die gewonnen hebben.
	 *
	 * @return Een lijst van DTOs van alle spelers die gewonnen hebben.
	 * @throws IllegalArguments indien er een fout is opgetreden.
	 */
	public List<SpelerDTO> geefWinnaars() {
		if (spel == null)
			throw new IllegalArguments("Er is nog geen spel gestart");
		return SpelerDTO.spelersToDTOS(spel.getWinnaars(), spel.geefSpelerAanBeurt(), spel.geefStartSpeler());
	}

	/**
	 * Gaat naar de volgende speler.
	 *
	 * @throws IllegalArgumentException indien de volgende speler niet geldig is.
	 */
	public void volgendeSpeler() {
		try {
			spel.volgendeSpeler();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	/**
	 * Neemt een aantal edelsteenfiches van elke kleur.
	 *
	 * @param ak een object dat de aantallen per kleur bijhoudt.
	 * @throws IllegalArguments indien het object ak niet geldig is.
	 */
	public void neemEdelsteenfiches(AantallenPerKleur ak) {
		try {
			spel.neemEdelsteenfiches(ak);
		} catch (Exception e) {
			throw new IllegalArguments(e.getMessage());
		}

	}

	/**
	 * Koopt een ontwikkelingskaart.
	 *
	 * @param ok het nummer van de gekozen ontwikkelingskaart.
	 * @throws IllegalArguments indien de ontwikkelingskaart niet gekocht kan
	 *                          worden.
	 */
	public void koopOntwikkelingskaart(int ok) {
		try {
			spel.koopOntwikkelingskaart(ok);
		} catch (Exception e) {
			throw new IllegalArguments(e.getMessage());
		}
	}

	/**
	 * Verplaatst de terug te geven edelsteenfiches.
	 *
	 * @param ak een object dat de aantallen per kleur bijhoudt.
	 * @throws IllegalArguments indien het object ak niet geldig is.
	 */
	public void verplaatsTerugTeGevenEdelsteenFiches(AantallenPerKleur ak) {
		try {
			spel.verplaatsTerugTeGevenEdelsteenfiches(ak);
		} catch (Exception e) {
			throw new IllegalArguments(e.getMessage());
		}
	}

	/**
	 * Vult de edelsteenfiches aan.
	 *
	 * @throws UnsupportedOperationException indien het vullen van de
	 *                                       edelsteenfiches niet mogelijk is.
	 */
	public void vulEdelsteenfichesAan() {
		try {
			spel.vulEdelsteenFiches();
		} catch (Exception e) {
			throw new UnsupportedOperationException(e.getMessage());
		}

	}

	/**
	 * Geeft een lijst van alle koopbare ontwikkelingskaarten van het spel terug.
	 *
	 * @return Een lijst van koopbare ontwikkelingskaarten.
	 * @throws IllegalArguments indien er een fout is opgetreden.
	 */
	public List<OntwikkelingskaartDTO> geefKoopbareOntwikkelingskaarten() {
		List<OntwikkelingskaartDTO> koopbareOntwikkelingskaarten;
		try {
			koopbareOntwikkelingskaarten = OntwikkelingskaartDTO
					.OntwikkelingskaartenToDTOS(spel.geefKoopbareOntwikkelingskaarten());

		} catch (Exception e) {
			throw new IllegalArguments(e.getMessage());
		}
		return koopbareOntwikkelingskaarten;

	}

	/**
	 *
	 * Geeft de DTO terug van de edelsteenfiches van de huidige speler aan beurt.
	 *
	 * @return de DTO van de edelsteenfiches van de huidige speler aan beurt.
	 */
	public SpelerAanBeurtFichesDTO geefSpelerAanBeurtEdelSteenFiches() {
		return new SpelerAanBeurtFichesDTO(spel.geefSpelerAanBeurtEdelsteenFiches());
	}

	/**
	 * Haalt een lijst van OntwikkelingskaartDTO-objecten op.
	 *
	 * @return Een lijst van OntwikkelingskaartDTO-objecten.
	 * @throws IllegalArguments als er een uitzondering optreedt bij het ophalen van
	 *                          de OntwikkelingskaartDTO-objecten.
	 */
	public List<OntwikkelingskaartDTO> geefOntwikkelingskaarten() {
		List<OntwikkelingskaartDTO> Ontwikkelingskaarten;
		try {
			Ontwikkelingskaarten = OntwikkelingskaartDTO.OntwikkelingskaartenToDTOS(spel.getOntwikkelingskaarten());

		} catch (Exception e) {
			throw new IllegalArguments(e.getMessage());
		}
		return Ontwikkelingskaarten;
	}

	/**
	 * Voert een cheatactie uit in het spel.
	 */
	public void Cheat() {
		spel.Cheat();
	}

}