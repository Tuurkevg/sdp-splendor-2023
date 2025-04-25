package cui;

import java.util.InputMismatchException;
import java.util.Scanner;

import domein.DomeinController;

public class SplendorApplicatie {
	private DomeinController dc;

	public SplendorApplicatie(DomeinController dc) {
		this.dc = dc;

	}

	public void start() {
		voegSpelersToe();
		dc.startSpel();
		System.out.println(dc.geefOverzicht());
	}

	public void voegSpelersToe() {
		Scanner invoer = new Scanner(System.in);
		boolean nogToeTeVoegen = true;
		String tempNaam = "";
		String tempJaNee = "";
		int tempGeboorteJaar = 0;
		int aantalSpelers = 0;// vraag dit aan DomeinController

		do {

			try {
				if (aantalSpelers + 1 <= 4) {
					invoer = new Scanner(System.in);
					System.out.println("Geef de speler naam");
					tempNaam = invoer.nextLine().trim();
					while (tempNaam.isEmpty()) {
						System.out.println("\u001B[31mSpelernaam mag niet leeg zijn!\u001B[0m");
						tempNaam = invoer.nextLine().trim();
					}
					System.out.println("Geef het geboortejaar");
					while (true) {
						try {
							tempGeboorteJaar = Integer.parseInt(invoer.nextLine().trim());
						} catch (NumberFormatException nfe) {
							System.out.println("\u001B[31mGeboortejaar moet een getal zijn!\u001B[0m");
							continue;
						}
						break;
					} 
				} else {
					nogToeTeVoegen = false;
				}
				// controlleer of er geen 2 dezelfde spelers zijn
				dc.meldAan(tempNaam, tempGeboorteJaar);
				aantalSpelers++; // vraag dit op aan domeincontroller!
				if (aantalSpelers != 1) {
					boolean gelukt = false;
					do {
						try {
							invoer = new Scanner(System.in);
							System.out.println("Wilt u nog spelers toevoegen? (ja/neen)");
							tempJaNee = invoer.next("^(ja|neen)$").toLowerCase();
							gelukt = true;

						} catch (InputMismatchException ime) {
							System.out.printf("\u001B[31mInvoer mag enkel ja of neen zijn!\u001B[0m%n");
						}
					} while (!gelukt);
					if (tempJaNee.equals("neen")) {
						nogToeTeVoegen = false;
					}
				}
			} catch (IllegalArgumentException | NullPointerException iae) {
				System.out.printf("\u001B[31m%s\u001B[0m%n", iae.getMessage());
			} catch (InputMismatchException ime) {
				System.out.printf("\u001B[31mGebruik correcte input datatype\u001B[0m%n");
			}
		} while (nogToeTeVoegen);
		invoer.close();
		System.out.println("We kunnen beginnen!");
	}
}
