package gui.components.sidebars;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dto.OntwikkelingskaartDTO;
import enums.EdelsteenType;
import exceptions.IllegalArguments;
import gui.SplendorGUI;
import gui.components.OntwikkelingsKaartComponent;
import gui.views.SpelSchermView;
import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import utils.AantallenPerKleur;

public class Center extends AnchorPane {

	private static final int NUM_NIVEAUS = 3;
	private static final int CIRCLE_SIZE = 23;

	public Center(List<OntwikkelingskaartDTO> ontwikkelingskaarten) {
		ResourceBundle rb = ResourceBundle.getBundle("utils.language");
		// Create grid for ontwikkelingskaarten
		GridPane ontwikkelingsKaartenGrid = new GridPane();
		ontwikkelingsKaartenGrid.prefHeightProperty().bind(Bindings.multiply(heightProperty(), 0.9));
		ontwikkelingsKaartenGrid.prefWidthProperty().bind(Bindings.multiply(widthProperty(), 0.3));
		ontwikkelingsKaartenGrid.hgapProperty().bind(Bindings.multiply(ontwikkelingsKaartenGrid.widthProperty(), 0.1));
		ontwikkelingsKaartenGrid.vgapProperty()
				.bind(Bindings.multiply(ontwikkelingsKaartenGrid.heightProperty(), 0.04));

		// Group ontwikkelingskaarten by level
		List<OntwikkelingskaartDTO>[] ontwikkelingskaartenPerNiveau = new ArrayList[NUM_NIVEAUS];
		for (OntwikkelingskaartDTO ontwikkelingskaartDTO : ontwikkelingskaarten) {
			int niveau = ontwikkelingskaartDTO.niveau() - 1;
			if (ontwikkelingskaartenPerNiveau[niveau] == null) {
				ontwikkelingskaartenPerNiveau[niveau] = new ArrayList<>();
			}
			ontwikkelingskaartenPerNiveau[niveau].add(ontwikkelingskaartDTO);
		}

		// Add ontwikkelingskaarten to grid
		for (int i = 0; i < ontwikkelingskaartenPerNiveau.length; i++) {
			for (int j = 0; j < ontwikkelingskaartenPerNiveau[i].size(); j++) {
				OntwikkelingsKaartComponent ontwikkelingsKaart = new OntwikkelingsKaartComponent(
						ontwikkelingskaartenPerNiveau[i].get(j));
				ontwikkelingsKaart.setOnMouseClicked(e -> {
					SpelSchermView.mediator.koopOntwikkelingskaart(ontwikkelingsKaart.getOntwikkelingskaart());
					try {
						SplendorGUI.spelSchermView.update();
					} catch (Exception ex) {
						throw new RuntimeException(ex);
					}
				});
				// ontwikkelingsKaartenGrid.add(ontwikkelingsKaart, j + 1, i + 1);
				ontwikkelingsKaartenGrid.add(ontwikkelingsKaart, j + 1, ontwikkelingskaartenPerNiveau[i].size() - i);
				GridPane.setHalignment(ontwikkelingsKaart, HPos.CENTER);
				GridPane.setValignment(ontwikkelingsKaart, VPos.CENTER);
			}
		}

		// Add empty grid panes for spacing
		ontwikkelingsKaartenGrid.add(new GridPane(), 0, ontwikkelingskaartenPerNiveau[0].size() + 1);
		ontwikkelingsKaartenGrid.add(new GridPane(), 5, ontwikkelingskaartenPerNiveau[0].size() + 1);
		ontwikkelingsKaartenGrid.add(new GridPane(), 6, ontwikkelingskaartenPerNiveau[0].size() + 1);

		// Add grid to center pane
		AnchorPane.setLeftAnchor(ontwikkelingsKaartenGrid, 0.0);
		AnchorPane.setRightAnchor(ontwikkelingsKaartenGrid, 0.0);
		this.getChildren().add(ontwikkelingsKaartenGrid);

		// Create grid for circles
		AnchorPane right = new AnchorPane();
		AnchorPane.setRightAnchor(right, 0.0);
		GridPane cirkelGrid = new GridPane();
		cirkelGrid.setVgap(20);

		Color[] colors = { Color.web("#FF0000"), Color.web("#10F319"), Color.web("#FFFFFF"), Color.web("#000000"),
				Color.web("#000AFF") };

		AantallenPerKleur aantalFiches = SpelSchermView.mediator.geefTeTonenFiches();
		// Add circles to grid
		for (int i = 0; i < colors.length; i++) {
			CircleComponent circle = new CircleComponent(i, Color.web(EdelsteenType.values()[i].getKleurCode()),
					aantalFiches.getAantal(EdelsteenType.values()[i]));
			circle.setOnMouseClicked(e -> {
				SpelSchermView.mediator.selecteerFiche(EdelsteenType.values()[circle.getIndex()]);
				for (int j = 0; j < colors.length; j++) {
					circle.setAantal(aantalFiches.getAantal(EdelsteenType.values()[j]));
				}
				try {
					SplendorGUI.spelSchermView.update();
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			});
			cirkelGrid.add(circle, 0, i + 1);
		}

		// Create grid for text fields
		GridPane textFieldGrid = new GridPane();
		textFieldGrid.setHgap(10);
		textFieldGrid.setVgap(10);

		AantallenPerKleur aantalFichesOverzicht = SpelSchermView.mediator.geefGeselecteerdeFiches();

		// Create text fields for live quantity display
		Text[] textFields = new Text[colors.length];
		String[] colorLabels = { rb.getString("white"), rb.getString("red"), rb.getString("blue"),
				rb.getString("green"), rb.getString("black") }; // Toegevoegde
		// tekstlabels
		for (int i = 0; i < colors.length; i++) {
			textFields[i] = new Text();
			textFields[i].setText(String.valueOf(aantalFichesOverzicht.getAantal(EdelsteenType.values()[i])));

			// Create a label for the color
			Label colorLabel = new Label(colorLabels[i]); // Toegevoegde label met de tekst

			// Create a VBox to hold the label and text field
			VBox vbox = new VBox(5);
			vbox.setAlignment(Pos.CENTER);
			vbox.getChildren().addAll(colorLabel, textFields[i]);

			// Add text fields to the grid with pairs
			if (i % 2 == 0) {
				textFieldGrid.add(vbox, i / 2, 0);
			} else {
				textFieldGrid.add(vbox, i / 2, 1);
			}
		}

		// Add an empty pane for left margin
		Pane leftMarginPane = new Pane();
		textFieldGrid.add(leftMarginPane, 0, 0); // Added at column 0

		// Add text field grid to cirkelGrid with left alignment
		cirkelGrid.add(textFieldGrid, 0, colors.length + 1);
		GridPane.setHalignment(textFieldGrid, HPos.LEFT);

		// Create buttons
		Button bevestigButton = new Button(rb.getString("confirm"));
		Button restartButton = new Button(rb.getString("restart"));

		// Add buttons to grid
		cirkelGrid.add(bevestigButton, 0, colors.length + 2);
		cirkelGrid.add(restartButton, 1, colors.length + 2);
		// Reduce the margin between buttons
		GridPane.setMargin(bevestigButton, new Insets(0, -55, 0, -20));
		GridPane.setMargin(restartButton, new Insets(0, 0, 0, -50));
		// Add grid to right pane
		right.getChildren().add(cirkelGrid);
		AnchorPane.setTopAnchor(right, 0.0);
		AnchorPane.setBottomAnchor(right, 0.0);
		this.getChildren().add(right);

		// Add action listeners to buttons
		bevestigButton.setOnAction(e -> {
			try {
				SpelSchermView.mediator.neemFiches();
				SplendorGUI.spelSchermView.update();
			} catch (Exception ex) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Error");
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
				throw new IllegalArguments(ex.getMessage());
			}
		});

		restartButton.setOnAction(e -> {
			try {
				SpelSchermView.mediator.resetGeselecteerdeFiches();
				SplendorGUI.spelSchermView.update();
			} catch (Exception ex) {
				throw new IllegalArguments(ex.getMessage());
			}
		});
	}

	private class CircleComponent extends StackPane {
		private final int index;
		private int aantal;
		private final Text text;

		public CircleComponent(int index, Color color, int aantal) {
			this.index = index;
			this.aantal = aantal;
			Circle circle = new Circle(CIRCLE_SIZE);
			circle.setFill(color);
			circle.setStroke(Color.BLACK);
			if (index == 4) {
				circle.setStroke(Color.GRAY);
			}
			circle.setStrokeWidth(1);

			text = new Text(String.valueOf(aantal));
			if (index == 4 || index == 2) {
				text.setFill(Color.WHITE);
			}

			this.getChildren().addAll(circle, text);
		}

		public int getIndex() {
			return index;
		}

		public void setAantal(int aantal) {
			this.aantal = aantal;
			text.setText(String.valueOf(aantal));
		}
	}

}