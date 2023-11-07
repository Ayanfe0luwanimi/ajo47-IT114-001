#### How to Execute 
- compile the Server.java
- compile the Client.java 
- Run the Server and Clients in a separate terminals
- You can open multiple instances of the client. each istance is run on a new separate thread

- In the client terminal run the command "connect localhost:3000" to connect them to the Server
- after successful connection you can run the command "guesser" or "shuffler" to start the Number Guesser or Message Shuffler respectively
- when running the Number guesser any client can guess the number by typing the command "guess" followed by a number eg ("guess 20")
- When the Message Shuffler is activated, subsequent messages will be shuffled and the result will be broadcasted to all connected clients
- 