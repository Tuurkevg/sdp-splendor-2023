package gui.views;

import gui.SplendorGUI;
import gui.components.SpelComponent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mediator.SplendorMediator;

public class SpelSchermView extends StackPane {
    public static SplendorMediator mediator;
    public SpelComponent spelComponent;

    public SpelSchermView(Stage primaryStage) {
        SpelSchermView.mediator = new SplendorMediator(SplendorGUI.dc);
        spelComponent = new SpelComponent(SplendorGUI.dc.geefOverzicht());
        getChildren().add(spelComponent);
    }

    public void update() {
        getChildren().remove(spelComponent);
    	spelComponent = new SpelComponent(SplendorGUI.dc.geefOverzicht());
        getChildren().add(spelComponent);
    }

}
