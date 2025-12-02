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
import java.awt.event.*;

public class CardPanel extends JLabel {

    private final CardSprite card;
    private final GameSubject subject;
    private final String ownerId;

    public CardPanel(CardSprite card, String ownerId, GameSubject subject) {
        this.card = card;
        this.subject = subject;
        this.ownerId = ownerId;

        if (card.icon != null) {
            setIcon(card.icon);
        }

        setPreferredSize(new Dimension(68, 91)); // NEW SIZE
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                scale(1.15);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                scale(1.0);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                subject.notifyAll(new GameEvent(
                        GameEvent.Type.CARD_PLAYED,
                        new Object[]{ownerId, card}
                ));

                SwingUtilities.invokeLater(() -> {
                    HandPanel parent = (HandPanel) getParent();
                    parent.removeCardPanel(CardPanel.this);
                });
            }
        });
    }

    private void scale(double s) {
        if (card.icon == null) return;

        Image img = card.icon.getImage()
                .getScaledInstance((int)(68 * s), (int)(91 * s), Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(img));
        revalidate();
    }
}
