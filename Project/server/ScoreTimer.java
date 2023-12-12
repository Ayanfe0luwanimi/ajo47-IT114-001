package Project.server;

import java.util.Timer;
import java.util.TimerTask;

public class ScoreTimer {
    private int secondsRemaining;
    private final Timer timer;

    public ScoreTimer(int durationInSeconds) {
        timer = new Timer();
        secondsRemaining = durationInSeconds;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                secondsRemaining--;
                if (secondsRemaining <= 0) {
                    timer.cancel();
                    secondsRemaining = 0;
                }
            }
        }, 1000, 1000);
    }

    public int getRemainingTime() {
        return secondsRemaining;
    }

    public static void main(String[] args) {
        ScoreTimer timer = new ScoreTimer(1800); // 30 minutes (30 minutes * 60 seconds)
        
        // To get the remaining time
        System.out.println("Time remaining: " + timer.getRemainingTime() + " seconds");
    }
}
