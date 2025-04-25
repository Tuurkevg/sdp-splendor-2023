package gui.components;

import java.util.List;
import java.util.ResourceBundle;

import dto.EdeleDTO;
import dto.OntwikkelingskaartDTO;
import dto.SpelerDTO;
import enums.EdelsteenType;
import gui.SplendorGUI;
import gui.views.SpelSchermView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Pair;
import utils.AantallenPerKleur;

public class SpelerAanBeurtComponent extends HBox {
	private final Font lemonadaFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Lemonada-SemiBold.ttf"), 20);

	public SpelerAanBeurtComponent(SpelerDTO speler) {
		SpelerAanBeurtOverzicht spelerAanBeurtOverzicht = new SpelerAanBeurtOverzicht(speler.gebruikersnaam(),
				speler.prestigePunten(), speler.getBonussen());
		OntwikkelingskaartenOverzicht ontwikkelingskaartenOverzicht = new OntwikkelingskaartenOverzicht(
				speler.ontwikkelingskaartDTOS());
		EdelenOverzicht edelenOverzicht = new EdelenOverzicht(speler.edeleDTOS());
		EdelsteenfichesOverzicht edelsteenfichesOverzicht = new EdelsteenfichesOverzicht(speler.aantalFiches());

		getChildren().add(spelerAanBeurtOverzicht);
		getChildren().add(edelsteenfichesOverzicht);
		getChildren().add(edelenOverzicht);
		getChildren().add(ontwikkelingskaartenOverzicht);
	}

