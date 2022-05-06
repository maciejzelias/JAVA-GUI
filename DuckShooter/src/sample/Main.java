package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

    private final BorderPane borderPane = new BorderPane();

    private static final int width = 1280;
    private static final int height = 720;

    private Stage mainStage;

    private ButtonsPane buttonsPane;
    private HighScoresPane highScoresPane;
    private DifficultyChoosePane difficultyChoosePane;
    private TextInputDialog textInputDialog;
    private String nickname;

    @Override
    public void start(Stage primaryStage) {

        mainStage = primaryStage;

        highScoresPane = new HighScoresPane();
        highScoresPane.setVisible(false);

        borderPane.setPrefSize(1280, 720);
        /**
         * Insets : https://docs.oracle.com/javase/8/javafx/api/javafx/geometry/Insets.html
         * Insets( top,  right, bottom, left )
         */
        borderPane.setPadding(new Insets(40, 20, 10, 20));

        //ADDING BACKGROUND TO MENU
        /**
         * JavaFX image constructor found at :
         * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/image/Image.html
         * Image(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth, boolean backgroundLoading)
         */
        Image backgroundImage = new Image("background.jpg", 1280, 720, false, true, true);
        ImageView imageView = new ImageView(backgroundImage);
        borderPane.getChildren().add(imageView);


        /**
         * input dialog :
         * https://www.geeksforgeeks.org/javafx-textinputdialog/
         */
        //ADDING ENTERING NICKNAME :
        textInputDialog = new TextInputDialog("nickname");
        textInputDialog.setTitle("ENTER YOUR NICKNAME");
        textInputDialog.setHeaderText("ENTER YOUR NICKNAME");

        //ADDING TITLE
        TitlePane titlePane = new TitlePane();
        BorderPane.setAlignment(titlePane, Pos.TOP_CENTER);
        borderPane.setTop(titlePane);

        //CREATING DIFFICULTY CHOOSE MENU
        difficultyChoosePane = new DifficultyChoosePane();
        difficultyChoosePane.setVisible(false);

        //ADDING BUTTONS
        buttonsPane = new ButtonsPane(highScoresPane, difficultyChoosePane);
        borderPane.setCenter(buttonsPane);



        borderPane.getChildren().add(highScoresPane);
        borderPane.getChildren().add(difficultyChoosePane);

        Scene scene = new Scene(borderPane, width, height);
        Image icon = new Image("icon.png");

        primaryStage.setOnCloseRequest(e -> {
            System.exit(0);
        });
        primaryStage.getIcons().add(icon);
        primaryStage.setResizable(false);
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setTitle("Duck shooter v 1.0");
        primaryStage.setScene(scene);
        event();
        primaryStage.show();
    }

    /**
     * solution to opening another app by button found at :
     * https://stackoverflow.com/questions/25873769/launch-javafx-application-from-another-class/36805921
     */
    public void event() {
        difficultyChoosePane.getEasy().setOnAction(actionEvent -> {
            Stage stage = new Stage();
            textInputDialog.showAndWait();
            nickname = textInputDialog.getEditor().getText();
            new GameFrame(mainStage, Difficulty.EASY, nickname).start(stage);
        });
        difficultyChoosePane.getMedium().setOnAction(actionEvent -> {
            Stage stage = new Stage();
            textInputDialog.showAndWait();
            nickname = textInputDialog.getEditor().getText();
            new GameFrame(mainStage, Difficulty.MEDIUM, nickname).start(stage);
        });
        difficultyChoosePane.getHard().setOnAction(actionEvent -> {
            Stage stage = new Stage();
            textInputDialog.showAndWait();
            nickname = textInputDialog.getEditor().getText();
            new GameFrame(mainStage, Difficulty.HARD, nickname).start(stage);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
