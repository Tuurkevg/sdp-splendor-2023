package gui.components;

import dto.OverzichtDTO;
import dto.SpelerDTO;
import gui.components.sidebars.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class SpelComponent extends BorderPane {
    public SpelComponent(OverzichtDTO overzichtDTO) {
        Left gedekteStapelsAnchor = new Left();
        Top edelenAnchor = new Top(overzichtDTO.edeleDTOS());
        Bottom huidigeSpelerAnchor = new Bottom(overzichtDTO.spelerDTOS().stream().filter(SpelerDTO::isAanBeurt).toList().get(0));
        Right spelerOverzichtAnchor = new Right(overzichtDTO.spelerDTOS().stream().filter(spelerDTO -> !spelerDTO.isAanBeurt()).toList());
        Center ontwikkelingsKaarten = new Center(overzichtDTO.ontwikkelingskaartDTOS());

        gedekteStapelsAnchor.prefWidthProperty().bind(widthProperty().multiply(0.2));
        edelenAnchor.prefHeightProperty().bind(heightProperty().multiply(0.23));

        AnchorPane.setBottomAnchor(huidigeSpelerAnchor, 0.0);
//        AnchorPane.setBottomAnchor(edelenAnchor, 0.0);

        AnchorPane.setBottomAnchor(ontwikkelingsKaarten, 0.0);
        AnchorPane.setTopAnchor(ontwikkelingsKaarten, 0.0);
        AnchorPane.setLeftAnchor(ontwikkelingsKaarten, 0.0);
        AnchorPane.setRightAnchor(ontwikkelingsKaarten, 0.0);



        setLeft(gedekteStapelsAnchor);
        setTop(edelenAnchor);
        setBottom(huidigeSpelerAnchor);
        setRight(spelerOverzichtAnchor);
        setCenter(ontwikkelingsKaarten);
    }
}
