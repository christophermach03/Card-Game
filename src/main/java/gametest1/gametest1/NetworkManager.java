/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chris
 */
public class NetworkManager {

    private int port;
    private boolean authorizedUser = false; // true if this peer is dealer
    private List<Socket> peers = new ArrayList<>();
    private ServerSocket serverSocket;

    public NetworkManager(int port) {
        this.port = port;
    }

    public void start() {
        try {
            // First peer becomes dealer
            try {
                serverSocket = new ServerSocket(port);
                authorizedUser = true; // this peer is dealer
                System.out.println("Server started. You are dealer.");
                new Thread(this::acceptConnections).start();
            } catch (IOException e) {
                // Port in use → join existing dealer
                authorizedUser = false;
                Socket s = new Socket("localhost", port);
                peers.add(s);
                new Thread(() -> listenToPeer(s)).start();
                System.out.println("Joined existing game as client.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void handleMessage(String message, Peer peer){
        System.out.println("Recieved message: "+ message);
    }
    
    private void acceptConnections() {
        while (true) {
            try {
                Socket s = serverSocket.accept();
                peers.add(s);
                System.out.println("New peer connected: " + s.getInetAddress());
                new Thread(() -> listenToPeer(s)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void listenToPeer(Socket s) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Received: " + line);
                // TODO: parse message and call NetworkController handlers
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns true if this peer is the dealer/authorized user.
     */
    public boolean isAuthorizedUser() {
        return authorizedUser;
    }

    /**
     * Broadcast a message to all peers (including the dealer if client).
     */
    public void broadcast(String message) {
        for (Socket s : peers) {
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                writer.write(message);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // If dealer, optionally process locally
        if (authorizedUser) {
            System.out.println("Dealer processed broadcast: " + message);
        }
    }
}