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
import javax.swing.*;
import java.awt.*;

public class GameUI extends JFrame implements GameObserver {

    private HandPanel p1, p2, p3, p4;
    private JLabel turnLabel;
    private DummyTurnManager turnManager;

    private DeckPanel deck;
    private GameSubject subject;

    public GameUI() {

        subject = new GameSubject();
        subject.addObserver(this);

        turnManager = new DummyTurnManager(subject);
        DummyClock clock = new DummyClock(subject);

        setTitle("Card Game UI");
        setSize(1300, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // DECK PNG
        ImageIcon backIcon =
                new ImageIcon(getClass().getResource("/assets/cards/backCard.png"));

        deck = new DeckPanel(backIcon.getImage());
        deck.setPreferredSize(new Dimension(136, 182));

        // TOP BAR 
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(true);
        topBar.setBackground(new Color(230, 230, 230));

        // deck height
        topBar.setPreferredSize(new Dimension(1300, 192));

        //TURN LABEL
        turnLabel = new JLabel("Turn: P1");
        turnLabel.setFont(new Font("Arial", Font.BOLD, 24));
        turnLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false);
        leftPanel.add(turnLabel);

        JButton drawBtn = new JButton("Draw Card");
        drawBtn.setFont(new Font("Arial", Font.BOLD, 16));

        drawBtn.addActionListener(e -> {
            subject.notifyAll(
                new GameEvent(GameEvent.Type.DRAW_REQUESTED, null)
            );
        });

        leftPanel.add(drawBtn);
        topBar.add(leftPanel, BorderLayout.WEST);

        // DECK
        JPanel deckCenterPanel = new JPanel(new GridBagLayout());
        deckCenterPanel.setOpaque(false);
        deckCenterPanel.add(deck);
        topBar.add(deckCenterPanel, BorderLayout.CENTER);

        //CLOCK
        JPanel clockPanel = new JPanel();
        clockPanel.setPreferredSize(new Dimension(60, 60));
        clockPanel.setBackground(Color.BLACK);
        clockPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        topBar.add(clockPanel, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        // HANDS
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

        setVisible(true);

        SwingUtilities.invokeLater(() -> new DummyHandFiller().giveHands(subject));

        turnManager.nextTurn();
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

            case TURN_CHANGED:
                String player = (String) event.data;
                turnLabel.setText("Turn: " + player);

                p1.setHighlighted(player.equals("P1"));
                p2.setHighlighted(player.equals("P2"));
                p3.setHighlighted(player.equals("P3"));
                p4.setHighlighted(player.equals("P4"));
                break;

            case CARD_PLAYED:
                Object[] arr = (Object[]) event.data;
                CardSprite sprite = (CardSprite) arr[1];
                deck.updateDeckImage(sprite.icon.getImage());
                break;

            // You don't need a block here for DRAW_REQUESTED.
            // Your controller handles it, NOT the UI.
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameUI::new);
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
