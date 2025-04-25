package gui;

import dto.OverzichtDTO;
import dto.SpelerDTO;
import gui.components.sidebars.*;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lk.vivoxalabs.customstage.CustomStage;
import lk.vivoxalabs.customstage.CustomStageBuilder;
import lk.vivoxalabs.customstage.tools.NavigationType;
import lk.vivoxalabs.customstage.tools.Style;
import mediator.SplendorMediator;

public class CustomSplendorGUI extends Stage {
    private static SplendorMediator mediator;
    private static Left gedekteStapelsAnchor;
    private static Top edelenAnchor;
    private static Bottom huidigeSpelerAnchor;
    private static Right spelerOverzichtAnchor;
    private static CustomStage stage;

    public CustomSplendorGUI() throws Exception {
        CustomSplendorGUI.mediator = new SplendorMediator(SplendorGUI.dc);
    }

    public static void refreshScreen() throws Exception {
        OverzichtDTO overzichtDTO = SplendorGUI.dc.geefOverzicht();
        start(overzichtDTO);
    }
    public static void start(OverzichtDTO overzichtDTO) throws Exception {

        // Create the sidebars
        gedekteStapelsAnchor = new Left();
        edelenAnchor = new Top(overzichtDTO.edeleDTOS());
        huidigeSpelerAnchor = new Bottom(overzichtDTO.spelerDTOS().stream().filter(SpelerDTO::isAanBeurt).toList().get(0));
        spelerOverzichtAnchor = new Right(overzichtDTO.spelerDTOS().stream().filter(spelerDTO -> !spelerDTO.isAanBeurt()).toList());

        // Create the custom stage
        stage = new CustomStageBuilder()
                .setWindowTitle("CustomStage example")
                .setWindowColor("rgb(34,54,122)")
                .setNavigationPane(Style.DYNAMIC, NavigationType.LEFT, gedekteStapelsAnchor, 50, 0, true)
                .setNavigationPane(Style.DYNAMIC, NavigationType.TOP, edelenAnchor, 0, 300, true)
                .setNavigationPane(Style.DYNAMIC, NavigationType.BOTTOM, huidigeSpelerAnchor, 0, 300, true)
                .setNavigationPane(Style.DYNAMIC, NavigationType.RIGHT, spelerOverzichtAnchor, 50, 0, true)
                .build();
        stage.show();
        stage.dynamicDrawerEvent(NavigationType.LEFT);
        stage.dynamicDrawerEvent(NavigationType.TOP);
        stage.dynamicDrawerEvent(NavigationType.BOTTOM);
        stage.dynamicDrawerEvent(NavigationType.RIGHT);
        stage.changeScene(generateContent(overzichtDTO));
    }

    public static BorderPane generateContent(OverzichtDTO overzichtDTO) {
        // Create the root pane
        BorderPane rootBox = new BorderPane();

        // Create the left and top rectangles
        HBox leftRB = new HBox();
        VBox topRB = new VBox();
        Rectangle rectLeft = new Rectangle(gedekteStapelsAnchor.getWidth() *0.5, stage.getHeight() * 0.9 - 100);
        Rectangle rectTop = new Rectangle(stage.getWidth(), edelenAnchor.getHeight() * 0.3);

        // Set up the center pane
        Center ontwikkelingsKaarten = new Center(overzichtDTO.ontwikkelingskaartDTOS());
        rootBox.setCenter(ontwikkelingsKaarten);
        AnchorPane.setTopAnchor(ontwikkelingsKaarten, 0.0);
        AnchorPane.setLeftAnchor(ontwikkelingsKaarten, 0.0);

        // Set up the bottom and right rectangles
        Rectangle bottomRect = new Rectangle(stage.getWidth(), 100);
        bottomRect.widthProperty().bind(stage.widthProperty());
        rootBox.setBottom(bottomRect);
        bottomRect.setFill(Color.TRANSPARENT);
        AnchorPane.setRightAnchor(bottomRect, 0.0);
        AnchorPane.setBottomAnchor(bottomRect, 0.0);

        Rectangle rightRect = new Rectangle(100, 100);
        rightRect.heightProperty().bind(Bindings.subtract(Bindings.multiply(stage.heightProperty(), 0.9), 100));
        rootBox.setRight(rightRect);
        rightRect.setFill(Color.TRANSPARENT);
        AnchorPane.setRightAnchor(rightRect, 0.0);
        AnchorPane.setBottomAnchor(rightRect, 0.0);

        // Add the rectangles to the left and top panes
        rectLeft.widthProperty().bind(Bindings.multiply(gedekteStapelsAnchor.widthProperty(), 0.5));
        rectLeft.heightProperty().bind(Bindings.subtract(Bindings.multiply(stage.heightProperty(), 0.9), 100));
        rectLeft.setFill(Color.TRANSPARENT);
        leftRB.getChildren().add(rectLeft);
        AnchorPane.setTopAnchor(rectLeft, 0.0);
        AnchorPane.setLeftAnchor(rectLeft, 0.0);

        rectTop.widthProperty().bind(stage.widthProperty());
        rectTop.heightProperty().bind(Bindings.multiply(edelenAnchor.heightProperty(), 0.3));
        rectTop.setFill(Color.TRANSPARENT);
        topRB.getChildren().add(rectTop);
        AnchorPane.setTopAnchor(rectTop, 0.0);
        AnchorPane.setLeftAnchor(rectTop, 0.0);

        // Add the panes to the root pane
        rootBox.setLeft(leftRB);
        rootBox.setTop(topRB);
        rootBox.toFront();

        // Set up the mouse events for the rectangles
        rectLeft.setOnMouseEntered(event -> {
            gedekteStapelsAnchor.visibleProperty().set(true);
            stage.dynamicDrawerEvent(NavigationType.LEFT);
            event.consume();
        });

        gedekteStapelsAnchor.setOnMouseExited(event -> {
            stage.dynamicDrawerEvent(NavigationType.LEFT);
            event.consume();
        });

        rectTop.setOnMouseEntered(event -> {
            edelenAnchor.visibleProperty().set(true);
            stage.dynamicDrawerEvent(NavigationType.TOP);
            event.consume();
        });

        edelenAnchor.setOnMouseExited(event -> {
            stage.dynamicDrawerEvent(NavigationType.TOP);
            event.consume();
        });

        bottomRect.setOnMouseEntered(event -> {
            huidigeSpelerAnchor.visibleProperty().set(true);
            stage.dynamicDrawerEvent(NavigationType.BOTTOM);
            event.consume();
        });

        huidigeSpelerAnchor.setOnMouseExited(event -> {
            stage.dynamicDrawerEvent(NavigationType.BOTTOM);
            event.consume();
        });

        rightRect.setOnMouseEntered(event -> {
            spelerOverzichtAnchor.visibleProperty().set(true);
            stage.dynamicDrawerEvent(NavigationType.RIGHT);
            event.consume();
        });

        spelerOverzichtAnchor.setOnMouseExited(event -> {
            stage.dynamicDrawerEvent(NavigationType.RIGHT);
            event.consume();
        });

        // Change the scene to the root pane
        return rootBox;
    }
}