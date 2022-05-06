package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ButtonsPane extends VBox {
    private Button startButton;
    private Button highScoresButton;
    private Button exitButton;

    public ButtonsPane(HighScoresPane highScoresPane, DifficultyChoosePane difficultyChoosePane) {
        /**
         * buttons css styles found :
         * http://tutorials.jenkov.com/javafx/button.html
         * Image constructor:
         * Image(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth)
         */

        Font font = Font.font("Impact", FontWeight.NORMAL, 20);

        startButton = new Button("New Game");
        Image startimage = new Image("playicon.png", 30, 30, false, true);
        ImageView startImageView = new ImageView(startimage);
        startButton.setPrefSize(200, 100);
        startButton.setGraphic(startImageView);
        startButton.setFont(font);
        //startButton.setStyle("-fx-background-color: DARKGREEN; -fx-text-fill: WHITE; -fx-pref-width: 200;" +
        //         "-fx-pref-height: 100; -fx-border-color: WHITE; -fx-progress-color: GREEN");
        startButton.setOnAction(e -> {
            difficultyChoosePane.setVisible(true);
        });


        highScoresButton = new Button("High Scores");
        Image highScoreimage = new Image("highscoreicon.png", 30, 30, false, true);
        ImageView highScoreImageView = new ImageView(highScoreimage);
        highScoresButton.setGraphic(highScoreImageView);
        highScoresButton.setPrefSize(200, 100);
        highScoresButton.setFont(font);
        highScoresButton.setOnAction(e -> {
            highScoresPane.setVisible(true);
            highScoresPane.readFile();
        });


        exitButton = new Button("Exit");
        Image exitimage = new Image("exiticon.png", 30, 30, false, true);
        ImageView exitImageView = new ImageView(exitimage);
        exitButton.setGraphic(exitImageView);
        exitButton.setPrefSize(200, 100);
        exitButton.setFont(font);
        // closing app found at :
        // http://www.java2s.com/Tutorials/Java/JavaFX_How_to/Application/Exit_application.htm
        exitButton.setOnAction(e -> System.exit(0));


        getChildren().addAll(startButton, highScoresButton, exitButton);
        setAlignment(Pos.CENTER);
        setSpacing(50);
    }

    public Button getStartButton() {
        return startButton;
    }
}
