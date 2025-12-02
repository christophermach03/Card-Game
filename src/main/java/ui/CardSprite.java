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
import java.net.URL;

public class CardSprite {
    public final int id;
    public final String name;
    public final ImageIcon icon;

    public CardSprite(int id, String name, String resourcePath) {
        this.id = id;
        this.name = name;

        URL url = getClass().getResource(resourcePath);
        if (url == null) {
            System.err.println("ERROR: Could not load image: " + resourcePath);
            this.icon = new ImageIcon(); // empty but non-null
        } else {
            this.icon = new ImageIcon(url);
        }
    }
}
