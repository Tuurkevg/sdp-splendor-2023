package gui.components;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GedekteStapelComponent extends VBox {
	public GedekteStapelComponent(int niveau, int aantalKaarten) {
		CornerRadii radius = new CornerRadii(10);
		if (niveau < 1 || niveau > 3)
			throw new IllegalArgumentException("Niveau moet tussen 1 en 3 liggen.");

		String kleur = switch (niveau) {
		case 1 -> "#287233"; // Groen
		case 2 -> "#FAF233"; // Geel
		default -> "#1E2DB1"; // Blauw
		};
		BackgroundFill backgroundFill = new BackgroundFill(Color.web(kleur), new CornerRadii(10), new Insets(0));
		setBackground(new Background(backgroundFill));
		HBox circleContainer = new HBox();
		circleContainer.setAlignment(Pos.BOTTOM_CENTER);
		circleContainer.setSpacing(5);
		for (int i = 0; i < niveau; i++) {
			circleContainer.getChildren().add(createCircle());
		}
		// aanmaken text veld voor aantal kaarten op stapel
		Text text = new Text(String.valueOf(aantalKaarten));
		if (niveau == 2)
			text.setFill(Color.BLACK);
		else
			text.setFill(Color.WHITE);

		text.setFont(Font.font(20));
		setAlignment(Pos.TOP_CENTER);
		getChildren().add(text);

		setMargin(circleContainer, new Insets(10, 0, 10, 0));
		getChildren().addAll(new Region(), circleContainer);
		setAlignment(Pos.BOTTOM_CENTER);

		// Bind width and height to the parent
		parentProperty().addListener((obs, oldParent, newParent) -> {
			if (newParent != null) {
				prefWidthProperty().bind(
						Bindings.multiply(Bindings.selectDouble(newParent.boundsInParentProperty(), "width"), 0.7));
				prefHeightProperty().bind(Bindings.selectDouble(newParent.boundsInParentProperty(), "height"));
			} else {
				prefWidthProperty().unbind();
				prefHeightProperty().unbind();
			}
		});
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, radius, BorderWidths.DEFAULT)));
	}

	private Circle createCircle() {
		Circle circle = new Circle(5);
		circle.setFill(Color.web("#7E7E7E"));
		return circle;
	}
}