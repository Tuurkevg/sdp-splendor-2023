package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.GedekteStapel;
import domein.Ontwikkelingskaart;
import enums.EdelsteenType;

public class TestGedekteStapel {

	GedekteStapel gedekteStapel;
	Stack<Ontwikkelingskaart> gedekteKaarten;

	@BeforeEach
	void setUp() {
		gedekteKaarten = new Stack<>();
		gedekteStapel = null;
	}

	@ParameterizedTest
	@ValueSource(ints = { Integer.MIN_VALUE, 0, 4, Integer.MAX_VALUE })
	void setNiveau_foutieveWaarden_throw_Exception(int niveau) {
		for (int i = 0; i < 20; i++) {
			gedekteKaarten.add(new Ontwikkelingskaart(3, EdelsteenType.ROOD, 1, 1, 1, 1, 1, 1));
		}
		assertThrows(IllegalArgumentException.class, () -> gedekteStapel = new GedekteStapel(gedekteKaarten, niveau));
	}

	@Test
	void setNiveau_correcteWaarden_setNiveau() {
		for (int i = 0; i < 20; i++) {
			gedekteKaarten.add(new Ontwikkelingskaart(3, EdelsteenType.ROOD, 1, 1, 1, 1, 1, 1));
		}
		gedekteStapel = new GedekteStapel(gedekteKaarten, 3);
		assertEquals(3, gedekteStapel.getNiveau());

	}

	@Test
	void maakGedekteStapel_niveau3_20kaarten_MaaktGedekteStapel() {
		for (int i = 0; i < 20; i++) {
			gedekteKaarten.add(new Ontwikkelingskaart(3, EdelsteenType.ROOD, 1, 1, 1, 1, 1, 1));
		}
		gedekteStapel = new GedekteStapel(gedekteKaarten, 3);
		assertEquals(20, gedekteStapel.getGedekteKaarten().size());
	}

	@Test
	void maakGedekteStapel_niveau2_30kaarten_MaaktGedekteStapel() {
		for (int i = 0; i < 30; i++) {
			gedekteKaarten.add(new Ontwikkelingskaart(2, EdelsteenType.ROOD, 1, 1, 1, 1, 1, 1));
		}
		gedekteStapel = new GedekteStapel(gedekteKaarten, 2);
		// assertequels
		assertEquals(30, gedekteStapel.getGedekteKaarten().size());
	}

	@Test
	void getKaarten_maakGedekteStapel_niveau1_40kaarten_geef40Kaarten() {
		for (int i = 0; i < 40; i++) {
			gedekteKaarten.add(new Ontwikkelingskaart(1, EdelsteenType.ROOD, 1, 1, 1, 1, 1, 1));
		}

		// assertEquals(gedekteKaarten., gedekteStapel.getGedekteKaarten());
		gedekteStapel = new GedekteStapel(gedekteKaarten, 1);
		assertEquals(40, gedekteStapel.getGedekteKaarten().size());

	}

	@Test
	void maakGedekteStapel_incorrecteGedekteStapel_ThrowException() {

		assertThrows(IllegalArgumentException.class, () -> new GedekteStapel(null, 1));
		assertThrows(IllegalArgumentException.class, () -> new GedekteStapel(gedekteKaarten, 1));
		for (int i = 0; i < 85; i++) {
			gedekteKaarten.add(new Ontwikkelingskaart(1, EdelsteenType.ROOD, 1, 1, 1, 1, 1, 1));
		}
		assertThrows(IllegalArgumentException.class, () -> new GedekteStapel(gedekteKaarten, 1));
	}

	@ParameterizedTest
	@ValueSource(ints = { Integer.MAX_VALUE, -5, Integer.MIN_VALUE, 0, 4, 9 })
	void maakGedekteStapel_incorrecteNiveau_ThrowException(int niveau) {
		for (int i = 0; i < 30; i++) {
			gedekteKaarten.add(new Ontwikkelingskaart(2, EdelsteenType.ROOD, 1, 1, 1, 1, 1, 1));
		}
		assertThrows(IllegalArgumentException.class, () -> new GedekteStapel(gedekteKaarten, niveau));

	}

	@Test
	void neemOntwikkelingskaart_Correct_verwijderd1Kaart() {

		for (int i = 0; i < 20; i++) {
			gedekteKaarten.add(new Ontwikkelingskaart(3, EdelsteenType.ROOD, 1, 1, 1, 1, 1, 1));
		}
		gedekteStapel = new GedekteStapel(gedekteKaarten, 3);
		assertEquals(gedekteKaarten, gedekteStapel.getGedekteKaarten());
	}

}
