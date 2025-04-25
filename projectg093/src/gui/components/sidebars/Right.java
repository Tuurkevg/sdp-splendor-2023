package gui.components.sidebars;

import dto.SpelerDTO;
import gui.components.SpelersOverzichtComponent;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class Right extends AnchorPane {

        public Right(List<SpelerDTO> spelers) {
            setStyle("-fx-background-color: lightyellow;");
            SpelersOverzichtComponent spelersOverzichtComponent = new SpelersOverzichtComponent(spelers);
            getChildren().add(spelersOverzichtComponent);

            AnchorPane.setLeftAnchor(spelersOverzichtComponent, 0.0);
            AnchorPane.setRightAnchor(spelersOverzichtComponent, 0.0);
            AnchorPane.setTopAnchor(spelersOverzichtComponent, 0.0);
            AnchorPane.setBottomAnchor(spelersOverzichtComponent, 0.0);

//            visibleProperty().set(false);
        }
}
