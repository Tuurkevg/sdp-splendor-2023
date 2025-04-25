package test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.DomeinController;
import domein.Spel;
import domein.SpelerRepository;
import enums.EdelsteenType;
import utils.AantallenPerKleur;

public class TestDomeinController {
	DomeinController dc;
	Spel spel;
	SpelerRepository spelerRepo;
	AantallenPerKleur k;

	@BeforeEach
	void beforeEach() {
		dc = new DomeinController();
		spelerRepo = new SpelerRepository();
		spel = new Spel();
	}

	@Test
	void meldAan_correct_meldAan() {
		dc.meldAan("Test1", 1999);

	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "Bart De Wever", "Wart De Bever", "Eddy Planckear", "Eddy is anders Geaard", "Arthur",
			"Renzeke" })
	void meldAan_incorrecteNaam_faaltAanMelden(String naam) {
		assertThrows(IllegalArgumentException.class, () -> dc.meldAan(naam, 1999));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3, 4, 5, Integer.MAX_VALUE, Integer.MIN_VALUE })
	void meldAan_incorrecteLeeftijd_faaltAanMelden(int leeftijd) {
		assertThrows(IllegalArgumentException.class, () -> dc.meldAan("Test1", leeftijd));
	}

	@Test
	void startSpel_startSpel() {
		dc.meldAan("Test1", 1999);
		dc.meldAan("Test2", 1999);
		dc.startSpel();
	}

	@Test
	void startSpel_Onvoldoendespelers_faaltStartSpel() {
		assertThrows(UnsupportedOperationException.class, () -> dc.startSpel());

		dc.meldAan("Test2", 1999);
		assertThrows(UnsupportedOperationException.class, () -> dc.startSpel());
	}

//---------------------------------------------------------------------------------------//
	@Nested
	class Meldaan {
		@BeforeEach
		void beforeEach() {
			dc.meldAan("Test1", 1999);
			dc.meldAan("Test2", 1999);
			k = new AantallenPerKleur();
		}

		@Test
		void geefKoopbareEdelen_Correct_geefKoopbareEdelen() {
			dc.startSpel();
			dc.geefKoopbareEdelen();
		}

		@Test
		void geefKoopbareEdelen_spelNietGestart_geefKoopbareEdelenFaalt() {

			assertThrows(IllegalArgumentException.class, () -> dc.geefKoopbareEdelen());
		}

		// getest in testSpel
		// @Test
		// void kiesEdele_Correct_kiesEdele() {
		// dc.meldAan("Test1", 1999);
		// dc.meldAan("Test2", 1999);
		// dc.startSpel();
		// dc.geefKoopbareEdelen();
		// dc.koopOntwikkelingskaart(1);
		// dc.kiesEdele(1);
		// }
		@Test
		void kiesEdele_spelNietGestart_kiesEdeleFaalt() {

			assertThrows(IllegalArgumentException.class, () -> dc.kiesEdele(1));
		}

		@Test
		void geefOverzicht_spelGestart_geeftOverzicht() {

			dc.startSpel();
			dc.geefOverzicht();
		}

		@Test
		void geefOverzicht_spelNietGestart_geeftOverzichtFaalt() {

			assertThrows(IllegalArgumentException.class, () -> dc.geefOverzicht());
		}

		@Test
		void geefWinnaars_spelGestart_geeftwinnaars() {

			dc.startSpel();
			dc.geefWinnaars();
		}

		@Test
		void geefWinnaars_spelNietGestart_geeftwinnaarsFaalt() {

			assertThrows(IllegalArgumentException.class, () -> dc.geefWinnaars());
		}

		@Test
		void volgendeSpeler_spelGestart_volgendeSpeler() {

			dc.startSpel();
			dc.volgendeSpeler();
		}

		@Test
		void volgendeSpeler_spelNietGestart_volgendeSpelerFaalt() {

			assertThrows(IllegalArgumentException.class, () -> dc.volgendeSpeler());
		}

		@Test
		void neemEdelsteenfiches_succes_neemtEdelsteenfiches() {

			dc.startSpel();
			k.verhoogAantal(EdelsteenType.BLAUW, 1);
			dc.neemEdelsteenfiches(k);
		}

		@Test
		void neemEdelsteenfiches_SpelNietGestart_neemtEdelsteenfichesFaalt() {
			k.verhoogAantal(EdelsteenType.BLAUW, 1);
			assertThrows(IllegalArgumentException.class, () -> dc.neemEdelsteenfiches(k));
		}

		// getest in testSpel
//		@Test
//		void koopOntwikkelingskaart_succes_Kooptkaarten() {
//
//			k.verhoogAantal(EdelsteenType.BLAUW, 4);
//
//			dc.startSpel();
//			dc.koopOntwikkelingskaart(1);
//		}
		@Test
		void koopOntwikkelingskaart_SpelNietGestart_KooptkaartenFaalt() {

			assertThrows(IllegalArgumentException.class, () -> dc.koopOntwikkelingskaart(1));
		}

//		@Test
//		void verplaatsTerugTeGevenEdelsteenFiches_succes_verplaatst() {
//			dc.startSpel();
//			k.verhoogAantal(EdelsteenType.ROOD, 15);
//			dc.verplaatsTerugTeGevenEdelsteenFiches(k);
//		}

		@Test
		void verplaatsTerugTeGevenEdelsteenFiches_SpelNietGestart_verplaatstFaalt() {
			assertThrows(IllegalArgumentException.class, () -> dc.verplaatsTerugTeGevenEdelsteenFiches(k));
		}

		@Test
		void vulEdelsteenfichesAan_succes_vulEdelsteenfichesAan() {
			dc.startSpel();
			dc.vulEdelsteenfichesAan();
		}

		@Test
		void geefKoopbareOntwikkelingskaarten_succes_geeftKoopbareOntwikkelingskaarten() {

			dc.startSpel();
			dc.geefKoopbareOntwikkelingskaarten();
		}

		@Test
		void geefKoopbareOntwikkelingskaarten_spelNietGestart_geefKoopbareOntwikkelingskaartenFaalt() {

			assertThrows(IllegalArgumentException.class, () -> dc.geefKoopbareOntwikkelingskaarten());
		}

		@Test
		void geefSpelerAanBeurtEdelSteenFiches_succes_geefSpelerAanBeurtEdelSteenFiches() {

			dc.startSpel();
			dc.geefSpelerAanBeurtEdelSteenFiches();
		}

		@Test
		void geefSpelerAanBeurtEdelSteenFiches_spelNietGestart_geefSpelerAanBeurtEdelSteenFichesFaalt() {

			assertThrows(IllegalArgumentException.class, () -> dc.geefSpelerAanBeurtEdelSteenFiches());
		}
	}

	@Test
	void vulEdelsteenfichesAan_NietVoldoendeSpelers_vulEdelsteenfichesAanFaalt() {

		assertThrows(UnsupportedOperationException.class, () -> dc.vulEdelsteenfichesAan());
	}

}
