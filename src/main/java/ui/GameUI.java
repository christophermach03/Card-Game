/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author madma
 */

import observers.*;
import gametest1.gametest1.*;
import javax.swing.*;
import java.awt.*;

public class GameUI extends JFrame implements GameObserver {

    private HandPanel p1, p2, p3, p4;
    private JLabel turnLabel;
    private DeckPanel deck;
    private GameSubject subject;

    private NetworkManager network;
    private NetworkController controller;
    private Player localPlayer;

    
    public GameUI(NetworkManager network, Player player) {
        this.network = network;
        this.localPlayer = player;
        this.controller = new NetworkController(network);

        subject = new GameSubject();
        subject.addObserver(this);

        setTitle("Card Game UI - " + player.getPlayerName());
        setSize(1300, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setupTopBar();
        setupHands();

        setVisible(true);

        // If dealer, setup initial deck & hands
        if (network.isAuthorizedUser()) {
            Game.getInstance().setupDeck();
            Game.getInstance().playerList.forEach(Game.getInstance()::giveHandToPlayer);
        }
    }
    
    private void setupTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(true);
        topBar.setBackground(new Color(230, 230, 230));
        topBar.setPreferredSize(new Dimension(1300, 192));

        turnLabel = new JLabel("Turn: " + localPlayer.getPlayerName());
        turnLabel.setFont(new Font("Arial", Font.BOLD, 24));
        turnLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false);
        leftPanel.add(turnLabel);

        JButton drawBtn = new JButton("Draw Card");
        drawBtn.setFont(new Font("Arial", Font.BOLD, 16));
        drawBtn.addActionListener(e -> controller.requestDrawCard(localPlayer));

        leftPanel.add(drawBtn);
        topBar.add(leftPanel, BorderLayout.WEST);

        ImageIcon backIcon =
                new ImageIcon(getClass().getResource("/assets/cards/backCard.png"));
        deck = new DeckPanel(backIcon.getImage());
        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        center.add(deck);
        topBar.add(center, BorderLayout.CENTER);

        add(topBar, BorderLayout.NORTH);
    }
    
    private void setupHands() {
        p1 = new HandPanel("P1", subject);
        p2 = new HandPanel("P2", subject);
        p3 = new HandPanel("P3", subject);
        p4 = new HandPanel("P4", subject);

        JPanel hands = new JPanel();
        hands.setLayout(new BoxLayout(hands, BoxLayout.Y_AXIS));
        hands.setOpaque(false);

        hands.add(wrapWithLabel("P1", p1));
        hands.add(wrapWithLabel("P2", p2));
        hands.add(wrapWithLabel("P3", p3));
        hands.add(wrapWithLabel("P4", p4));

        add(hands, BorderLayout.CENTER);
    }
    
    private JPanel wrapWithLabel(String label, HandPanel hand) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel text = new JLabel(label + ": ");
        text.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(text, BorderLayout.WEST);
        panel.add(hand, BorderLayout.CENTER);
        return panel;
    }

    @Override
    public void onNotify(GameEvent event) {
        switch (event.type) {
            case CARD_DEALT:
                Object[] data = (Object[]) event.data;
                String playerName = (String) data[0];
                CardSprite sprite = (CardSprite) data[1];

                if (playerName.equals(localPlayer.getPlayerName())) {
                    // Add card visually to hand
                    if (p1.getOwnerId().equals(playerName)) p1.addCard(sprite);
                    if (p1.getOwnerId().equals(playerName)) p2.addCard(sprite);
                    if (p1.getOwnerId().equals(playerName)) p3.addCard(sprite);
                    if (p1.getOwnerId().equals(playerName)) p4.addCard(sprite);
                }
                break;

            case TURN_CHANGED:
                String player = (String) event.data;
                turnLabel.setText("Turn: " + player);
                p1.setHighlighted(player.equals("P1"));
                p2.setHighlighted(player.equals("P2"));
                p3.setHighlighted(player.equals("P3"));
                p4.setHighlighted(player.equals("P4"));
                break;
        }
    }
    

}


// =========================================================
// DUMMY CLASSES — input the real classes chris and gianni
class DummyTurnManager {

    private int turn = 0;
    private final String[] order = {"P1", "P2", "P3", "P4"};
    private final GameSubject subject;
    private final Timer timer;

    public DummyTurnManager(GameSubject subject) {
        this.subject = subject;
        timer = new Timer(4000, e -> nextTurn());
    }

    public void nextTurn() {
        String player = order[turn];
        turn = (turn + 1) % order.length;
        subject.notifyAll(new GameEvent(GameEvent.Type.TURN_CHANGED, player));
        timer.start();
    }
}

class DummyClock {
    public DummyClock(GameSubject subject) {}
}
