/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cop4331.server;

import cop4331.game.Game;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
/**
 *Waits for clients to connect, creates a ClientHandler for each one, then relays messages
 * @author rigaljoseph
 */

public class Server {
    private ServerSocket serverSocket; //Listenes for incoming connections
    private Set<ClientHandler> clients = new HashSet<>(); //all connected clients
    //set is used so there is no duplicates and fast lookup
    
    private Map<Integer, ClientHandler> playerConnections = new HashMap<>();
    
    private Game game;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        this.game = new Game(this);

        // Ensure the server socket is closed when the JVM exits
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("Shutting down server...");
                if (!serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    
    public void registerPlayerConnection(int playerId, ClientHandler handler){
        playerConnections.put(playerId, handler);
    }
    /***
     * Sends a message to a specific player id
     * @param playerId
     * @param message 
     */
    public void sendToPlayer(int playerId, String message){
        ClientHandler handler = playerConnections.get(playerId);
        if(handler != null) handler.send(message);
    }
    
    /***
     * Runs indefinitely, accepts new clients.
     */
    public void start() {
        try {
            while (true) {
                //server accepts a connection
                Socket socket = serverSocket.accept();
                //creates a new clienthandler
                ClientHandler handler = new ClientHandler(socket, this, game);
                //stores it in clients
                clients.add(handler);
                //Starts a new threat for that client
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * sends a message to every connected client.
     * @param msg 
     */
    public synchronized void broadcast(String msg) {
        for (ClientHandler client : clients) {
            client.send(msg);
        }
    }
    
    /**
     * called when client disconnects
     * @param handler 
     */
    public synchronized void remove(ClientHandler handler) {
        clients.remove(handler);
    }
    /**
     * Starts the server
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        Server server = new Server(1255);
        server.start();
    }
}
