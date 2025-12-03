/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import gametest1.gametest1.*;

/**
 *
 * @author chris
 */


public class MainMenuUI extends JFrame {

    private static final int MAX_PLAYERS = 4; // Change as needed

    private JTextField portField;
    private JButton joinButton;
    private NetworkManager network;
    private Player localPlayer;

    public MainMenuUI() {
        setTitle("Main Menu");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel portLabel = new JLabel("Enter Port:");
        portLabel.setBounds(50, 30, 100, 25);
        add(portLabel);

        portField = new JTextField();
        portField.setBounds(150, 30, 100, 25);
        add(portField);

        joinButton = new JButton("Join Game");
        joinButton.setBounds(100, 80, 150, 30);
        add(joinButton);

        joinButton.addActionListener(e -> startGame());

        setVisible(true);
    }

    private void startGame() {
        try {
            int port = Integer.parseInt(portField.getText());
            network = new NetworkManager(port);

            // Assign a local player
            localPlayer = new Player(
                    "Player" + (System.currentTimeMillis() % 1000),
                    Game.getInstance().playerList.size()
            );
            Game.getInstance().addPlayer(localPlayer);

            network.setOnPlayerJoined(peerPlayer -> {
                System.out.println(peerPlayer + " joined the game.");

                // Only dealer will deal cards when all players are connected
                if (network.isAuthorizedUser() &&
                    Game.getInstance().playerList.size() == MAX_PLAYERS) {

                    // Setup deck
                    Game.getInstance().setupDeck();

                    // Give each player their starting hand
                    Game.getInstance().playerList.forEach(Game.getInstance()::giveHandToPlayer);

                    // Notify all players to launch GameUI
                    Game.getInstance().playerList.forEach(p -> {
                        if (p.equals(localPlayer)) {
                            SwingUtilities.invokeLater(() -> new GameUI(network, localPlayer));
                        }
                        // In real P2P, send a network message to other peers to launch UI
                    });

                    dispose(); // close menu
                }
            });

            network.start();

            if (network.isAuthorizedUser()) {
                System.out.println("Server started. You are dealer.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid port number!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenuUI::new);
    }
}