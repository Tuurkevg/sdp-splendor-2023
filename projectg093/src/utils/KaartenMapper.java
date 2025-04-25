package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import domein.Edele;
import domein.GedekteStapel;
import domein.Ontwikkelingskaart;
import enums.EdelsteenType;

public class KaartenMapper {

	public KaartenMapper() {

	}

	public static GedekteStapel[] initialiseerGedekteStapels() {
		List<Ontwikkelingskaart> niveauLijst1 = new ArrayList<>();
		// TODO Ontwikkelingskaarten toevoegen aan kaarten
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.ZWART, 1, 0, 0, 4, 0, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.BLAUW, 0, 1, 1, 0, 1, 2));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.BLAUW, 0, 0, 1, 0, 2, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.ZWART, 0, 1, 0, 0, 1, 3));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.ROOD, 0, 0, 3, 0, 0, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.ROOD, 0, 1, 2, 0, 2, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.GROEN, 0, 0, 2, 1, 0, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.ZWART, 0, 3, 0, 0, 0, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.WIT, 0, 0, 0, 2, 2, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.GROEN, 0, 0, 0, 0, 0, 3));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.ROOD, 0, 0, 1, 0, 3, 1));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.ROOD, 0, 1, 0, 2, 0, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.ZWART, 0, 2, 2, 0, 0, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.GROEN, 0, 0, 1, 1, 1, 1));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.WIT, 0, 1, 0, 1, 1, 1));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.ZWART, 0, 0, 2, 2, 0, 1));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.GROEN, 0, 1, 1, 3, 0, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.WIT, 0, 2, 0, 1, 1, 1));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.WIT, 0, 2, 0, 2, 1, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.WIT, 0, 0, 0, 3, 0, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.ZWART, 0, 1, 1, 1, 0, 1));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.WIT, 0, 0, 3, 1, 1, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.ZWART, 0, 2, 0, 0, 0, 1));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.GROEN, 0, 0, 0, 1, 2, 2));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.BLAUW, 0, 0, 0, 0, 3, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.ROOD, 0, 1, 2, 1, 1, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.GROEN, 0, 0, 1, 1, 2, 1));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.WIT, 0, 0, 0, 0, 1, 2));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.BLAUW, 0, 3, 0, 1, 0, 1));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.BLAUW, 0, 2, 1, 0, 0, 2));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.WIT, 1, 4, 0, 0, 0, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.GROEN, 1, 0, 0, 0, 4, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.GROEN, 0, 0, 0, 2, 0, 2));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.ROOD, 0, 0, 2, 0, 0, 2));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.ROOD, 1, 0, 4, 0, 0, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.BLAUW, 0, 2, 0, 0, 0, 2));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.BLAUW, 0, 1, 1, 0, 1, 1));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.BLAUW, 1, 0, 0, 0, 0, 4));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.ROOD, 0, 1, 1, 1, 1, 0));
		niveauLijst1.add(new Ontwikkelingskaart(1, EdelsteenType.ZWART, 0, 1, 1, 3, 0, 1));
		List<Ontwikkelingskaart> niveauLijst2 = new ArrayList<>();
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.ZWART, 2, 0, 5, 0, 0, 0));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.ZWART, 1, 2, 3, 2, 0, 0));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.ROOD, 1, 0, 0, 3, 3, 2));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.GROEN, 1, 0, 2, 3, 2, 0));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.WIT, 2, 1, 0, 0, 2, 4));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.WIT, 1, 0, 2, 3, 0, 3));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.GROEN, 1, 2, 3, 0, 0, 3));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.BLAUW, 2, 0, 2, 0, 4, 1));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.ROOD, 2, 0, 0, 0, 5, 0));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.ROOD, 2, 0, 3, 0, 5, 0));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.BLAUW, 1, 3, 0, 2, 3, 0));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.ROOD, 1, 0, 2, 0, 3, 2));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.GROEN, 3, 6, 0, 0, 0, 0));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.ROOD, 2, 2, 1, 4, 0, 0));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.BLAUW, 2, 0, 0, 5, 0, 0));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.GROEN, 2, 3, 0, 5, 0, 0));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.GROEN, 2, 0, 4, 2, 1, 0));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.WIT, 2, 0, 0, 0, 0, 5));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.WIT, 3, 0, 6, 0, 0, 0));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.ZWART, 2, 4, 0, 1, 0, 2));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.BLAUW, 3, 0, 6, 0, 0, 0));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.ZWART, 2, 5, 0, 0, 0, 3));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.WIT, 1, 3, 0, 0, 2, 2));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.BLAUW, 1, 2, 0, 2, 0, 3));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.ZWART, 1, 3, 3, 0, 2, 0));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.GROEN, 2, 5, 0, 0, 0, 0));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.WIT, 2, 0, 0, 0, 3, 5));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.ZWART, 3, 0, 0, 0, 6, 0));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.BLAUW, 2, 0, 5, 3, 0, 0));
		niveauLijst2.add(new Ontwikkelingskaart(2, EdelsteenType.ROOD, 3, 0, 0, 0, 0, 6));
		List<Ontwikkelingskaart> niveauLijst3 = new ArrayList<>();
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.ROOD, 4, 6, 0, 3, 0, 3));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.ZWART, 5, 0, 0, 0, 5, 7));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.ZWART, 4, 3, 0, 0, 3, 6));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.GROEN, 4, 3, 3, 6, 0, 0));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.WIT, 5, 0, 3, 0, 7, 0));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.WIT, 3, 3, 0, 3, 3, 5));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.ROOD, 5, 7, 0, 0, 0, 3));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.BLAUW, 3, 3, 3, 0, 5, 3));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.GROEN, 3, 0, 5, 3, 3, 3));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.ROOD, 4, 7, 0, 0, 0, 0));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.BLAUW, 4, 0, 7, 0, 0, 0));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.ZWART, 4, 0, 0, 0, 0, 7));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.WIT, 4, 0, 0, 0, 7, 0));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.GROEN, 5, 3, 0, 7, 0, 0));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.WIT, 4, 0, 3, 0, 6, 3));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.BLAUW, 4, 0, 6, 3, 3, 0));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.GROEN, 4, 0, 0, 7, 0, 0));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.ZWART, 3, 5, 3, 3, 0, 3));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.ROOD, 3, 3, 3, 5, 3, 0));
		niveauLijst3.add(new Ontwikkelingskaart(3, EdelsteenType.BLAUW, 5, 0, 7, 3, 0, 0));
		GedekteStapel[] uitvoer = new GedekteStapel[3];
		Stack<Ontwikkelingskaart> niveau1 = new Stack<>();
		Collections.shuffle(niveauLijst1);
		niveau1.addAll(niveauLijst1);
		uitvoer[0] = new GedekteStapel(niveau1, 1);
		Stack<Ontwikkelingskaart> niveau2 = new Stack<>();
		Collections.shuffle(niveauLijst2);
		niveau2.addAll(niveauLijst2);
		uitvoer[1] = new GedekteStapel(niveau2, 2);
		Stack<Ontwikkelingskaart> niveau3 = new Stack<>();
		Collections.shuffle(niveauLijst3);
		niveau3.addAll(niveauLijst3);
		uitvoer[2] = new GedekteStapel(niveau3, 3);
		return uitvoer;
	}

	/**
	 * Initialiseert de edelen
	 * 
	 * @return een lijst met edelen
	 */
	public static Stack<Edele> initialiseerEdelen() {
		List<Edele> edelen = new ArrayList<>();
		edelen.add(new Edele(0, 4, 0, 4, 0));
		edelen.add(new Edele(0, 3, 0, 3, 3));
		edelen.add(new Edele(0, 3, 3, 3, 0));
		edelen.add(new Edele(3, 3, 3, 0, 0));
		edelen.add(new Edele(0, 0, 3, 3, 3));
		edelen.add(new Edele(3, 0, 3, 0, 3));
		edelen.add(new Edele(4, 0, 0, 0, 4));
		edelen.add(new Edele(0, 0, 0, 4, 4));
		edelen.add(new Edele(0, 4, 4, 0, 0));
		edelen.add(new Edele(4, 0, 0, 0, 4));
		Collections.shuffle(edelen);
		Stack<Edele> uitvoer = new Stack<>();
		uitvoer.addAll(edelen);
		return uitvoer;
	}

}
