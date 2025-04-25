package mediator;

import domein.DomeinController;
import dto.EdeleDTO;
import dto.OntwikkelingskaartDTO;
import dto.SpelerDTO;
import enums.EdelsteenType;
import exceptions.IllegalArguments;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import utils.AantallenPerKleur;

import java.util.List;
import java.util.ResourceBundle;

public class SplendorMediator {
	private final DomeinController dc;
	private AantallenPerKleur geselecteerdeFiches;
	private boolean gedaan;
	private ResourceBundle rb;

	public SplendorMediator(DomeinController dc) {
		this.dc = dc;
		this.geselecteerdeFiches = new AantallenPerKleur();
		gedaan = false;
		rb = ResourceBundle.getBundle("utils.language");
	}

	public void kiesEdele(EdeleDTO edeleDTO) {
		try {
			dc.kiesEdele(geefKoopbareEdelen().indexOf(edeleDTO));

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText(rb.getString("titleError"));
			alert.setContentText(e.getMessage());
			alert.showAndWait();

		}
	}

	private List<EdeleDTO> geefKoopbareEdelen() {
		try {
			return dc.geefKoopbareEdelen();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText(rb.getString("titleError"));
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			return null;
		}

	}

	public void koopOntwikkelingskaart(OntwikkelingskaartDTO ontwikkelingskaartDTO) {
		try {
			dc.koopOntwikkelingskaart(dc.geefOntwikkelingskaarten().indexOf(ontwikkelingskaartDTO));
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText(rb.getString("titleError"));
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}

	}

	private List<OntwikkelingskaartDTO> geefKoopbareOntwikkelingskaarten() {
		return dc.geefKoopbareOntwikkelingskaarten();
	}

	public void selecteerFiche(EdelsteenType kleur) {
		try {
			if (geselecteerdeFiches.size() > 3 && geselecteerdeFiches.getAantal(kleur) < 1) {
				geselecteerdeFiches = new AantallenPerKleur();
				throw new IllegalArgumentException("Deze actie is niet mogelijk");
			}
			geselecteerdeFiches.verhoogAantal(kleur, 1);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText(rb.getString("titleError"));
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}

	}

	public void neemFiches() {
		try {
			if (geselecteerdeFiches.size() == 0) {
				throw new IllegalArguments("Selecteer fiches voor je op bevestig klikt");
			}
			if (geselecteerdeFiches.size() > 3
					|| (geselecteerdeFiches.size() == 1 && geselecteerdeFiches.get(0).getValue() > 2)) {
				geselecteerdeFiches = new AantallenPerKleur();
				throw new IllegalArguments("Ongeldige selectie");
			}
			if (geselecteerdeFiches.size() == 3
					|| (geselecteerdeFiches.size() == 1 && geselecteerdeFiches.get(0).getValue() == 2)) {
				dc.neemEdelsteenfiches(geselecteerdeFiches);
				geselecteerdeFiches = new AantallenPerKleur();
				return;
			}
			throw new IllegalArguments("Ongeldige selectie");

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText(rb.getString("titleError"));
			alert.setContentText(e.getMessage());
			alert.showAndWait();

		}

	}

	public AantallenPerKleur geefTeTonenFiches() {
		AantallenPerKleur aantallenPerKleur = (AantallenPerKleur) dc.geefOverzicht().aantalFiches();
		return AantallenPerKleur.trekAantallenPerKleurAfSafe(aantallenPerKleur, geselecteerdeFiches);
	}

	public AantallenPerKleur geefGeselecteerdeFiches() {
		return geselecteerdeFiches;
	}

	public void resetGeselecteerdeFiches() {
		geselecteerdeFiches = new AantallenPerKleur();
	}

	public void verwijderFiche(EdelsteenType edelsteenType) {
		try {
			AantallenPerKleur aantallenPerKleur = new AantallenPerKleur();
			aantallenPerKleur.verhoogAantal(edelsteenType, 1);
			dc.verplaatsTerugTeGevenEdelsteenFiches(aantallenPerKleur);
		} catch (Exception e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText(rb.getString("titleError"));
			alert.setContentText(e.getMessage());
			alert.showAndWait();

		}
	}

	public List<SpelerDTO> bepaalWinnaars() {
		return dc.geefWinnaars();
	}

	public boolean eindeRonde() {
		geselecteerdeFiches = new AantallenPerKleur();
		if (!dc.geefWinnaars().isEmpty()) {
			gedaan = true;
		}
		dc.volgendeSpeler();
		return gedaan && dc.geefOverzicht().spelerDTOS().stream().filter(SpelerDTO::isAanBeurt).findFirst()
				.equals(dc.geefOverzicht().spelerDTOS().stream().filter(SpelerDTO::isStartSpeler).findFirst());
	}

	public void Cheat() {
		dc.Cheat();
	}
}