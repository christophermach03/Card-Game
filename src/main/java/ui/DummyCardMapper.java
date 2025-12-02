/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author madma
 */

public class DummyCardMapper {

    public static CardSprite getCardSprite(int id) {

        String base = "/assets/cards/";

        return switch (id) {
            case 0 -> new CardSprite(0, "Bomb", base + "bombCard.png");
            case 1 -> new CardSprite(1, "Shuffle", base + "shuffleCard.png");
            case 2 -> new CardSprite(2, "Nope", base + "nopeCard.png");
            case 3 -> new CardSprite(3, "Defuse", base + "defuseCard.png");
            case 4 -> new CardSprite(4, "Skip", base + "skipCard.png");
            case 5 -> new CardSprite(5, "Favor", base + "favorCard.png");
            case 6 -> new CardSprite(6, "Attack", base + "attackCard.png");
            case 7 -> new CardSprite(7, "Future", base + "futureCard.png");
            default -> null;
        };
    }

    public static CardSprite getCardSpriteByName(String name) {
        return switch (name) {
            case "Bomb"   -> getCardSprite(0);
            case "Shuffle"-> getCardSprite(1);
            case "Nope"   -> getCardSprite(2);
            case "Defuse" -> getCardSprite(3);
            case "Skip"   -> getCardSprite(4);
            case "Favor"  -> getCardSprite(5);
            case "Attack" -> getCardSprite(6);
            case "Future" -> getCardSprite(7);
            default       -> null;
        };
    }
}
