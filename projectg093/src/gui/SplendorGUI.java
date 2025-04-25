package gui;

import domein.DomeinController;
import gui.views.SpelSchermView;
import gui.views.SpelersToevoegenView;
import gui.views.TalenView;
import gui.views.WelkomView;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class SplendorGUI extends Application {
	public static DomeinController dc;
	public static String css;
	public static SpelSchermView spelSchermView;
	private Scene scene;

//    public void init() {
//        this.dc = new DomeinController();
//    }

	public static void startGUI() {
		launch();
	}

	public void start(Stage primaryStage) throws Exception {
		TalenView talenView = new TalenView(primaryStage);
		css = this.getClass().getResource("../utils/stylesheet.css").toExternalForm();
		talenView.getStylesheets().add(css);
//		Button enButton = talenView.toonEnButton();
//		Button nlButton = talenView.toonNLButton();
//
//		enButton.setOnAction(event -> maakWelkomView(primaryStage, Locale.UK));
//		nlButton.setOnAction(event -> maakWelkomView(primaryStage, new Locale("nl", "BE")));

		ComboBox<String> comboBoxTaal = talenView.toonComboBoxTaal();
		comboBoxTaal.setValue("English");
		Button bevestigBtn = talenView.toonBevestigBtn();
		bevestigBtn.setOnAction(e -> {
			String geselecteerdeTaal = comboBoxTaal.getValue();
			Locale locale = switch (geselecteerdeTaal) {
			case "Nederlands" -> new Locale("nl", "BE");
			case "English" -> Locale.UK;
			case "Русский" -> new Locale("ru", "RU");
			default -> Locale.getDefault();
			};
			maakWelkomView(primaryStage, locale);
		});



		scene = new Scene(talenView, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Splendor Language");
		primaryStage.show();
	}

	private void maakWelkomView(Stage primaryStage, Locale l) {
		Locale.setDefault(l);
		ResourceBundle rb = ResourceBundle.getBundle("utils.language");

		WelkomView welkomView = new WelkomView();
		welkomView.getStylesheets().add(css);
		Button startKnop = welkomView.getStartButton();
		startKnop.setOnAction(event -> {
			SpelersToevoegenView spelersToevoegenView = new SpelersToevoegenView(primaryStage);
			spelersToevoegenView.getStylesheets().add(css);

			scene = new Scene(spelersToevoegenView, 1200, 600);
			primaryStage.setScene(scene);

			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();

			double centerX = bounds.getMinX() + (bounds.getWidth() - scene.getWidth()) / 2.0;
			double centerY = bounds.getMinY() + (bounds.getHeight() - scene.getHeight()) / 2.0;

			primaryStage.setX(centerX);
			primaryStage.setY(centerY);
//			primaryStage.setMaximized(true);
			primaryStage.setTitle(rb.getString("titleAddPlayers"));
			primaryStage.show();
		});

		scene = new Scene(welkomView, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle(rb.getString("titleWelcomeView"));
		primaryStage.show();
	}

	public void maakSpelSchermView(Stage primaryStage, Locale l) {
		Locale.setDefault(l);
		ResourceBundle rb = ResourceBundle.getBundle("utils.language");

		spelSchermView = new SpelSchermView(primaryStage);

		scene = new Scene(spelSchermView, 1200, 600);
		primaryStage.setScene(scene);

		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();

		double centerX = bounds.getMinX() + (bounds.getWidth() - scene.getWidth()) / 2.0;
		double centerY = bounds.getMinY() + (bounds.getHeight() - scene.getHeight()) / 2.0;

		primaryStage.setX(centerX);
		primaryStage.setY(centerY);
	}

//    public static void main(String[] args) {
//        launch(args);
//    }
}
