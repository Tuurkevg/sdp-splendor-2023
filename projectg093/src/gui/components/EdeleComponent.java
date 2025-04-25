package gui.components;

import dto.EdeleDTO;
import enums.EdelsteenType;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Pair;
import utils.AantallenPerKleur;

public class EdeleComponent extends HBox {

	private EdeleDTO edeleDTO;

	public EdeleDTO getEdeleDTO() {
		return edeleDTO;
	}

	private final Font lemonadaFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Lemonada-SemiBold.ttf"), 20);

	public EdeleComponent(EdeleDTO edeleDTO) {
		if (edeleDTO == null)
			throw new IllegalArgumentException("EdeleDTO mag niet null zijn.");
		if ((long) edeleDTO.aantalBonusPrijs().size() > 3)
			throw new IllegalArgumentException("EdeleDTO mag niet meer dan 3 bonussen bevatten.");
		this.edeleDTO = edeleDTO;
		// Bind width and height to the parent
		parentProperty().addListener((obs, oldParent, newParent) -> {
			if (newParent != null) {
//				prefWidthProperty().bind(Bindings.multiply(Bindings.selectDouble(newParent.boundsInParentProperty(), "width"),  0.4));
				prefHeightProperty().bind(
						Bindings.multiply(Bindings.selectDouble(newParent.boundsInParentProperty(), "height"), 0.6));
				maxWidthProperty().bind(Bindings.multiply(Bindings.selectDouble(newParent.boundsInParentProperty(), "height"),  0.82));
				minWidthProperty().bind(Bindings.multiply(Bindings.selectDouble(newParent.boundsInParentProperty(), "height"),  0.82));
				prefWidthProperty().bind(
						Bindings.multiply(Bindings.selectDouble(newParent.boundsInParentProperty(), "height"), 0.6));
				maxHeightProperty().bind(Bindings.multiply(Bindings.selectDouble(newParent.boundsInParentProperty(), "height"), 0.82));
				minHeightProperty().bind(Bindings.multiply(Bindings.selectDouble(newParent.boundsInParentProperty(), "height"), 0.82));
			} else {
				prefWidthProperty().unbind();
				prefHeightProperty().unbind();
			}
		});
		BackgroundFill backgroundFill = new BackgroundFill(Color.web("#D9D9D9"), new CornerRadii(10), new Insets(0));
		setBackground(new Background(backgroundFill));
		setPadding(new Insets(0, 10, 10, 10));

		GridPane bonussenGrid = maakBonussenGrid(edeleDTO.aantalBonusPrijs());

		HBox prestigePuntenBox = new HBox();
		Text prestigePuntenText = new Text("%d".formatted(edeleDTO.prestigePunten()));
		prestigePuntenText.setFont(lemonadaFont);
		prestigePuntenText.setFill(Color.WHITE);
		prestigePuntenText.setStroke(Color.BLACK);
		prestigePuntenText.setStrokeWidth(0.5);

		prestigePuntenBox.getChildren().add(prestigePuntenText);
		prestigePuntenBox.setMaxWidth(100);

		 HBox.setHgrow(bonussenGrid, Priority.ALWAYS);

		getChildren().addAll(bonussenGrid, prestigePuntenBox);
	}

	private GridPane maakBonussenGrid(AantallenPerKleur aantallenPerKleur) {
		GridPane gridPane = new GridPane();
		gridPane.setVgap(6);
		gridPane.setAlignment(Pos.BOTTOM_LEFT);

		for (int i = 0; i < aantallenPerKleur.size(); i++) {
			VBox vBox = new VBox();
			vBox.getChildren().add(maakBonusRechthoek(aantallenPerKleur.get(i)));
			gridPane.add(vBox, 0, i); // Add the VBox to the GridPane with the row index i and column index 0
		}

		return gridPane;
	}

	private HBox maakBonusRechthoek(Pair<EdelsteenType, Integer> aantalVoorType) {
		Text bonusText = new Text("%d".formatted(aantalVoorType.getValue()));
		bonusText.setFont(lemonadaFont);
		bonusText.setFill(Color.WHITE);
		bonusText.setStroke(Color.BLACK);
		bonusText.setStrokeWidth(0.5);

		Label label = new Label();
		label.setGraphic(bonusText);
		label.setStyle("-fx-background-color: %s;".formatted(aantalVoorType.getKey().getKleurCode()));
		label.setPadding(new Insets(0, 6, 0, 6));
		label.setMinWidth(27);
		label.setMaxWidth(27);
		label.setAlignment(Pos.CENTER);
		label.setPrefWidth(27);

		HBox hBox = new HBox();
		hBox.getChildren().add(label);
		return hBox;
	}

}
