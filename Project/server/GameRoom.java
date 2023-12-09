package Project.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import Project.client.Client;
import Project.common.Constants;
import Project.common.Payload;
import Project.common.Phase;
import Project.common.TimedEvent;
import Project.common.QuestionDetails;
public class GameRoom extends Room {
    Phase currentPhase = Phase.READY;
    private static Logger logger = Logger.getLogger(GameRoom.class.getName());
    private TimedEvent readyTimer = null;
    private ConcurrentHashMap<ServerPlayer, Integer> guessesPerPlayer = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Long, ServerPlayer> players = new ConcurrentHashMap<Long, ServerPlayer>();
    private List<QuestionDetails> questions;
    private List<List<String>> potentialAnswers;
    private List<String> correctAnswers; // New list for correct answers
    boolean gameover=false;
    private TimedEvent sessionTimer;
    private QuestionDetails currentDisplayedQuestion;
    private Map<ServerPlayer, Integer> timeOfGuess = new ConcurrentHashMap<>();
    private String currentQuestionAnswer; // Variable to store the current question's answer


    private int totalGuesses = 0;
    private  int MAX_GUESSES = 1;
    String randquestion;

    private static ScheduledExecutorService executor;
    private static int remainingTime = 30;

    public void startCountdown() {
        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            if (remainingTime > 0) {       
                remainingTime--;
            } else {
                stopCountdown();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }


    public static void stopCountdown() {
        if (executor != null) {
            executor.shutdownNow();
            remainingTime = 0;
        }
    }

    public static int getRemainingTime() {
        return remainingTime;
    }




    public GameRoom(String name) {
        super(name);
    }

    public boolean validateAnswer(String correctAnswer, String userAnswer) {
        return userAnswer.equalsIgnoreCase(correctAnswer);
    }

    @Override
    protected void addClient(ServerThread client) {
        logger.info("Adding client as player");
        players.computeIfAbsent(client.getClientId(), id -> {
            ServerPlayer player = new ServerPlayer(client);
            super.addClient(client);
            logger.info(String.format("Total clients %s", clients.size()));
            return player;
        });
    }

    protected void trackmessages(){

    }

    protected void setReady(ServerThread client) {
        logger.info("Ready check triggered");
        if (currentPhase != Phase.READY) {
            logger.warning(String.format("readyCheck() incorrect phase: %s", Phase.READY.name()));
            return;
        }
        if (readyTimer == null) {
            sendMessage(null, "Ready Check Initiated, 30 seconds to join");
            readyTimer = new TimedEvent(30, () -> {
                readyTimer = null;
                readyCheck(true);
            });
        }
        players.values().stream().filter(p -> p.getClient().getClientId() == client.getClientId()).findFirst()
                .ifPresent(p -> {
                    p.setReady(true);
                    logger.info(String.format("Marked player %s[%s] as ready", p.getClient().getClientName(), p
                            .getClient().getClientId()));
                    syncReadyStatus(p.getClient().getClientId());
                });
        readyCheck(false);
    
        // Reset session timer upon starting a new session
        if (sessionTimer != null) {
            sessionTimer.cancel();
            sessionTimer = null;
        }
    }
    
    

    public void receiveAnswerFromClient(ServerThread client, Payload answer) {
        String newAnswer = answer.getMessage();
    
        // Generating a unique ID based on player's name
        ServerPlayer currentPlayer = players.get(new ServerPlayer(client).getClient().getClientId());
    
        if (currentPlayer != null) {
            if (!hasPlayerMadeGuess(currentPlayer)) {
                int remainingTime = timedEvent.getRemainingTime();
                timeOfGuess.put(currentPlayer, remainingTime);
    
                if (!hasPlayerReachedMaxGuesses(currentPlayer)) {
                    String currentQuestion = randquestion;
    
                    if (currentQuestion != null) {
                        String correctAnswer = currentQuestionAnswer;
                            //ucid: ajo47
                            //date: 9/12/2023
                            /*marks are awarded based on the minutes remaining for the game to end.
                            incorrect responses are however awarded zero. This informstion is stored in 
                            the timeofguess list object
                            */
    
                        if (validateAnswer(correctAnswer, newAnswer)) {
                            sendMessage(null, "Successfully submited your choice,");
                            timeOfGuess.put(currentPlayer, getRemainingTime());
                        } else {
                            sendMessage(null, "Successfully submited your choice");
                            timeOfGuess.put(currentPlayer, 0);
                        }
                        incrementGuessCount(currentPlayer);
                        checkGameOver();
                    } else {
                        sendMessage(null, "No question found");
                    }
                } else {
                    sendMessage(null, "You have reached the maximum number of guesses.");
                }
            } else {
                sendMessage(null, "You have already made a guess.");
            }
    
            // Check if all players have made a guess after the current player's turn
            if (allPlayersMadeGuess()) {
                resetSession();
            }
        }
    }
    /*ucid: ajo47
    date: 9/12/2023
    checks if all players have made a guess/submission. If this method returns true, the resetsession
    method is invoked
    */

    private boolean allPlayersMadeGuess() {
        return guessesPerPlayer.size() == players.size();
    }
    
    
    
    
    
    
    
    
    

    private void incrementGuessCount(ServerPlayer currentPlayer) {
        guessesPerPlayer.put(currentPlayer, guessesPerPlayer.getOrDefault(currentPlayer, 0) + 1);
    }

    //ucid: ajo47
    //date: 9/12/2023

    private boolean hasPlayerMadeGuess(ServerPlayer currentPlayer) {
        return timeOfGuess.containsKey(currentPlayer);
    }
    //this method checks that a player hasnt made a guess recrded in the timeofguess object
    

    private boolean hasPlayerReachedMaxGuesses(ServerPlayer currentPlayer) {
        return guessesPerPlayer.getOrDefault(currentPlayer, 0) > MAX_GUESSES;
    }
    //returns yes or no dependidng on whether the player has reached maximum number of guesses, ie 1

    private void checkGameOver() {
    // ucid: ajo47
    //date: 9/12/2023
        int totalPlayers = players.size(); // Get the total number of players
        if (totalGuesses > totalPlayers * MAX_GUESSES) {
            gameover = true;
            resetSession();
            stopCountdown();
        }
    }

    
    

    private void readyCheck(boolean timerExpired) {
        if (currentPhase != Phase.READY) {
            return;
        }
    // two examples for the same result
        // int numReady = players.values().stream().mapToInt((p) -> p.isReady() ? 1 :
        // 0).sum();
        long numReady = players.values().stream().filter(ServerPlayer::isReady).count();
        if (numReady >= Constants.MINIMUM_PLAYERS) {

            if (timerExpired) {
                sendMessage(null, "Ready Timer expired, starting session");
                start();
            } else if (numReady >= players.size()) {
                
                sendMessage(null, "Everyone in the room marked themselves ready, starting session");
                if (readyTimer != null) {
                    readyTimer.cancel();
                    readyTimer = null;
                }
                start();
            }

        } else {
            if (timerExpired) {
                resetSession();
                sendMessage(null, "Ready Timer expired, not enough players. Resetting ready check");
            }
        }
    }



    // Get lines from a file
    private List<String> getFileLines(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    // Get a random line from a list of strings
    private String getRandomLine(List<String> lines) {
        Random random = new Random();
        int index = random.nextInt(lines.size());
        return lines.get(index);
    }

    // ucid: ajo47
    //date: 9/12/2023
    private String getRandomQuestionAndAnswer(String filePath) {
        try {
            List<String> lines = getFileLines(filePath);
            String randomLine = getRandomLine(lines);
            List<String> randomLineAsList = Arrays.asList(randomLine.split(";"));
            if (randomLineAsList.size() >= 4) {
                String question = "Category: "+randomLineAsList.get(0).trim()+", Question: "+randomLineAsList.get(1).trim()+", Options: "+randomLineAsList.get(2).trim();
                String correctAnswer = randomLineAsList.get(3).trim();
                setCurrentQuestionAnswer(correctAnswer);
                randquestion=question;
                return question;
            }
        } catch (IOException e) {
            logger.severe("Error while getting a random question: " + e.getMessage());
        }
        return "No question found";
    }

    // Set the current question's answer
    private void setCurrentQuestionAnswer(String answer) {
        this.currentQuestionAnswer = answer;
    }

    // Show a random question and pass to the user
    // ucid: ajo47
    //date: 9/12/2023
    private String showQuestion(String filePath) {
        return getRandomQuestionAndAnswer(filePath);
    }

    // ucid: ajo47
    //date: 9/12/2023
    //the start method is called and runs for 30 seconds, after which the resetsession method is called
    //the time is enforced by the timedevent logic

    private void start() {
        String filepath="questions.txt";
        updatePhase(Phase.IN_PROGRESS);
        sendMessage(null, "Session started");
        sessionTimer = new TimedEvent(30, () -> resetSession());
        sendMessage(null, showQuestion(filepath));
        startCountdown();
        
       
        
    }


    private synchronized void resetSession() {
        players.values().forEach(p -> p.setReady(false));
        updatePhase(Phase.READY);
        // Output times of guesses
        StringBuilder finalMessage = new StringBuilder("Rankings:\n");
        for (Map.Entry<ServerPlayer, Integer> entry : timeOfGuess.entrySet()) {
            ServerPlayer player = entry.getKey();
            int timeOfGuess = entry.getValue();
            String playerName = player.getClient().getClientName();
            String playerInfo =   playerName + ": Score " + timeOfGuess + "\n";
            finalMessage.append(playerInfo);
        }
          /*ucid: ajo47
        date: 9/12/2023
        Making of a string object of the scores and mapping them to player names
        */
        sendMessage(null,  finalMessage.toString() + "\nSession ended, please initiate a ready check to begin a new one");
        guessesPerPlayer.clear();
        timeOfGuess.clear();
        updatePhase(currentPhase);
        
        if (sessionTimer != null) {
            sessionTimer.cancel();
            sessionTimer = null;
        }
    }
    
    
    private void updatePhase(Phase phase) {
        if (currentPhase == phase) {
            return;
        }
        currentPhase = phase;
        Client.Instance.setCurrentPhase(phase);
        // NOTE: since the collection can yield a removal during iteration, an iterator
        // is better than relying on forEach
        Iterator<ServerPlayer> iter = players.values().stream().iterator();
        while (iter.hasNext()) {
            ServerPlayer client = iter.next();
            boolean success = client.getClient().sendPhaseSync(currentPhase);
            if (!success) {
                handleDisconnect(client);
            }
        }
    }

    protected void handleDisconnect(ServerPlayer player) {
        if (players.containsKey(player.getClient().getClientId())) {
            players.remove(player.getClient().getClientId());
            super.handleDisconnect(null, player.getClient());
            logger.info(String.format("Total clients %s", clients.size()));
            sendMessage(null, player.getClient().getClientName() + " disconnected");
            if (players.isEmpty()) {
                close();
            }
        }
    }

    private void syncReadyStatus(long clientId) {
        Iterator<ServerPlayer> iter = players.values().stream().iterator();
        while (iter.hasNext()) {
            ServerPlayer client = iter.next();
            boolean success = client.getClient().sendReadyStatus(clientId);
            if (!success) {
                handleDisconnect(client);
            }
        }
    }

}