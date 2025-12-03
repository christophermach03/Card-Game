/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1;

import java.util.List;

/**
 *
 * @author Gianni
 */
public class CardType07Future extends Card{
    public CardType07Future(){
        this.cardName="See the Future";
        this.cardId=0;
        this.cardDescription="Look at the top three cards of the deck.";
    }
    public void resolve(Game game, Player user) {
        List<Card> topCards = game.currentDeck.peekTop(3);
        System.out.println("Top 3 cards: " + topCards);
    }
}
