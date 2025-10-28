/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1;
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
    public void RemoveCardById(int cardIdToFind){
        for(int i=0; i<hand.size();i++){
            if(hand.get(i).cardId == cardIdToFind){
                hand.remove(i);
            }
        }
    }
    
    /**
     * Will be used to sort the hand in order of card IDs.
     */
    public void SortHand(){
        
    }
}
