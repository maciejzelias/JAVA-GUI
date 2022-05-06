package sample;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class GameFrame extends Application {

    Stage mainStage;
    TimeThread timeThread;
    DuckThread gameThread;
    Difficulty difficulty;
    Stage gameStage;
    String nickname;

    public GameFrame(Stage mainstage, Difficulty difficulty, String nickname) {
        this.nickname = nickname;
        this.mainStage = mainstage;
        this.difficulty = difficulty;
    }

    public void start(Stage stage) {
        this.gameStage = stage;
        Pane pane = new Pane();
        Image backgroundImage = new Image("backgroundGame.jpg", 1280, 720, false, true, true);
        ImageView imageView = new ImageView(backgroundImage);
        pane.getChildren().add(imageView);

        TimePane timePane = new TimePane();

        timeThread = new TimeThread(timePane);
        Thread timeController = new Thread(timeThread);
        timeController.start();
        pane.getChildren().add(timePane);

        gameThread = new DuckThread(pane, difficulty, timePane, this);
        Thread gameController = new Thread(gameThread);
        gameController.start();

        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        stage.setResizable(false);
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.setTitle("Duck shooter v 1.0");

        Scene gameScene = new Scene(pane, 280, 450);
        closingByKeyCombination(gameScene);

        //adding crosshair on mouse cursor
        //Image cursorImage = new Image("crosshair.png");
        gameScene.setCursor(Cursor.CROSSHAIR);
        stage.setScene(gameScene);
        stage.show();

        mainStage.close();
        /*
        showing menu after closing game.
        https://www.tabnine.com/code/java/methods/javafx.stage.Stage/setOnCloseRequest
         */
        stage.setOnCloseRequest(event -> {
            mainStage.show();
            stage.close();
            timeThread.stop();
        });
    }

    public void endGame(double score) {
        mainStage.show();
        gameStage.close();
        timeThread.stop();
        writeScoresToFile(score,nickname);
    }

    private void writeScoresToFile(double score, String nickname) {
        try {
            FileWriter fileWriter = new FileWriter("highScores.txt",true);
            fileWriter.write("NICKNAME : " + nickname + "   SCORE: " + score + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void closingByKeyCombination(Scene scene) {
                /*
        shortcut combination comeback to main menu found at :
        https://stackoverflow.com/questions/25397742/javafx-keyboard-event-shortcut-key
        https://stackoverflow.com/questions/29064225/how-to-create-a-javafx-keycombination-with-three-or-more-keys
        and keycodeCombination :
        https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/KeyCodeCombination.html
         */

        scene.getAccelerators().put(KeyCombination.keyCombination("CTRL+SHIFT+Q"),
                () -> {
                    mainStage.show();
                    gameStage.close();
                    timeThread.stop();
                });
    }

}
