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

public class DummyHandFiller {

    public void giveHands(GameSubject subject) {

        int[] p1 = {0, 1, 2, 3, 4, 5, 6, 7};
        int[] p2 = {3, 5, 7, 0, 2, 4, 1, 6};
        int[] p3 = {6, 1, 2, 4, 0, 3, 5, 7};
        int[] p4 = {0, 0, 2, 2, 4, 4, 6, 6};

        dealTo(subject, "P1", p1);
        dealTo(subject, "P2", p2);
        dealTo(subject, "P3", p3);
        dealTo(subject, "P4", p4);
    }

    private void dealTo(GameSubject subject, String player, int[] list) {
        for (int id : list) {
            CardSprite sprite = DummyCardMapper.getCardSprite(id);
            if (sprite != null) {
                subject.notifyAll(new GameEvent(
                        GameEvent.Type.CARD_DEALT,
                        new Object[]{player, sprite}
                ));
            }
        }
    }
}
