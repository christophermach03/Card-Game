/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1.ClientServer;
import java.io.*;
import java.net.*;
/**
 *
 * @author rigaljoseph
 */

public class ClientHandler extends Thread {
    private Socket socket; //conection to one client
    private Server server; //reference to the main server
    private PrintWriter out; //send messages TO this client
    private BufferedReader in; //send messages FROM this client

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
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
            
            //Announce to all clients that a user joined
            server.broadcast(username + " joined the game!");
            
            //Main message loop (takes in messages from the clients)
            String msg;
            while ((msg = in.readLine()) != null) {
                server.broadcast(username + ": " + msg);
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
