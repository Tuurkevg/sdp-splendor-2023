package gui.components.sidebars;

import dto.SpelerDTO;
import gui.components.SpelerAanBeurtComponent;
import javafx.scene.layout.AnchorPane;

public class Bottom extends AnchorPane {

    public Bottom(SpelerDTO spelerDTO) {
        setStyle("-fx-background-color: lightpink;");
//         visibleProperty().set(false);

        SpelerAanBeurtComponent spelerAanBeurtComponent = new SpelerAanBeurtComponent(spelerDTO);
        getChildren().add(spelerAanBeurtComponent);


        AnchorPane.setLeftAnchor(spelerAanBeurtComponent, 0.0);
        AnchorPane.setRightAnchor(spelerAanBeurtComponent, 0.0);
        AnchorPane.setTopAnchor(spelerAanBeurtComponent, 0.0);
        AnchorPane.setBottomAnchor(spelerAanBeurtComponent, 0.0);


    }
}
