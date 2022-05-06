package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TimePane extends Pane {
    private Label timeLabel;
    private BorderPane lives;

    public TimePane() {
        timeLabel = new Label();
        this.setPrefSize(100, 720);
        this.setLayoutX(1280 / 2 - 50);
        this.setLayoutY(0);

        timeLabel.setText("TIME : 0");
        Font font = Font.font("Impact", FontWeight.NORMAL, 40);
        timeLabel.setFont(font);
        timeLabel.setLayoutX(0);

        timeLabel.setStyle("-fx-text-fill: #0C8647");

        Image heartImage = new Image("heart.png", 30, 30, false, true);
        lives = new BorderPane();
        lives.setPrefSize(300, 30);
        for (int i = 1; i <= 10; i++) {
            ImageView imageView = new ImageView(heartImage);
            imageView.setLayoutX(30 * i);
            imageView.setLayoutY(0);
            lives.getChildren().add(imageView);
        }
        lives.setLayoutY(635);
        lives.setLayoutX(-130);

        this.getChildren().add(lives);
        this.getChildren().add(timeLabel);
    }

    public void setTime() {
        timeLabel.setText("TIME : " + TimeThread.time);
    }

    public void removeLive() {
        Platform.runLater(() -> {
            lives.getChildren().remove(lives.getChildren().size() - 1);
        });
    }
}
