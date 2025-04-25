package test;


import domein.Edele;
import domein.Ontwikkelingskaart;
import domein.Speler;
import enums.EdelsteenType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import utils.AantallenPerKleur;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestSpeler {
	Speler speler;
	AantallenPerKleur ak;

	//
	@BeforeEach
	void setUp() {
		speler = new Speler("test", 2000);
		ak = new AantallenPerKleur();
	}

	@Test
	void getGebruikersnaam_geeftcorrecteNaam() {
		assertEquals("test", speler.getGebruikersnaam());
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "Gebruikersnaam$", "-&é", "Gebruikers!naam", " gebruikersnaam", "_gebruikersnaam", "911",
			"9gebruikersnaam" })
	void setGebruikersnaam_onGeldigeGebruikersnaam_ThrowsException(String naam) {
		assertThrows(IllegalArgumentException.class, () -> speler.controleerGebruikersnaam(naam));
	}

	@ParameterizedTest
	@ValueSource(strings = { "Gebruikersnaam", "Gebruikers naam", "Gebruikers_naam", "GeBRuikersnaam",
			"mijn_gebruikersnaam 123", "GEBRUIKERSNAAM", "Gebr1ikersnaam", "k565656 9999" })
	void setGebruikersnaam_geldigeGebruikersnaam_setGeboortejaar(String naam) {
		speler.controleerGebruikersnaam(naam);
	}

	@Test
	void getGeboortejaar_geeftcorrecteGeboortejaar() {
		assertEquals(2000, speler.getGeboortejaar());
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, -10, 1899, 1800, 202, 2018, 2024, 5000000, Integer.MAX_VALUE, Integer.MIN_VALUE })
	void setGeboortejaar_onGeldigeGeboortejaar_ThrowsException(int geboortejaar) {
		assertThrows(IllegalArgumentException.class, () -> speler.controleerGeboortejaar(geboortejaar));
	}

	@ParameterizedTest
	@ValueSource(ints = { 2000, 2017 })
	void setGeboortejaar_geldigeGeboortejaar_setGeboortejaar(int geboortejaar) {
		speler.controleerGeboortejaar(geboortejaar);
	}

	@Test
	void bepaalPrestigePunten_berekentPrestigepunten() {
		
		assertEquals(0, speler.bepaalPrestigePunten());
	}

	@Test
	void getEdelsteenFiches_geeftEdelsteenFiches() {
		ak.verhoogAantal(EdelsteenType.ROOD,1);
		ak.verhoogAantal(EdelsteenType.ROOD,1);
		speler.voegEdelsteenfichesToe(ak);
		assertEquals(speler.getEdelsteenfiches(),ak);
		
	}

	@Test
	void getEdelen_geeftgetEdelenLeeg() {
		assertEquals(new ArrayList<>(), speler.getEdelen());
	}

	@Test
	void voegEdelsteenfichesToe_correct_geeftFichesTerug() {
		ak.verhoogAantal(EdelsteenType.ROOD,1);
		ak.verhoogAantal(EdelsteenType.ROOD,1);
		speler.voegEdelsteenfichesToe(ak);
		assertEquals(speler.getEdelsteenfiches().getAantal(EdelsteenType.ROOD),2);
	}

	@Test
	void voegEdelsteenfichesToe_incorrect_Exception() {
		assertThrows(IllegalArgumentException.class, () -> speler.voegEdelsteenfichesToe(null));
	}

	@Test
	void voegOntwikkelingsKaartToe_kleurbestaatnietCombinatie_werptException() {
		Ontwikkelingskaart ok = new Ontwikkelingskaart(1, EdelsteenType.ROOD, 1, 1, 1, 1, 1, 1);
		assertThrows(IllegalArgumentException.class, () -> speler.voegOntwikkelingsKaartToe(ok));
	}
	
	@Test
	void verplaatsTerugTeGevenEdelsteenfiches_10fichesOverhouden_werptException() {

		assertThrows(IllegalArgumentException.class, () -> speler.verplaatsTerugTeGevenEdelsteenfiches(ak));
	}

	@Test
	void voegEdeleToe_edelenToegeVoeg() {
		Edele e = new Edele(1, 1, 1, 1, 1);
		speler.voegEdeleToe(e);
	}

	@Test
	void voegEdeleToe_leeg_WerptException() {

		assertThrows(NullPointerException.class, () -> speler.voegEdeleToe(null));
	}

	@Test
	void getOntwikkelingskaarten_geeftOntwikkelingsKaartenLeeg() {
		assertEquals(new ArrayList<>(), speler.getOntwikkelingskaarten());
	}

	@Test
	void geefBonussen_geeftBonussen() {
		assertEquals(speler.geefBonussen(), ak);
	}

	@Test
	void geefTotaalEdelFichesBonussen_geeftgeefTotaalEdelFichesBonussen() {
		assertEquals(speler.geefTotaalEdelFichesBonussen(), ak);
	}

}