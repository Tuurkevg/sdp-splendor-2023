package gui.components.sidebars;

import dto.EdeleDTO;
import gui.SplendorGUI;
import gui.components.EdeleComponent;
import gui.views.SpelSchermView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.List;

public class Top extends AnchorPane {
    public Top(List<EdeleDTO> edelen) {
        GridPane edelenGrid = new GridPane();
        edelenGrid.add(new GridPane(), 0, 0);
//        edelenGrid.add(new GridPane(), 0, 2);
//        edelenGrid.setGridLinesVisible(true);
        for (int i = 1; i <= edelen.size(); i++) {
            EdeleComponent component = new EdeleComponent(edelen.get(i - 1));
            edelenGrid.add(component, i, 1);
            component.setOnMouseClicked(e -> {
                SpelSchermView.mediator.kiesEdele(component.getEdeleDTO());
                try {
                    SplendorGUI.spelSchermView.update();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
//            GridPane.setHalignment(component, HPos.CENTER);
//            GridPane.setValignment(component, VPos.CENTER);
        }
        edelenGrid.add(new GridPane(), 6, 0);
        edelenGrid.setVgap(30);
        edelenGrid.setHgap(30);

        AnchorPane.setLeftAnchor(edelenGrid, 0.0);
        AnchorPane.setRightAnchor(edelenGrid, 0.0);
//        AnchorPane.setTopAnchor(edelenGrid, 0.0);
        AnchorPane.setBottomAnchor(edelenGrid, 0.0);

        getChildren().add(edelenGrid);


        setStyle("-fx-background-color: lightgreen;");
//        setVisible(false);
    }
}
