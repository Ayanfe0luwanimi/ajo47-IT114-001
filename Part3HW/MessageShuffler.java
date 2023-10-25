import java.util.Random;

public class MessageShuffler {
    private boolean isShufflerActive = false;
    private final Random random = new Random();

    public void activateShuffler() {
        isShufflerActive = true;
    }

    public boolean isShufflerActive() {
        return isShufflerActive;
    }

    public String shuffleMessage(String message) {
        if (isShufflerActive) {
            char[] chars = message.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int j = random.nextInt(chars.length);
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
            }
            return new String(chars);
        }
        return message; // Shuffler is not active, return the message as it is.
    }
}
