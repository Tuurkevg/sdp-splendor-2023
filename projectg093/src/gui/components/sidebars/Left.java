package gui.components.sidebars;

import gui.SplendorGUI;
import gui.components.GedekteStapelComponent;
import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class Left extends AnchorPane {

	public Left() {
		GridPane gedekteStapelsGrid = new GridPane();
		gedekteStapelsGrid.prefHeightProperty().bind(Bindings.multiply(heightProperty(), 1));

		gedekteStapelsGrid.setVgap(10);
		gedekteStapelsGrid.setHgap(40);
		for (int i = 3; i >= 1; i--) {
			GedekteStapelComponent component = new GedekteStapelComponent(i,
					SplendorGUI.dc.geefOverzicht().gedekteStapels()[i - 1].ontwikkelingskaartDTOS().size());
			gedekteStapelsGrid.add(component, 1, 4 - i);
			GridPane.setHalignment(component, HPos.CENTER);
			GridPane.setValignment(component, VPos.CENTER);
		}
		gedekteStapelsGrid.add(new GridPane(), 1, 4);

//		gedekteStapelsGrid.prefWidthProperty().bind(Bindings.multiply(widthProperty(), 1));

		// gedekteStapelsGrid.gridLinesVisibleProperty().set(true);

		getChildren().add(gedekteStapelsGrid);
		AnchorPane.setLeftAnchor(gedekteStapelsGrid, 0.0);
		AnchorPane.setRightAnchor(gedekteStapelsGrid, 0.0);
		AnchorPane.setTopAnchor(gedekteStapelsGrid, 0.0);
		AnchorPane.setBottomAnchor(gedekteStapelsGrid, 0.0);
		setStyle("-fx-background-color: lightblue;");
//		visibleProperty().set(false);
	}

}
