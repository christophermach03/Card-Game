/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cop4331.game;
import java.util.*;

/**
 *
 */
public class Hand {
    LinkedList<Card> hand;
    public Hand(){
        hand = new LinkedList<>();
        
    }
    
    /**
     * Adds a card to hand.
     * @param cardToAdd 
     */
    public void AddCard(Card cardToAdd){
        hand.add(cardToAdd);
    }
    
    /**
     * Removes a card from your hand using its cardID. Because copies of the same
     * card type are identical, we dont need specific card IDs, just generic ones.
     * @param cardIdToFind 
     */
    public void RemoveCardById(int cardIdToFind) {
        Iterator<Card> it = hand.iterator();
        while (it.hasNext()) {
            if (it.next().cardId == cardIdToFind) {
                it.remove();
                break; // stop after removing one
            }
        }
    }
    
    public Card GetCardById(int cardIdToFind){
        for (Card c : hand){
            if (c.cardId == cardIdToFind) return c;
        }
        return null;
    }
    
    public Boolean CheckCardById (int cardId){
        for (Card c : hand){
            if (c.cardId == cardId){
                return true;
            }
        }
        return false;
    }
    
    public  LinkedList<Card> getHand(){
        return hand;
    }
}
