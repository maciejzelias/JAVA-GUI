package sample;

import javafx.application.Platform;

public class TimeThread implements Runnable {
    public static int time;
    private volatile boolean exit = false;
    private TimePane timePane;

    public TimeThread(TimePane timePane) {
        this.time = 0;
        this.timePane=timePane;
    }

    @Override
    public void run() {
        while (!exit) {
            try {
                Platform.runLater(() -> {
                    timePane.setTime();
                    System.out.println(time);
                });
                time++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.exit = true;
    }
}
