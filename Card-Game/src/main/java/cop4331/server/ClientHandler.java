/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cop4331.server;
import java.io.*;
import java.net.*;
import cop4331.game.Game;
import cop4331.game.Player;
/**
 *
 * @author rigaljoseph
 */

public class ClientHandler extends Thread {
    private Socket socket; //conection to one client
    private Server server; //reference to the main server
    private PrintWriter out; //send messages TO this client
    private BufferedReader in; //send messages FROM this client
    
    private int playerId;
    private Game game;

    public ClientHandler(Socket socket, Server server, Game game) {
        this.socket = socket;
        this.server = server;
        this.game = game;
    }
    /***
     * Used by server to send messages to this specific client
     * @param msg is the message
     */
    public void send(String msg) {
        out.println(msg);
    }
    /**
     * Runs when you call handler.start() in the Server
     */
    @Override
    public void run() {
        try {
            //initialized the IO streams
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            
            //Ask for a username
            out.println("Welcome! Enter your username:");
            String username = in.readLine();
            
            // Assign a new unique playerId
            this.playerId = game.getPlayerList().size() + 1;
            
            // Create Player object inside Game
            Player p = new Player(username, playerId);
            game.addPlayer(p);
            
            
            // Register link between playerId and this handler
            server.registerPlayerConnection(this.playerId, this);
            
            //Announce to all clients that a user joined
            server.broadcast(username + " joined as Player " + this.playerId);
            //Main message loop (takes in messages from the clients)
            
            if (game.getPlayerList().size() == 2) {
                // automatically start when 2 players join
                game.Setup();
                game.startGame();
            }
            
            String msg;
            while ((msg = in.readLine()) != null) {
                game.takeTurn(playerId, msg);
            }

        } catch (IOException e) {
            System.out.println("Client disconnected");
        } finally {
            //handles disconnections
            server.remove(this);
            try { socket.close(); } catch (IOException ignored) {}
        }
    }
}
