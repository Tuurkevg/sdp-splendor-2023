package test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.SpelerRepository;

public class TestSpelerRepository {
	SpelerRepository sp;

	@BeforeEach
	void setUp() {
		sp = new SpelerRepository();
	}

	@Test
	void geefSpeler_GeeftcorrecteSpelersTerug() {
		sp.geefSpeler("Test2", 1999);
		sp.geefSpeler("Test1", 1999);
	}

	@Test
	void geefSpeler_GeeftINcorrect_WerptException() {
		assertThrows(NullPointerException.class, () -> sp.geefSpeler("Test2", 15));
		assertThrows(NullPointerException.class, () -> sp.geefSpeler("@cv", 1999));
	}

}
