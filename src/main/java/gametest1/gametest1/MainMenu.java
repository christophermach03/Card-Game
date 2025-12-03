/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author chris
 */


public class MainMenu {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Card Game - P2P Setup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JTextField portField = new JTextField("1234");
        JButton startButton = new JButton("Start Game");

        frame.setLayout(new GridLayout(3, 1));
        frame.add(new JLabel("Enter port to host game:"));
        frame.add(portField);
        frame.add(startButton);

        startButton.addActionListener(e -> {
            int port = Integer.parseInt(portField.getText());
            new Thread(() -> {
                PeerNetworkManager manager = new PeerNetworkManager(port);
                manager.start();
            }).start();
            frame.dispose();
        });

        frame.setVisible(true);
    }
}