	private class SpelerAanBeurtOverzicht extends GridPane {
		public SpelerAanBeurtOverzicht(String spelerNaam, int aantalPrestigePunten, AantallenPerKleur bonussen) {
			ResourceBundle rb = ResourceBundle.getBundle("utils.language");
			Text spelerNaamText = new Text(spelerNaam);
			spelerNaamText.setStyle("-fx-font-size: 1.5em;");
			add(spelerNaamText, 0, 0);

			GridPane prestigePuntenGrid = new GridPane();
			Text prestigePuntenText = new Text(rb.getString("sumPrestigePoints"));
			prestigePuntenText.setStyle("-fx-font-size: 1.5em;");
			prestigePuntenGrid.add(prestigePuntenText, 0, 0);
			Text prestigePuntenAantalText = new Text(Integer.toString(aantalPrestigePunten));
			prestigePuntenAantalText.setFont(lemonadaFont);
			prestigePuntenAantalText.setStyle("-fx-font-size: 1.5em;");
			prestigePuntenGrid.add(prestigePuntenAantalText, 1, 0);
			add(prestigePuntenGrid, 0, 1);

			Text bonussenText = new Text(rb.getString("Bonuses"));
			bonussenText.setStyle("-fx-font-size: 1.5em;");
			add(bonussenText, 0, 2);

			GridPane bonussenGrid = new GridPane();
			for (int i = 0; i < bonussen.size(); i++) {
				bonussenGrid.add(maakBonusRechthoek(bonussen.get(i)), i, 0);
			}
			add(bonussenGrid, 0, 3);

			setAlignment(Pos.BOTTOM_LEFT);

			// Set equal spacing between each row
			setVgap(10);

			// Make sure the GridPane expands to fill the parent height
			setMaxHeight(Double.MAX_VALUE);

			Button button = new Button(rb.getString("nextTurn"));

			add(button, 1, 0);

			button.setOnAction(e -> {
				Alert alert = null;
				if (SpelSchermView.mediator.eindeRonde()) {
					alert = new Alert(Alert.AlertType.CONFIRMATION,
							"De winnaars zijn: " + SpelSchermView.mediator.bepaalWinnaars().stream()
									.map(SpelerDTO::gebruikersnaam).reduce((s, s2) -> s + ", " + s2).orElse("Niemand"));
				}
				try {
					SplendorGUI.spelSchermView.update();
					if (alert != null)
						alert.showAndWait();
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			});

			Button cheatButton = new Button(rb.getString("cheat"));
			add(cheatButton, 2, 0);

			cheatButton.setOnAction(event -> {
				SpelSchermView.mediator.Cheat();
				try {
					SplendorGUI.spelSchermView.update();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			});

		}

		private HBox maakBonusRechthoek(Pair<EdelsteenType, Integer> aantalVoorType) {
			Text bonusText = new Text("%d".formatted(aantalVoorType.getValue()));
			bonusText.setFont(lemonadaFont);
			bonusText.setFill(Color.WHITE);
			bonusText.setStroke(Color.BLACK);
			bonusText.setStrokeWidth(0.5);
			bonusText.setStyle("-fx-font-size: 2em;");

			Label label = new Label();
			label.setGraphic(bonusText);
			label.setStyle("-fx-background-color: %s;".formatted(aantalVoorType.getKey().getKleurCode()));
			label.setPadding(new Insets(0, 6, 0, 6));
			label.setMinWidth(35);
			label.setMaxWidth(35);
			label.setAlignment(Pos.CENTER);
			label.setPrefWidth(35);

			HBox hBox = new HBox();
			hBox.getChildren().add(label);
			return hBox;
		}
	}

	private static class OntwikkelingskaartenOverzicht extends GridPane {
		public OntwikkelingskaartenOverzicht(List<OntwikkelingskaartDTO> ontwikkelingskaartDTOS) {
			// setGridLinesVisible(true);
			setHgap(10);
			setVgap(10);

			for (int i = 0; i < ontwikkelingskaartDTOS.size(); i++) {
				OntwikkelingsKaartComponent component = new OntwikkelingsKaartComponent(ontwikkelingskaartDTOS.get(i));
				component.maxHeightProperty().bind(heightProperty().divide(3));
				component.maxWidthProperty().bind(widthProperty().divide(5));
				add(component, i % 10, i / 10);
			}
		}
	}

	private static class EdelenOverzicht extends GridPane {
		public EdelenOverzicht(List<EdeleDTO> edeleDTOS) {
//            setGridLinesVisible(true);
			add(new GridPane(), 0, 0);
			add(new GridPane(), 0, 3);
			add(new GridPane(), edeleDTOS.size() / 2 + 2, 0);
			for (int i = 0; i < edeleDTOS.size(); i++) {
				add(new EdeleComponent(edeleDTOS.get(i)), i / 2 + 1, (i & 1) + 1);
			}
			setHgap(10);
			setVgap(10);
		}
	}

	private static class EdelsteenfichesOverzicht extends GridPane {
		public EdelsteenfichesOverzicht(AantallenPerKleur aantallenPerKleur) {
//            setGridLinesVisible(true);
			add(new GridPane(), 0, 0);

			add(new GridPane(), 2, 0);
			for (int i = 0; i < aantallenPerKleur.size(); i++) {
				EdelsteenType edelsteenType = aantallenPerKleur.get(i).getKey();
				StackPane edelsteenFiche = EdelSteenficheOvaal(edelsteenType, aantallenPerKleur.get(i).getValue());
				add(edelsteenFiche, 1, i + 1);
				edelsteenFiche.setOnMouseClicked(e -> {
					try {
						SpelSchermView.mediator.verwijderFiche(edelsteenType);
						SplendorGUI.spelSchermView.update();
					} catch (Exception ex) {
						throw new RuntimeException(ex);
					}
				});
			}
			setHgap(10);
			setVgap(8);
		}

		private StackPane EdelSteenficheOvaal(EdelsteenType edelsteenType, int aantal) {
			StackPane stackPane = new StackPane();
			Circle ovaal = new Circle(10);
			ovaal.setFill(Color.valueOf(edelsteenType.getKleurCode()));
			ovaal.setStroke(Color.BLACK);
			ovaal.setStrokeWidth(0.5);
			Text text = new Text("%d".formatted(aantal));
//            text.setFont(lemonadaFont);
			text.setFill(Color.WHITE);
			text.setStroke(Color.BLACK);
			text.setStrokeWidth(0.5);
			stackPane.getChildren().add(ovaal);
			stackPane.getChildren().add(text);
			return stackPane;
		}
	}
}
