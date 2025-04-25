package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Edele;
import enums.EdelsteenType;
import utils.AantallenPerKleur;

public class TestEdele {
	Edele edel;
	AantallenPerKleur k;

	@BeforeEach
	void setUp() {
		edel = new Edele(1, 1, 1, 1, 1);
		k = new AantallenPerKleur();
	}

	@ParameterizedTest
	@ValueSource(ints = { Integer.MIN_VALUE, -5, -1, 8, Integer.MAX_VALUE })
	void setAantalKleur_onGeldigWaarde_throwsException(int aantalKleur) {
		assertThrows(IllegalArgumentException.class, () -> edel.setAantalGroen(aantalKleur));
		assertThrows(IllegalArgumentException.class, () -> edel.setAantalWit(aantalKleur));
		assertThrows(IllegalArgumentException.class, () -> edel.setAantalBlauw(aantalKleur));
		assertThrows(IllegalArgumentException.class, () -> edel.setAantalZwart(aantalKleur));
		assertThrows(IllegalArgumentException.class, () -> edel.setAantalRood(aantalKleur));
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3, 7 })
	void setAantalKleur_geldigeWaarde_setAantalKleur(int aantalKleur) {
		edel.setAantalGroen(aantalKleur);
		edel.setAantalWit(aantalKleur);
		edel.setAantalBlauw(aantalKleur);
		edel.setAantalZwart(aantalKleur);
		edel.setAantalRood(aantalKleur);
		assertEquals(aantalKleur, edel.getAantalGroen());
		assertEquals(aantalKleur, edel.getAantalWit());
		assertEquals(aantalKleur, edel.getAantalBlauw());
		assertEquals(aantalKleur, edel.getAantalZwart());
		assertEquals(aantalKleur, edel.getAantalRood());
	}

	@Test
	void getPrestigePunten_Verwacht3_Geeft3PrestigePunten() {
		assertEquals(3, Edele.getPrestigePunten());
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3, 7 })
	void aantalBonusPrijs_geeftCorrectAantalTerug(int prestigePunten) {
		edel.setAantalGroen(prestigePunten);
		k.setAantal(EdelsteenType.GROEN, prestigePunten);
		assertEquals(edel.getAantalGroen(), k.getAantal(EdelsteenType.GROEN));
	}

}
