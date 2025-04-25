package main;

import cui.SplendorApplicatie;
import domein.DomeinController;
import gui.SplendorGUI;

public class StartUp {

	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("Usage: java StartUp [gui|app]");
			System.exit(0);
		}

		DomeinController dc = new DomeinController();

		switch (args[0]) {
			case "gui" -> {
				SplendorGUI.dc = dc;
				SplendorGUI.startGUI();
			}
			case "app" -> {
				SplendorApplicatie sa = new SplendorApplicatie(dc);
				sa.start();
			}
			default -> {
				System.out.println("Invalid argument: " + args[0]);
				System.out.println("Usage: java StartUp [gui|app]");
				System.exit(0);
			}
		}
	}
}
