import java.util.Random;

public class NumberGuesser {
    private int hiddenNumber;
    private boolean isGameActive = false;
    private long hiddenNumberClientId;
    private final Random random = new Random();

    public void activateGame(long clientId) {
        if (!isGameActive) {
            hiddenNumber = random.nextInt(100); // You can change the range as needed
            hiddenNumberClientId = clientId;
            isGameActive = true;
        }
    }

    public boolean isGameActive() {
        return isGameActive;
    }

    public long getHiddenNumberClientId() {
        return hiddenNumberClientId;
    }

    public String guessNumber(long clientId, int guess) {
        if (isGameActive) {
            if (guess == hiddenNumber) {
                isGameActive = false;
                return "correct";
            } else {
                return "not correct";
            }
        }
        return "The game is not active.";
    }

    public void deactivateGame() {
        isGameActive = false;
    }
}
