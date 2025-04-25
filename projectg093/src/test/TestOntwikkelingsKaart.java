package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Ontwikkelingskaart;
import enums.EdelsteenType;
import utils.AantallenPerKleur;

class TestOntwikkelingsKaart {
	Ontwikkelingskaart o;
	AantallenPerKleur k;

	@BeforeEach
	void setUp() {
		o = new Ontwikkelingskaart(1, EdelsteenType.ROOD, 1, 1, 1, 1, 1, 1);
		k = new AantallenPerKleur();
	}

	@Test
	void getNiveau_geeftNiveau() {
		assertEquals(o.getNiveau(), 1);
	}

	@ParameterizedTest
	@ValueSource(ints = { Integer.MIN_VALUE, -5, -1, 0, 4, 8, Integer.MAX_VALUE })
	void controleerNiveau_onGeldigWaarde_throwsException(int niveau) {
		assertThrows(IllegalArgumentException.class, () -> o.controleerNiveau(niveau));

	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3 })
	void controleerNiveau_GeldigWaarde_Controleert(int niveau) {
		o = new Ontwikkelingskaart(niveau, EdelsteenType.ROOD, 1, 1, 1, 1, 1, 1);
		o.controleerNiveau(niveau);
		assertEquals(niveau, o.getNiveau());

	}

	@Test
	void getBonus_geeftBonus() {
		assertEquals(o.getBonus(), EdelsteenType.ROOD);
	}

	@Test
	void getPrestigePunten_geeftPrestigePunten() {
		assertEquals(o.getPrestigePunten(), 1);
	}

	@ParameterizedTest
	@ValueSource(ints = { Integer.MIN_VALUE, -5, -1, 6, Integer.MAX_VALUE })
	void controleerPrestigePunten_onGeldigWaarde_throwsException(int aantalPrestige) {
		assertThrows(IllegalArgumentException.class, () -> o.controleerPrestigePunten(aantalPrestige));

	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3, 4, 5 })
	void controleerPrestigePunten_GeldigeWaarde_geeftWaarden(int aantalPrestige) {
		o = new Ontwikkelingskaart(1, EdelsteenType.ROOD, aantalPrestige, 1, 1, 1, 1, 1);
		o.controleerPrestigePunten(aantalPrestige);
		assertEquals(aantalPrestige, o.getPrestigePunten());

	}

	@Test
	void getAantalKleur_geeftAantalKleur() {
		assertEquals(o.getAantalKleur(EdelsteenType.ROOD), 0);
	}

	@Test
	void get_AlleKleuren_Correct() {
		assertEquals(o.getAantalBlauw(), 0);
		assertEquals(o.getAantalGroen(), 0);
		assertEquals(o.getAantalRood(), 0);
		assertEquals(o.getAantalZwart(), 0);
		assertEquals(o.getAantalWit(), 0);
	}

	@Test
	void getAantalFiches() {
		k.setAantal(EdelsteenType.GROEN, 1);
		k.setAantal(EdelsteenType.WIT, 1);
		k.setAantal(EdelsteenType.BLAUW, 1);
		k.setAantal(EdelsteenType.ZWART, 1);
		k.setAantal(EdelsteenType.ROOD, 1);
		assertEquals(o.getAantalFiches(), k);
	}
}
