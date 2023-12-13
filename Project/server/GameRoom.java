package Project.server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import Project.common.CellData;
import Project.common.Character;
import Project.common.Constants;
import Project.common.Grid;
import Project.common.Phase;
import Project.common.TimedEvent;
import Project.common.Utils;
import Project.common.Character.ActionType;
import Project.common.Character.CharacterType;
import Project.common.exceptions.CharacterAlreadyAssignedException;
import Project.common.exceptions.InvalidMoveException;
import Project.server.CharacterFactory.ControllerType;

public class GameRoom extends Room {
    Phase currentPhase = Phase.READY;
    private static Logger logger = Logger.getLogger(GameRoom.class.getName());
    private TimedEvent readyTimer = null;
    private ConcurrentHashMap<Long, ServerPlayer> players = new ConcurrentHashMap<Long, ServerPlayer>();
    private Grid grid = new Grid();
    private Character currentTurnCharacter = null;
    Random rand = new Random();
    private List<Character> turnOrder = new ArrayList<Character>();
    List<ClientScore> scores = new ArrayList<>();
    static String preferedcategory;

    private ScoreTimer scoreTimerInstance; 

    public GameRoom(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    public static void setcategory(String category, ServerThread client){
        preferedcategory = category;
        client.sendMessage(client.getClientId(), "The new preffered ctegory is "+category);
    }

    /**
     * Attempts to lookup and load a character
     * 
     * @param client
     * @param character expected to contain search/lookup criteria, not an actual
     *                  full character reference
     */
    protected void loadCharacter(ServerThread client, Character charData) {
        // for now using character code to fetch
        String characterCode = charData.getCode();
        String[] parts = characterCode.split("-");
        if (parts.length >= 2) {
            String position = parts[0];
            String code = parts[1];
            Consumer<Character> callback = character -> {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Character created: ").append(character.getName()).append("\n");
                    sb.append("Character level: ").append(character.getLevel()).append("\n");
                    sb.append("Character type: ").append(character.getType()).append("\n");
                    sb.append("Character action type: ").append(character.getActionType()).append("\n");
                    sb.append("Character stats: ").append("\n");
                    sb.append("Attack: ").append(character.getAttack()).append("\n");
                    sb.append("Vitality: ").append(character.getVitality()).append("\n");
                    sb.append("Defense: ").append(character.getDefense()).append("\n");
                    sb.append("Will: ").append(character.getWill()).append("\n");
                    sb.append("Luck: ").append(character.getLuck()).append("\n");
                    sb.append("Progression Rate: ").append(character.getProgressionRate()).append("\n");
                    sb.append("Range: ").append(character.getRange()).append("\n");

                    System.out.println(sb.toString());
                    assignCharacter(client, character);
                    // client.sendCharacter(client.getClientId(), character);
                    syncCharacter(client.getClientId(), character);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            CharacterFactory.loadCharacter(position, code, callback);
        }
    }

    /**
     * Attempts to create a random character of the given type (TANK, DAMAGE,
     * SUPPORT)
     * 
     * @param client
     * @param ct
     */


    public static void submitquestion(String incomingquestion, ServerThread client){
        String fileName = "questions.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))) {
            // Write the string to the file
            writer.write(incomingquestion);
            writer.newLine();

            client.sendMessage(client.getClientId(),"Text has been saved to " + fileName);
        } catch (IOException e) {
            client.sendMessage(client.getClientId(),"Error writing to the file: " + e.getMessage());
        }

    }

    protected void createCharacter(ServerThread client, CharacterType ct) {
        Consumer<Character> callback = character -> {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("Character created: ").append(character.getName()).append("\n");
                sb.append("Character level: ").append(character.getLevel()).append("\n");
                sb.append("Character type: ").append(character.getType()).append("\n");
                sb.append("Character action type: ").append(character.getActionType()).append("\n");
                sb.append("Character stats: ").append("\n");
                sb.append("Attack: ").append(character.getAttack()).append("\n");
                sb.append("Vitality: ").append(character.getVitality()).append("\n");
                sb.append("Defense: ").append(character.getDefense()).append("\n");
                sb.append("Will: ").append(character.getWill()).append("\n");
                sb.append("Luck: ").append(character.getLuck()).append("\n");
                sb.append("Progression Rate: ").append(character.getProgressionRate()).append("\n");
                sb.append("Range: ").append(character.getRange()).append("\n");

                System.out.println(sb.toString());
                assignCharacter(client, character);
                // client.sendCharacter(client.getClientId(), character);
                syncCharacter(client.getClientId(), character);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        CharacterFactory.createCharacter(ControllerType.PLAYER, ct, 1, Utils.randomEnum(ActionType.class), callback);
    }

    @Override
    protected void addClient(ServerThread client) {
        logger.info("Adding client as player");
        players.computeIfAbsent(client.getClientId(), id -> {
            ServerPlayer player = new ServerPlayer(client);
            super.addClient(client);
            syncGameState(client);
            logger.info(String.format("Total clients %s", clients.size()));// change visibility to protected
            return player;
        });
    }

    public void score(ServerThread client, String answer) {
        int remainingTime = scoreTimerInstance.getRemainingTime();
        long clientID = client.getClientId();
        //ucid: ajo47
        //date: 12/12/2023
        
        // Check if the clientID already exists in the scores list
        boolean alreadyGuessed = false;
        for (ClientScore cs : scores) {
            if (cs.getClientId() == clientID) {
                alreadyGuessed = true;
                break;
            }
        }
    
        if (alreadyGuessed) {
            client.sendMessage(clientID, "Sorry, you already made a guess. Please wait for the next round.");
        } else {
            boolean result = compareParts(answer);
            if (result) {
                scores.add(new ClientScore(clientID, remainingTime));
            } else {
                scores.add(new ClientScore(clientID, 0));
            }
        }
    
        for (ClientScore cs : scores) {
            client.sendMessage(clientID, "Client ID: " + cs.getClientId() + ", Score: " + cs.getScore());
        }

        // Check if all players have made their guesses
        if (scores.size() == players.size()) {

                sendRestart();
                readyTimer = null;
                updatePhase(Phase.PREPARING);
            
        }
    }
    

    private static boolean compareParts(String input) {
        // Split the input string by comma
        String[] parts = input.split(",");
        
        // Check if the parts exist and are of equal length
        if (parts.length == 2) {
            // Trim and ignore case when comparing
            return parts[0].trim().equalsIgnoreCase(parts[1].trim());
        }
        return false; // Return false if the string does not have two parts separated by a comma
    }


    private void syncGameState(ServerThread incomingClient) {
        // single data
        // sync grid
        if (grid.hasCells()) {
            incomingClient.sendGridDimensions(grid.getRows(), grid.getColumns());
        } else {
            incomingClient.sendGridReset();
        }
        if (currentTurnCharacter != null) {
            incomingClient
                    .sendCurrentTurn(((ServerPlayer) currentTurnCharacter.getController()).getClient().getClientId());
        }
        incomingClient.sendPhaseSync(currentPhase);
        Iterator<ServerPlayer> iter = players.values().stream().iterator();
        while (iter.hasNext()) {
            ServerPlayer client = iter.next();
            if (client.getClient().getClientId() == incomingClient.getClientId()) {
                continue;
            }
            Character c = client.getCharacter();
            boolean success = false;
            if (c != null) {
                success = incomingClient.sendCharacter(client.getClient().getClientId(), c);
            }
            if (client.isReady()) {
                success = incomingClient.sendReadyStatus(client.getClient().getClientId());
            }

            if (!success) {
                break;
            }
        }
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
        // Hashmaps allow fast lookup by keys
        if (players.containsKey(client.getClientId())) {
            ServerPlayer sp = players.get(client.getClientId());
            sp.setReady(true);
            logger.info(String.format("Marked player %s[%s] as ready", sp.getClient().getClientName(), sp
                    .getClient().getClientId()));
            syncReadyStatus(sp.getClient().getClientId());
        }
        readyCheck(false);
    }

    public void sendawaymessage(ServerThread client){
        sendMessage(null, "Player "+client.getClientName()+ " marked themself away for this round");
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

    //ucid: ajo47
    //date: 12/12/2023
    private void start() {
        updatePhase(Phase.SELECTION);
        // TODO example
        sendMessage(
            null,
                "Session started: Please click the right answer to submit");
        new TimedEvent(30, () -> optionspanel());
        scoreTimerInstance = new ScoreTimer(30);
    }

    private void optionspanel() {
        updatePhase(Phase.PREPARING);
        nextTurn();
    }

    public synchronized void sendRestart(){
        scores.clear();
        resetSession();
        
        updatePhase(Phase.SELECTION);
        start();
    }

    public static void getquestions(ServerThread threadd){
       String question=getquestionsandanswer();
        threadd.handlenewquestion(question);
    }


        
    private static String getquestionsandanswer() {
        String filePath = "questions.txt";
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
    
            // Check if preferedcategory is null
            if (preferedcategory == null) {
                // Combine all lines into a single string
                return String.join("-", lines);
            } else {
                // Filter lines that contain the specified string
                List<String> filteredLines = lines.stream()
                        .filter(line -> line.contains(preferedcategory))
                        .collect(Collectors.toList());
    
                // Join the filtered lines into a single string
                return String.join("-", filteredLines);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the IOException appropriately
        }
        return ""; // Return an empty string if reading fails or no lines contain the string
    }
    
    



    



    

    

    // start handle next turn
    private void nextTurn() {
        updatePhase(Phase.PREPARING);
    }



    private void syncCells(List<CellData> cells) {
        Iterator<ServerPlayer> iter = players.values().stream().iterator();
        while (iter.hasNext()) {
            ServerPlayer client = iter.next();
            boolean success = client.getClient().sendCells(cells);
            if (!success) {
                handleDisconnect(client);
            }
        }
    }

    // end handle next turn
    private void cancelReadyTimer() {
        if (readyTimer != null) {
            readyTimer.cancel();
            readyTimer = null;
        }
    }

   
    private void endDungeon() {
        // TODO give experience / rewards

        Iterator<Character> iter = turnOrder.iterator();
        while (iter.hasNext()) {
            Character c = iter.next();
            if (c.isInCell()) {
                grid.removeCharacterFromCell(c.getCurrentCell().getX(), c.getCurrentCell().getY(), c);
            }
        }
        grid.reset();
        syncGridReset();
        resetSession();// TODO allow the session to continue a new dungeon or quit rather than just
                       // resetting
    }

    private synchronized void syncGridReset() {
        Iterator<ServerPlayer> iter = players.values().stream().iterator();
        while (iter.hasNext()) {
            ServerPlayer client = iter.next();
            boolean success = client.getClient().sendGridReset();
            if (!success) {
                handleDisconnect(client);
            }
        }
    }

    private synchronized void resetSession() {
        players.values().stream().forEach(p -> p.setReady(false));
        updatePhase(Phase.READY);
        sendMessage(null, "Session ended, please intiate ready check to begin a new one");
    }

    private void updatePhase(Phase phase) {
        if (currentPhase == phase) {
            return;
        }
        currentPhase = phase;
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
            super.handleDisconnect(null, player.getClient()); // change visibility to protected
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

    // handle character
    private void assignCharacter(ServerPlayer player, Character character) throws Exception {
        if (player.hasCharacter()) {
            throw new CharacterAlreadyAssignedException("Character already assigned");
        }
        player.assignCharacter(character);
    }

    private void assignCharacter(ServerThread client, Character character) {
        try {
            ServerPlayer sp = players.get(client.getClientId());
            assignCharacter(sp, character);
        } catch (CharacterAlreadyAssignedException ce) {
            if (currentPhase != Phase.SELECTION) {
                client.sendMessage(Constants.DEFAULT_CLIENT_ID, "You already have a character assigned");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void syncCharacter(long clientId, Character character) {
        Iterator<ServerPlayer> iter = players.values().stream().iterator();
        while (iter.hasNext()) {
            ServerPlayer client = iter.next();
            boolean success = client.getClient().sendCharacter(clientId, character);
            if (!success) {
                handleDisconnect(client);
            }
        }
    }

    @Override
    public void close() {
        super.close();
        players.clear();
        players = null;
        currentTurnCharacter = null;
        // turnOrder.clear(); // this is actually an immutable array so can't clear it
        turnOrder = null;
    }
}
