package Project.server;


public class ClientScore {
    private long clientId;
    private int score;

    public ClientScore(long clientId, int score) {
        this.clientId = clientId;
        this.score = score;
    }

    public long getClientId() {
        return clientId;
    }

    public int getScore() {
        return score;
    }
}