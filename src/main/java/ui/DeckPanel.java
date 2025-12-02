/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author madma
 */

import javax.swing.*;
import java.awt.*;

public class DeckPanel extends JPanel {

    private Image deckImage;

    public DeckPanel(Image img) {
        this.deckImage = img;
        setOpaque(false);
    }

    public void updateDeckImage(Image img) {
        this.deckImage = img;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(136, 182);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(136, 182);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(136, 182);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (deckImage != null) {
            g.drawImage(deckImage, 0, 0, getWidth(), getHeight(), null);
        }
    }
}
