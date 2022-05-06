package Ducks;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GreenDuck extends Duck {
    private int lives;
    private Image imageLeftDuck;
    private Image imageRightDuck;
    private Image heartImage;
    private ImageView imageView;
    private DuckDirection duckDirection;

    public GreenDuck(Pane pane) {
        this.lives = 1;
        this.setPrefSize(100, 100);
        this.setLayoutX(40);
        this.setLayoutY(120);



        heartImage = new Image("heart.png", 10, 10, false, true);
        Pane healthBox = new BorderPane();
        healthBox.setPrefSize(100, 30);
        for (int i = 1; i <= lives; i++) {
            ImageView imageView = new ImageView(heartImage);
            imageView.setLayoutX(10 * i);
            imageView.setLayoutY(0);
            healthBox.getChildren().add(imageView);
        }

        //GETTING RANDOM DIRECTION AND HEIGHT OF DUCK
        int random = (int) ((Math.random() * 99) + 1);
        int randomHeight = (int) ((Math.random() * 450) + 70);
        if (random < 50) {
            duckDirection = DuckDirection.LEFT;
            setupLeftDuck();
            this.setLayoutY(randomHeight);
            this.setLayoutX(0);
        } else {
            duckDirection = DuckDirection.RIGHT;
            setupRightDuck();
            this.setLayoutY(randomHeight);
            this.setLayoutX(1200);
        }

        imageView.setLayoutY(20);

        imageView.setFitHeight(70);
        imageView.setFitWidth(70);
        /*
        https://www.jackrutorial.com/2020/04/how-to-handle-mouse-click-events-in-javafx.html
         */
        imageView.setOnMouseClicked(mouseEvent -> {
            healthBox.getChildren().remove(0);
            lives--;
            if (lives == 0) {
                DuckMovingThread.Ducks.remove(this);
                Platform.runLater(() -> pane.getChildren().remove(this));
            }
        });

        this.getChildren().add(healthBox);
        this.getChildren().add(imageView);
    }

    private void setupLeftDuck() {
        imageLeftDuck = new Image("greenDuckLeft.png");
        imageView = new ImageView(imageLeftDuck);
    }

    private void setupRightDuck() {
        imageRightDuck = new Image("greenDuckRight.png");
        imageView = new ImageView(imageRightDuck);
    }

    @Override
    public DuckDirection getDuckDirection() {
        return duckDirection;
    }
}

