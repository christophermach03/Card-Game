/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author chris
 */
public class PeerNetworkManager {

    private int port;
    private ServerSocket server;
    private boolean isDealer = false;
    private Game game;

    private int nextPlayerId = 1; // Dealer starts at 1
    private int playerId = -1;    // This peer's ID

    private List<PrintWriter> outStreams = new ArrayList<>();

    public PeerNetworkManager(int port) {
        this.port = port;
        this.game = new Game();
    }

    public void start() {
        try {
            server = new ServerSocket(port);
            isDealer = true;   // first peer = dealer
            playerId = nextPlayerId++; // Dealer is player 1
            game.addPlayer(playerId);  // Add dealer to game
            System.out.println("You are the DEALER on port " + port + " with ID " + playerId);

            // Start listening thread
            new Thread(this::listenForConnections).start();

        } catch (IOException e) {
            System.out.println("Port already in use. Connecting instead...");
            isDealer = false;
            connectToDealer("localhost", port);
        }
    }

    private void listenForConnections() {
        System.out.println("Waiting for peers...");
        while (true) {
            try {
                Socket s = server.accept();
                System.out.println("Peer connected!");
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                outStreams.add(out);

                new Thread(new PeerListener(s, this)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void connectToDealer(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            System.out.println("Connected to dealer!");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            outStreams.add(out);

            new Thread(new PeerListener(socket, this)).start();

            // Request an ID
            out.println("JOIN_REQUEST");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToAll(String msg) {
        for (PrintWriter out : outStreams) {
            out.println(msg);
        }
    }

    public void handleMessage(String msg) {
        System.out.println("Received: " + msg);

        String[] parts = msg.split("\\|");

        switch (parts[0]) {

            case "JOIN_REQUEST":
                if (isDealer) {
                    int assignedId = nextPlayerId++;
                    game.addPlayer(assignedId);

                    // Send only to the new peer their assigned ID
                    outStreams.get(outStreams.size() - 1).println("ASSIGN_ID|" + assignedId);
                    System.out.println("Assigned player ID " + assignedId);

                    // Send deck to new peer
                    outStreams.get(outStreams.size() - 1).println("DECK|" + game.currentDeck.serialize());
                }
                break;

            case "ASSIGN_ID":
                playerId = Integer.parseInt(parts[1]);
                game.addPlayer(playerId);
                System.out.println("I am player " + playerId);
                break;

            case "DECK":
                if (!isDealer) {
                    game.currentDeck.deserialize(parts[1]);
                    System.out.println("Deck synced!");
                }
                break;

            case "DRAW":
                int drawPlayerId = Integer.parseInt(parts[1]);
                if (isDealer) {
                    Player player = game.getPlayerById(drawPlayerId);
                    if (player != null) {
                        Card card = game.currentDeck.draw();
                        sendToAll("DRAW_RESULT|" + drawPlayerId + "|" + card.serialize());
                    }
                }
                break;

            case "DRAW_RESULT":
                int resultPlayerId = Integer.parseInt(parts[1]);
                Card card = Card.fromString(parts[2]);
                Player p = game.getPlayerById(resultPlayerId);
                if (p != null) {
                    p.DrawCard(card); // Call Player.DrawCard
                }
                break;
        }
    }
}