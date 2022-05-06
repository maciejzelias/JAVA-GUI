package sample;

import Ducks.DuckMovingThread;
import Ducks.GreenDuck;
import Ducks.RedDuck;
import Ducks.YellowDuck;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

public class DuckThread implements Runnable {
    private Pane gamePane;
    private Difficulty difficulty;
    private int spawning;
    private int speed;
    private int lives;
    private DuckMovingThread duckMovingThread;
    private GameFrame gameFrame;

    public DuckThread(Pane gamePane, Difficulty difficulty, TimePane timePane, GameFrame gameFrame) {
        lives = 10;

        this.gameFrame = gameFrame;
        this.gamePane = gamePane;
        this.difficulty = difficulty;
        switch (difficulty) {
            case EASY -> {
                spawning = 1000;
                speed = 5;
            }
            case MEDIUM -> {
                spawning = 600;
                speed = 2;
            }
            case HARD -> {
                spawning = 400;
                speed = 1;
            }
        }

        duckMovingThread = new DuckMovingThread(speed, timePane, this);
        Thread duckMoving = new Thread(duckMovingThread);
        duckMoving.start();
    }

    @Override
    public void run() {
        while (lives > 0) {
            try {
                Platform.runLater(() -> {
                    int rand = (int) ((Math.random() * 99) + 1);
                    if (rand <= 33) {
                        YellowDuck yellowDuck = new YellowDuck(gamePane);
                        DuckMovingThread.Ducks.add(yellowDuck);
                        gamePane.getChildren().add(yellowDuck);
                    } else if (rand > 33 && rand <= 66) {
                        GreenDuck greenDuck = new GreenDuck(gamePane);
                        DuckMovingThread.Ducks.add(greenDuck);
                        gamePane.getChildren().add(greenDuck);
                    } else {
                        RedDuck redDuck = new RedDuck(gamePane);
                        DuckMovingThread.Ducks.add(redDuck);
                        gamePane.getChildren().add(redDuck);
                    }
                });
                Thread.sleep(spawning);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (lives == 0) {
            double scoreMnoznik = 0;
            switch (difficulty) {
                case EASY -> scoreMnoznik = 1;
                case MEDIUM -> scoreMnoznik = 1.5;
                case HARD -> scoreMnoznik = 2;
            }
            double score = scoreMnoznik * TimeThread.time;
            Platform.runLater(() -> {
                gameFrame.endGame(score);
            });
        }
    }

    public int getLives() {
        return lives;
    }

    public void removeLives() {
        --this.lives;
    }

}
