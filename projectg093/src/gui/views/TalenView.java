package gui.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TalenView extends VBox {

	private final ComboBox<String> comboBoxTaal;
	private final Button bevestigBtn;

	public TalenView(Stage primaryStage) {
		Label chooseLanguage = new Label("Choose your language!");
		Label kiestaal = new Label("Kies uw taal!");
		Label kiestaalRU = new Label("Выберите свой язык!");
		chooseLanguage.setStyle("-fx-font: 24px black; -fx-font-family: \"Book Antiqua\" ");
		kiestaal.setStyle("-fx-font: 24px black; -fx-font-family: \"Book Antiqua\" ");
		kiestaalRU.setStyle("-fx-font: 24px black; -fx-font-family: \"Book Antiqua\" ");

		comboBoxTaal = new ComboBox<>();
		comboBoxTaal.getItems().addAll("English", "Nederlands", "Русский");
		bevestigBtn = new Button("Bevestigen");

		double height = primaryStage.getHeight();

		if (height <= 400) {
			setSpacing(20);
			kiestaalRU.setPadding(new Insets(0, 0, 20, 0));
		} else {
			setSpacing(25);
			kiestaalRU.setPadding(new Insets(0, 0, 150, 0));
		}

//		setSpacing(20);
//		setPadding(new Insets(20));
		setAlignment(Pos.CENTER);

		getChildren().addAll(chooseLanguage, kiestaal, kiestaalRU, comboBoxTaal, bevestigBtn);

		primaryStage.heightProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.doubleValue() <= 400) {
				setSpacing(20);
				kiestaalRU.setPadding(new Insets(0, 0, 20, 0));
			} else {
				setSpacing(25);
				kiestaalRU.setPadding(new Insets(0, 0, 150, 0));
			}
		});
		setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				bevestigBtn.fire();
			}
		});
	}

	public ComboBox<String> toonComboBoxTaal() {
		return comboBoxTaal;
	}

	public Button toonBevestigBtn() {
		return bevestigBtn;
	}

}
