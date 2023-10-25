import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Server {
    int port = 3001;
    private List<ServerThread> clients = new ArrayList<ServerThread>();
    private NumberGuesser numberGuesser = new NumberGuesser();
    private MessageShuffler messageShuffler = new MessageShuffler();

    private void start(int port) {
        this.port = port;
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            Socket incoming_client = null;
            System.out.println("Server is listening on port " + port);
            do {
                System.out.println("waiting for the next client");
                if (incoming_client != null) {
                    System.out.println("Client connected");
                    ServerThread sClient = new ServerThread(incoming_client, this);

                    clients.add(sClient);
                    sClient.start();
                    incoming_client = null;
                }
            } while ((incoming_client = serverSocket.accept()) != null);
        } catch (IOException e) {
            System.err.println("Error accepting connection");
            e.printStackTrace();
        } finally {
            System.out.println("closing server socket");
        }
    }

    protected synchronized void disconnect(ServerThread client) {
        long id = client.getId();
        client.disconnect();
        broadcast("Disconnected", id);
        if (numberGuesser.isGameActive()) {
            if (client.getId() == numberGuesser.getHiddenNumberClientId()) {
                numberGuesser.deactivateGame();
                broadcast("Number guesser game deactivated. The number was guessed by client ", client.getId());
            }
        }
    }

    protected synchronized void broadcast(String message, long id) {
        if (processCommand(message, id)) {
            return;
        }

        if (messageShuffler.isShufflerActive() && !message.startsWith("shuffle")) {
            message = messageShuffler.shuffleMessage(message);
        } else {
            message = String.format("User[%d]: %s", id, message);
        }

        Iterator<ServerThread> it = clients.iterator();
        while (it.hasNext()) {
            ServerThread client = it.next();
            boolean wasSuccessful = client.send(message);
            if (!wasSuccessful) {
                System.out.println(String.format("Removing disconnected client[%s] from the list", client.getId()));
                it.remove();
                broadcast("Disconnected", id);
            }
        }
    }

    private boolean processCommand(String message, long clientId) {
        System.out.println("Checking command: " + message);
        if (message.equalsIgnoreCase("disconnect")) {
            Iterator<ServerThread> it = clients.iterator();
            while (it.hasNext()) {
                ServerThread client = it.next();
                if (client.getId() == clientId) {
                    it.remove();
                    disconnect(client);
                    break;
                }
            }
            return true;
        } else if (message.equalsIgnoreCase("guesser")) {
            if (!numberGuesser.isGameActive()) {
                numberGuesser.activateGame(clientId);
                broadcast("Number guesser game activated. Try to guess the number between 0 and 99.", clientId);
            } else {
                broadcast("Number guesser game is already active.", clientId);
            }
            return true;
        } else if (message.startsWith("guess")) {
            String result = numberGuesser.guessNumber(clientId, Integer.parseInt(message.split(" ")[1]));
            String msg = String.format("Client %d guessed %s, which is %s", clientId, message.split(" ")[1], result);
            broadcast(msg, clientId);
            return true;
        } else if (message.equalsIgnoreCase("stop")) {
            if (numberGuesser.isGameActive()) {
                numberGuesser.deactivateGame();
                broadcast("Number guesser game deactivated.", clientId);
            } else {
                broadcast("Number guesser game is not active.", clientId);
            }
            return true;
        } else if (message.equalsIgnoreCase("shuffle")) {
            broadcast("Message shuffler activated. Type a message to shuffle.", clientId);
            if (!messageShuffler.isShufflerActive()) {
                messageShuffler.activateShuffler();
            } else {
                broadcast("Message shuffler is already active.", clientId);
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Starting Server");
        Server server = new Server();
        int port = 3000;
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
            // can ignore, will either be index out of bounds or type mismatch
            // will default to the defined value prior to the try/catch
        }
        server.start(port);
        System.out.println("Server Stopped");
    }
}
