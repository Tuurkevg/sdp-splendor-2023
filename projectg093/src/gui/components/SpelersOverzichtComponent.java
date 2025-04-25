package gui.components;

import java.util.List;
import java.util.ResourceBundle;

import dto.EdeleDTO;
import dto.OntwikkelingskaartDTO;
import dto.SpelerDTO;
import enums.EdelsteenType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import utils.AantallenPerKleur;

public class SpelersOverzichtComponent extends VBox {

	public SpelersOverzichtComponent(List<SpelerDTO> spelers) {
//        overzicht.spelerDTOS().remove(0);
		for (SpelerDTO spelerDTO : spelers) {
			getChildren().add(new SpelerOverzicht(spelerDTO.gebruikersnaam(), spelerDTO.aantalFiches(),
					spelerDTO.ontwikkelingskaartDTOS(), spelerDTO.edeleDTOS()));
		}
	}

	private static class SpelerOverzicht extends VBox {
		public SpelerOverzicht(String naam, AantallenPerKleur edelsteenFiches,
				List<OntwikkelingskaartDTO> ontwikkelingskaarten, List<EdeleDTO> edelen) {
			ResourceBundle rb = ResourceBundle.getBundle("utils.language");
			Label naamLabel = new Label(naam);
			naamLabel.setStyle("-fx-font-size: 1.1em;");
			Label prestigePuntenLabel = new Label(String.format(rb.getString("totalPrestigePoints") + " %d",
					Integer.sum(
							ontwikkelingskaarten.stream().reduce(0,
									(subtotal, element) -> subtotal + element.prestigePunten(), Integer::sum),
							edelen.stream().reduce(0, (subtotal, element) -> subtotal + element.prestigePunten(),
									Integer::sum))));
			prestigePuntenLabel.setStyle("-fx-font-size: 1.1em;");
			GridPane edelsteenFichesGrid = new GridPane();
			for (int i = 0; i < edelsteenFiches.size(); i++) {
				edelsteenFichesGrid.add(
						EdelSteenficheOvaal(edelsteenFiches.get(i).getKey(), edelsteenFiches.get(i).getValue()), i, 0);
			}
			GridPane ontwikkelingskaartenGrid = new GridPane();
			int columnIndex = 0;
			int rowIndex = 0;

			for (OntwikkelingskaartDTO ontwikkelingskaartDTO : ontwikkelingskaarten) {
				OntwikkelingsKaartComponent ontwikkelingskaartComponent = new OntwikkelingsKaartComponent(
						ontwikkelingskaartDTO);
				ontwikkelingskaartComponent.maxWidthProperty().bind(widthProperty().divide(5));
				ontwikkelingskaartComponent.maxHeightProperty().bind(heightProperty().divide(3));
				GridPane.setColumnIndex(ontwikkelingskaartComponent, columnIndex);
				GridPane.setRowIndex(ontwikkelingskaartComponent, rowIndex);
				ontwikkelingskaartenGrid.getChildren().add(ontwikkelingskaartComponent);

				columnIndex++;
				if (columnIndex == 5) {
					columnIndex = 0;
					rowIndex++;
				}
			}

			GridPane edelenGrid = new GridPane();
			for (int i = 0; i < edelen.size(); i++) {
				edelenGrid.add(new EdeleComponent(edelen.get(i)), i, 0);
			}

			getChildren().add(naamLabel);
			getChildren().add(prestigePuntenLabel);
			getChildren().add(edelsteenFichesGrid);
			getChildren().add(ontwikkelingskaartenGrid);
//            getChildren().add(edelenGrid);
		}

		private StackPane EdelSteenficheOvaal(EdelsteenType edelsteenType, int aantal) {
			StackPane stackPane = new StackPane();
			Circle ovaal = new Circle(10);
			ovaal.setFill(Color.valueOf(edelsteenType.getKleurCode()));
			ovaal.setStroke(Color.BLACK);
			ovaal.setStrokeWidth(0.5);
			Text text = new Text(String.valueOf(aantal));
			text.setFill(Color.WHITE);
			text.setStroke(Color.WHITE);
			if (edelsteenType == EdelsteenType.WIT) {
				text.setFill(Color.BLACK);
				text.setStroke(Color.BLACK);
			}
			text.setStrokeWidth(0.5);
			stackPane.getChildren().add(ovaal);
			stackPane.getChildren().add(text);
			return stackPane;
		}

	}
}
