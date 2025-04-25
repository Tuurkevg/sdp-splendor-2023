package gui.views;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class WelkomView extends VBox {
	private final Button startButton;

	public WelkomView() {
		ResourceBundle rb = ResourceBundle.getBundle("utils.language");
		Label welcomeLabel = new Label(rb.getString("greeting"));
		welcomeLabel.setStyle("-fx-font-size: 24px;");

		startButton = new Button(rb.getString("buttonWelcome"));
		setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				startButton.fire();
			}
		});

		setSpacing(10);
		setPadding(new Insets(20));
		setAlignment(Pos.CENTER);
		getChildren().addAll(welcomeLabel, startButton);
	}

	public Button getStartButton() {
		return startButton;
	}
}
