package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.Scanner;

public class HighScoresPane extends Pane {
    ListView<String> scores = new ListView<>();

    public HighScoresPane() {
        this.setPrefSize(250,600);
        readFile();
        Button button = new Button("EXIT HIGHSCORES");
        button.setLayoutX((1280 - 250) / 2);
        button.setLayoutY(600);
        button.setOnAction(e -> this.setVisible(false));
        scores.setFocusTraversable(false);
        scores.setLayoutX((1280 - 250) / 2);
        scores.setLayoutY((720 - 450) / 2);
        scores.setPrefSize(250, 450);
        this.getChildren().add(button);
        this.getChildren().add(scores);
    }

    public void readFile() {
        scores.getItems().clear();
        try {
            File file = new File("highScores.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                /*
                listView adding :
                https://stackoverflow.com/questions/32700005/javafx-listview-add-and-edit-element
                 */
                String s = scanner.nextLine();
                scores.getItems().add(s);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
