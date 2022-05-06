package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class DifficultyChoosePane extends Pane {
    private final Button easy;
    private final Button medium;
    private final Button hard;

    public DifficultyChoosePane() {
        this.setPrefSize(400, 600);
        easy = new Button("EASY");
        medium = new Button("MEDIUM");
        hard = new Button("HARD");

        Button comeback = new Button("CANCEL");
        comeback.setLayoutX(750);
        comeback.setLayoutY(250);
        comeback.setOnAction(e -> this.setVisible(false));

        easy.setLayoutX(750);
        easy.setLayoutY(200);
        medium.setLayoutX(800);
        medium.setLayoutY(200);
        hard.setLayoutX(870);
        hard.setLayoutY(200);

        this.getChildren().add(comeback);
        this.getChildren().add(easy);
        this.getChildren().add(medium);
        this.getChildren().add(hard);
    }

    public Button getEasy() {
        return easy;
    }

    public Button getMedium() {
        return medium;
    }

    public Button getHard() {
        return hard;
    }
}
