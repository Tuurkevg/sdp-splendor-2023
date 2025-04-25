package gui.components;

import dto.OntwikkelingskaartDTO;
import enums.EdelsteenType;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Pair;

public class OntwikkelingsKaartComponent extends VBox {
    private OntwikkelingskaartDTO ontwikkelingskaart;

    public OntwikkelingskaartDTO getOntwikkelingskaart() {
        return ontwikkelingskaart;
    }

    public OntwikkelingsKaartComponent(OntwikkelingskaartDTO ontwikkelingskaart) {
        if (ontwikkelingskaart.niveau() < 1 || ontwikkelingskaart.niveau() > 3) {
            throw new IllegalArgumentException("Niveau moet tussen 1 en 3 liggen.");
        }
        this.ontwikkelingskaart = ontwikkelingskaart;

        String kleur = switch (ontwikkelingskaart.niveau()) {
            case 1 -> "#287233"; // Groen
            case 2 -> "#FAF233"; // Geel
            default -> "#1E2DB1"; // Blauw
        };

        BorderPane ontwikkelingskaartContainer = new BorderPane();
        ontwikkelingskaartContainer.setTop(createTopContainer(ontwikkelingskaart, kleur));
        ontwikkelingskaartContainer.setLeft(createLeftContainer(ontwikkelingskaart));
        this.getChildren().add(ontwikkelingskaartContainer);
        setBackground(new Background(new BackgroundFill(Color.web("#D9D9D9"), new CornerRadii(10), new Insets(0))));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));

        // Bind width and height to the parent
        parentProperty().addListener((obs, oldParent, newParent) -> {
            if (newParent != null) {
                prefWidthProperty().bind(Bindings.multiply(Bindings.selectDouble(newParent.boundsInParentProperty(), "width"), 0.5));
                prefHeightProperty().bind(Bindings.selectDouble(newParent.boundsInParentProperty(), "height"));
            } else {
                prefWidthProperty().unbind();
                prefHeightProperty().unbind();
            }
        });
    }

    private HBox createTopContainer(OntwikkelingskaartDTO ontwikkelingskaart, String kleur) {
        HBox topContainer = new HBox();
        topContainer.setAlignment(Pos.TOP_LEFT);
        topContainer.setPrefHeight(30);
        topContainer.setBackground(new Background(new BackgroundFill(Color.web(kleur), new CornerRadii(10, 10,  0, 0, false), new Insets(0))));
        topContainer.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10, 10, 0 ,0, false), BorderWidths.DEFAULT)));

        Text prestigePuntenText;
        if (ontwikkelingskaart.prestigePunten() != 0) {
            prestigePuntenText = new Text("%d".formatted(ontwikkelingskaart.prestigePunten()));
        } else {
            prestigePuntenText = new Text(" ");
        }

        Font lemonadaFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Lemonada-SemiBold.ttf"), 20);
        prestigePuntenText.setFont(lemonadaFont);
        prestigePuntenText.setFill(Color.WHITE);
        prestigePuntenText.setStroke(Color.BLACK);
        prestigePuntenText.setStrokeWidth(0.5);

        AnchorPane left = new AnchorPane();
        left.getChildren().add(prestigePuntenText);
        HBox.setHgrow(left, Priority.ALWAYS);
        AnchorPane.setLeftAnchor(prestigePuntenText, 10.0);
        AnchorPane.setTopAnchor(prestigePuntenText, 0.0);
        topContainer.getChildren().add(left);

        AnchorPane right = new AnchorPane();
        Circle cirkel = new Circle(12);
        cirkel.setFill(Color.web(ontwikkelingskaart.bonus().getKleurCode()));
        cirkel.setStroke(Color.BLACK);
        cirkel.setStrokeWidth(0.5);
        right.getChildren().add(cirkel);
        AnchorPane.setRightAnchor(cirkel, 10.0);
        AnchorPane.setTopAnchor(cirkel, 8.0);
        topContainer.getChildren().add(right);

        return topContainer;
    }

    private VBox createLeftContainer(OntwikkelingskaartDTO ontwikkelingskaart) {
        VBox kaartLeft = new VBox();
        kaartLeft.getChildren().add(createCirclesGrid(ontwikkelingskaart));
        kaartLeft.getChildren().add(createCircularPane());
        AnchorPane.setLeftAnchor(kaartLeft, 0.0);
        AnchorPane.setBottomAnchor(kaartLeft, 0.0);
        return kaartLeft;
    }

    private GridPane createCirclesGrid(OntwikkelingskaartDTO ontwikkelingskaart) {
        GridPane circlesGrid = new GridPane();
        circlesGrid.setHgap(1); // Horizontal gap between grid cells
        circlesGrid.setVgap(-5); // Vertical gap between grid cells

        int itemCount = ontwikkelingskaart.aantalFiches().size();
        for (int i = 0; i < itemCount; i++) {
            Pair<EdelsteenType, Integer> aantalFich = ontwikkelingskaart.aantalFiches().get(i);

            Label label = new Label("%d".formatted(aantalFich.getValue()));
            if (aantalFich.getKey() == EdelsteenType.ZWART || aantalFich.getKey() == EdelsteenType.BLAUW) {
                label.setTextFill(Color.WHITE);
            }
            label.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Lemonada-SemiBold.ttf"), 15));

            Circle circle = new Circle(12, Color.web(aantalFich.getKey().getKleurCode()));
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(0.5);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(circle, label);
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPadding(new Insets(0, 0, 0, 5));

            circlesGrid.add(stackPane, 0, i);
        }

        AnchorPane.setLeftAnchor(circlesGrid, 0.0);
        AnchorPane.setBottomAnchor(circlesGrid, 0.0);
        return circlesGrid;
    }

    private StackPane createCircularPane() {
        StackPane circularPane = new StackPane();
        AnchorPane.setLeftAnchor(circularPane, 0.0);
        AnchorPane.setBottomAnchor(circularPane, 0.0);
        return circularPane;
    }
}