package gui.views;

import java.util.ResourceBundle;

import gui.SplendorGUI;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SpelersToevoegenView extends VBox {
	private final GridPane gridPane;
	private GridPane gridPaneSpeler;
	private TextField txfGebruikersnaam;
	private TextField txfGeboortejaar;
	int rij;
	public static Button btnSpeel;

	public SpelersToevoegenView(Stage primaryStage) {
		ResourceBundle rb = ResourceBundle.getBundle("utils.language");
		this.rij = 1;

		gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPrefSize(0, 1000);
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		GridPane spelersToevoegenGridPane = new GridPane();
		spelersToevoegenGridPane.setAlignment(Pos.TOP_LEFT);
		spelersToevoegenGridPane.setVgap(50);
		spelersToevoegenGridPane.setHgap(50);

		Label spelersToevoegen = new Label(rb.getString("addPlayers"));
		spelersToevoegen.setStyle("-fx-font: 24px bold; -fx-font-family: \"Book Antiqua\" ");
		spelersToevoegenGridPane.add(spelersToevoegen, 0, 0);

		Label lGebruikersnaam = new Label(rb.getString("username"));
		GridPane.setColumnIndex(lGebruikersnaam, 0);
		GridPane.setRowIndex(lGebruikersnaam, 8);

		Label lGeboortejaar = new Label(rb.getString("birthyear"));
		GridPane.setColumnIndex(lGeboortejaar, 1);
		GridPane.setRowIndex(lGeboortejaar, 8);

		txfGebruikersnaam = new TextField();
		txfGebruikersnaam.promptTextProperty().set(rb.getString("username"));
		txfGebruikersnaam.setAlignment(Pos.CENTER);
		txfGebruikersnaam.setMinHeight(20);
		GridPane.setColumnIndex(txfGebruikersnaam, 0);
		GridPane.setRowIndex(txfGebruikersnaam, 9);

		txfGeboortejaar = new TextField();
		txfGeboortejaar.promptTextProperty().set(rb.getString("birthyear"));
		txfGeboortejaar.setAlignment(Pos.CENTER);
		GridPane.setColumnIndex(txfGeboortejaar, 1);
		GridPane.setRowIndex(txfGeboortejaar, 9);

		Button btnSpelerToevoegen = new Button(rb.getString("addPlayer"));
		btnSpelerToevoegen.setAlignment(Pos.CENTER);
		GridPane.setColumnIndex(btnSpelerToevoegen, 2);
		GridPane.setRowIndex(btnSpelerToevoegen, 9);

		btnSpelerToevoegen.setOnAction(event -> {
			spelerToevoegen(rb);
		});

		txfGeboortejaar.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				spelerToevoegen(rb);
			}
		});

		btnSpeel = new Button(rb.getString("play"));
		btnSpeel.setAlignment(Pos.CENTER);
		GridPane.setColumnIndex(btnSpeel, 1);
		GridPane.setRowIndex(btnSpeel, 10);
		btnSpeel.setOnAction(event -> {
			try {
				SplendorGUI.dc.startSpel();
//				CustomSplendorGUI customSplendorGUI = new CustomSplendorGUI();
//				primaryStage.close();
//				customSplendorGUI.start(SplendorGUI.dc.geefOverzicht());
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText(rb.getString("titleError"));
				alert.setContentText(e.getMessage());
				alert.showAndWait();

			}
			SplendorGUI.spelSchermView = new SpelSchermView(primaryStage);
			primaryStage.setScene(new Scene(SplendorGUI.spelSchermView));
			SplendorGUI.spelSchermView.getStylesheets()
					.add(getClass().getResource("/utils/stylesheet2.css").toString());
			primaryStage.setTitle(rb.getString("splendorGame"));
			primaryStage.setMaximized(true);
		});

		gridPane.getChildren().addAll(spelersToevoegenGridPane, lGebruikersnaam, txfGebruikersnaam, lGeboortejaar,
				txfGeboortejaar, btnSpelerToevoegen, btnSpeel);

		getChildren().addAll(gridPane);

	}

	private GridPane toonSpeler(String gebruikersnaam, int geboortejaar, ResourceBundle rb) {

		gridPaneSpeler = new GridPane();

		Label lGebruikersnaam = new Label();
		lGebruikersnaam.setText(
				String.format(rb.getString("player") + ": %n" + rb.getString("username") + ": %s ", gebruikersnaam));
		GridPane.setColumnIndex(lGebruikersnaam, 0);
		GridPane.setRowIndex(lGebruikersnaam, rij);

		Label lGeboortejaar = new Label();
		lGeboortejaar.setText(String.format("%n			" + rb.getString("birthyear") + ": %s", geboortejaar));
		GridPane.setColumnIndex(lGeboortejaar, 1);
		GridPane.setRowIndex(lGeboortejaar, rij);

		gridPaneSpeler.getChildren().addAll(lGebruikersnaam, lGeboortejaar);
		return gridPaneSpeler;
	}

	private void spelerToevoegen(ResourceBundle rb) {
		try {
			String gebruikersnaam = txfGebruikersnaam.getText();
			int geboortejaar = Integer.parseInt(txfGeboortejaar.getText());
			SplendorGUI.dc.meldAan(gebruikersnaam, geboortejaar);
			gridPane.add(this.toonSpeler(gebruikersnaam, geboortejaar, rb), 0, rij, 2, 1);
			rij++;
			txfGebruikersnaam.clear();
			txfGeboortejaar.clear();
			toonSpeler(gebruikersnaam, geboortejaar, rb);
			txfGebruikersnaam.requestFocus();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText(rb.getString("titleError"));
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}

}
