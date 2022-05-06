package Ducks;

import javafx.application.Platform;
import sample.DuckThread;
import sample.TimePane;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class DuckMovingThread implements Runnable {
    public static Vector<Duck> Ducks;
    private final int speed;
    private final DuckThread duckThread;
    private final TimePane timePane;
    private boolean moving;

    public DuckMovingThread(int speed, TimePane timePane, DuckThread duckThread) {
        Ducks = new Vector<>();
        this.duckThread = duckThread;
        this.speed = speed;
        this.timePane = timePane;
        this.moving = true;
    }

    @Override
    public void run() {
        while (moving) {
            try {
                for (int i = 0; i < Ducks.size(); i++) {
                    double index = Ducks.get(i).getLayoutX();
                    if (Ducks.get(i).getDuckDirection() == DuckDirection.LEFT) {
                        index++;
                        Ducks.get(i).setLayoutX(index);
                        if (Ducks.get(i).getLayoutX() > 1280) {
                            Ducks.get(i).setDuckDirection(null);
                            Ducks.remove(i);
                            Platform.runLater(() -> {
                                timePane.removeLive();
                                duckThread.removeLives();
                            });
                        }
                    } else {
                        index--;
                        Ducks.get(i).setLayoutX(index);
                        if (Ducks.get(i).getLayoutX() < -70) {
                            Ducks.get(i).setDuckDirection(null);
                            Ducks.remove(i);
                            Platform.runLater(() -> {
                                timePane.removeLive();
                                duckThread.removeLives();
                            });
                        }
                    }
                }
                if (duckThread.getLives() == 0) {
                    moving = false;
                }
                TimeUnit.MILLISECONDS.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
