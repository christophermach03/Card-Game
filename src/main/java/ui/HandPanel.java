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
import java.util.ArrayList;

public class HandPanel extends JPanel implements GameObserver {

    private final String ownerId;
    private final GameSubject subject;
    private final ArrayList<CardPanel> cards = new ArrayList<>();

    public HandPanel(String ownerId, GameSubject subject) {
        this.ownerId = ownerId;
        this.subject = subject;

        subject.addObserver(this);

        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        setOpaque(false);
        setPreferredSize(new Dimension(1200, 95));
    }

    public void addCard(CardSprite sprite) {
        CardPanel cp = new CardPanel(sprite, ownerId, subject);
        cards.add(cp);
        add(cp);
        revalidate();
        repaint();
    }

    public void removeCardPanel(CardPanel cp) {
        cards.remove(cp);
        remove(cp);
        revalidate();
        repaint();
    }

    public void setHighlighted(boolean value) {
        if (value) {
            setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        } else {
            setBorder(null);
        }
    }

    @Override
    public void onNotify(GameEvent event) {

        if (event.type == GameEvent.Type.CARD_DEALT) {
            Object[] data = (Object[]) event.data;
            String player = (String) data[0];
            CardSprite sprite = (CardSprite) data[1];

            if (ownerId.equals(player)) {
                addCard(sprite);
            }
        }
    }
}
