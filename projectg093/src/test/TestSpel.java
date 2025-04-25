package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import domein.Spel;
import domein.Speler;
import exceptions.IllegalArguments;
import utils.AantallenPerKleur;

class TestSpel {
	Spel spel;
	AantallenPerKleur k;
	Speler s;
	Speler s2;

	@BeforeEach
	void setUp() {
		spel = new Spel();
		s = new Speler("Test1", 1999);
	}

	@Test
	void voegSpelerToe_JuisteGegevens_Success() {
		List<Speler> sl = new ArrayList<>();
		spel.voegSpelerToe(s);
		sl.add(s);
		assertEquals(spel.getSpelers(), sl);
	}

	@Test
	void voegSpelerToe_TeVeel_ThrowException() {
		spel.voegSpelerToe(s);
		spel.voegSpelerToe(s);
		spel.voegSpelerToe(s);
		spel.voegSpelerToe(s);
		assertThrows(IllegalArgumentException.class, () -> spel.voegSpelerToe(new Speler("Gebruikersnaam", 2000)));
	}

	@Nested
	class TweeSpelers {
		@BeforeEach
		void setUp() {
			spel.voegSpelerToe(s);
			spel.voegSpelerToe(s);
		}

		@Test
		void vulEdelsteenFiches_met2Spelers_VultBij() {
			spel.vulEdelsteenFiches();
		}

		@Test
		void startSpel_startSpel() {
			spel.startSpel();
		}

		@Test
		void bepaalEindeSpel_SpelNogNietGedaan_False() {
			spel.startSpel();
			assertEquals(spel.bepaalEindeSpel(), false);
		}

		@Test
		void bepaalEindeSpel_SpelNogNietBegonnen_Exception() {
			assertThrows(IllegalArguments.class, () -> spel.bepaalEindeSpel());
		}

		@Test
		void volgendeSpeler_SpelNietGestart_Exception() {
			assertThrows(IllegalArguments.class, () -> spel.volgendeSpeler());
		}

		@Test
		void geefKoopbareEdelen_SpelNietGestart_Exception() {
			spel.voegSpelerToe(s);
			spel.voegSpelerToe(s);
			assertThrows(IllegalArguments.class, () -> spel.geefKoopbareEdelen());
		}

		@Test
		void geefKoopbareEdelen_SpelGestart_geefKoopbareEdelen() {
			spel.startSpel();
			spel.geefKoopbareEdelen();
		}

		@Test
		void geefKoopbareOntwikkelingskaarten_SpelNietGestart_Exception() {
			assertThrows(IllegalArguments.class, () -> spel.geefKoopbareOntwikkelingskaarten());
		}

		@Test
		void geefKoopbareOntwikkelingskaarten_SpelGestart_geefKoopbareOntwikkelingskaarten() {
			spel.startSpel();
			spel.geefKoopbareOntwikkelingskaarten();
		}

		@Test
		void getWinnaars_SpelNietGestart_Exception() {
			assertThrows(IllegalArguments.class, () -> spel.getWinnaars());
		}

		@Test
		void getWinnaars_SpelGestart_getWinnaars() {
			spel.startSpel();
			spel.getWinnaars();
		}

		@Test
		void neemEdelsteenfiches_SpelGestart_SpelGestart() {
			spel.startSpel();
			spel.neemEdelsteenfiches(new AantallenPerKleur());
		}

		@Test
		void neemEdelsteenfiches_SpelNietGestart_Exception() {
			assertThrows(IllegalArguments.class, () -> spel.neemEdelsteenfiches(new AantallenPerKleur()));
		}

		@Test
		void koopOntwikkelingskaart_SpelNietGestart_koopOntwikkelingskaart() {
			assertThrows(IllegalArguments.class, () -> spel.koopOntwikkelingskaart(2));
		}

		@Test
		void verplaatsTerugTeGevenEdelsteenfichess_SpelNietGestart_verplaatsTerugTeGevenEdelsteenfiches() {
			assertThrows(IllegalArguments.class,
					() -> spel.verplaatsTerugTeGevenEdelsteenfiches(new AantallenPerKleur()));
		}

	}

	@Test
	void vulEdelsteenFiches_zonderSpelers_VultNietBij() {
		assertThrows(UnsupportedOperationException.class, () -> spel.vulEdelsteenFiches());
	}

	@Test
	void startSpel_Onvoldoendespelers_faaltStartSpel() {
		assertThrows(UnsupportedOperationException.class, () -> spel.startSpel());
		spel.voegSpelerToe(s);
		assertThrows(UnsupportedOperationException.class, () -> spel.startSpel());
	}

	@Test
	void volgendeSpeler_SpelGestart_VolgendeSpeler() {
		Speler s2 = new Speler("Test4", 1999);// zie en lees
		spel.voegSpelerToe(s);
		spel.voegSpelerToe(s2);
		spel.startSpel();
		spel.volgendeSpeler();
		assertEquals(spel.geefSpelerAanBeurt(), s2);

	}

	@Test
	void getSpelers_Spelersterug_Test() {
		// lege lijst
		List<Speler> sl = new ArrayList<>();
		assertEquals(spel.getSpelers(), sl);
		// 1 speler
		spel.voegSpelerToe(s);
		sl.add(s);
		assertEquals(spel.getSpelers(), sl);
		// 2 speler
		spel.voegSpelerToe(s);
		sl.add(s);
		assertEquals(spel.getSpelers(), sl);
	}

	@Nested
	class tweeverschSpelers {
		@BeforeEach
		void setUp() {
			s2 = new Speler("Test2", 1999);
			spel.voegSpelerToe(s);
			spel.voegSpelerToe(s2);

		}

		@Test
		void geefStartSpeler_geeftStarter_int() {
			spel.startSpel();
			assertEquals(spel.geefStartSpeler(), s);
		}

		@Test
		void geefSpelerAanBeurt_EersteSpeler_geeftSpeler() {
			spel.startSpel();
			assertEquals(spel.geefSpelerAanBeurt(), s);
		}

		@Test
		void geefSpelerAanBeurt_spelnietgestart_Exceptions() {
			assertThrows(IllegalArguments.class, () -> spel.geefSpelerAanBeurt());
		}

		@Test
		void getEdelen_geeftEdelen_Edelen() {
			spel.startSpel();
			spel.getEdelen();
		}

		@Test
		void getOntwikkelingskaarten_geeftgetOntwikkelingskaarten() {
			spel.startSpel();
			spel.getOntwikkelingskaarten();
		}

		@Test
		void getGedekteStapels_geeftgetGedekteStapels() {
			spel.startSpel();
			spel.getGedekteStapels();
		}

		@Test
		void getEdelsteenFiches_geeftgetEdelsteenFiches() {
			spel.startSpel();
			spel.getEdelsteenFiches();
		}

		@Test
		void koopEdele_spelNietGestart_Excpetion() {
			assertThrows(IllegalArguments.class, () -> spel.koopEdele(1));
		}

		@Test
		void geefSpelerAanBeurtEdelsteenFiches_geeftgeefSpelerAanBeurtEdelsteenFiches() {
			spel.startSpel();
			spel.geefSpelerAanBeurtEdelsteenFiches();
		}

		@Test
		void geefSpelerAanBeurtEdelsteenFiches_spelNietGestart_Excpetion() {
			assertThrows(IllegalArguments.class, () -> spel.geefSpelerAanBeurtEdelsteenFiches());
		}
	}

}