/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cop4331.game;
import java.util.*;

/**
 *
 */
public class Player {
        String playerName;
        Integer playerId ;
        Integer handSize;
        Integer currentState; //0 for not active, 1 for active
        Hand currentHand;
        Game currentGame;
        GameStack currentStack;
        
        Boolean lost = false;
        
        Boolean skipped = false;
        int extraTurns = 0;
        
    public Player(String name, Integer id){
        this.playerName = name;
        this.playerId = id;
        this.handSize = 5;
        this.currentState = 0;
        this.currentHand = new Hand();
    }
    
    /**
     * Adds card to hand.
     * @param addedCard 
     */
    public void AddCardToHand(Card addedCard){
        currentHand.AddCard(addedCard);
    }
    
    public void ClearSkip(){
        this.skipped = false;
    }
    
    public void Attacked(){
        this.extraTurns=this.extraTurns+2;
    }
    
    public void ExtraTurnTickDown(){
        if(this.extraTurns > 0){
            extraTurns--;
        }
    }
    
    public Card GiveUpRandomCard(){
        LinkedList<Card> handCards = currentHand.getHand();
        if (handCards.size() == 0) {
            return null;
        }

        int randomIndex = new Random().nextInt(handCards.size());
        Card givenCard = currentHand.GiveUpCardByIndex(randomIndex);

        return givenCard;
    }
    
    /**
     * Removes card from hand using its card ID.
     * @param removedCardId 
     */
    public void RemoveCardFromHandById(Integer removedCardId){
        currentHand.RemoveCardById(removedCardId);
    }
    
    public Boolean CheckCardInHandById(Integer cardId){
        return (this.currentHand.CheckCardById(cardId));
    }
    
    public Card GetCardInHandById(Integer cardId){
        return this.currentHand.GetCardById(cardId);
    }
    
    /**
     * Plays a card with extra steps.
     * @param playedCard 
     */
    public void PlayCard(Card playedCard){
        Card c = playedCard;
        RemoveCardFromHandById(c.cardId);
        currentStack.AddToStack(c);
        
    }
    /**
     * Adds the top card of the deck to the players hand, while it is removed from the deck.
     * @param deck 
     */
    public void DrawCard(Deck deck){
        Card card = deck.pop();
        if (card != null){
            //assigns owner
            card.owner = this;
            //checks to see if its an exploding dog card first
            if (card.cardId == 0) {
                System.out.println(this.playerName + " drew an Exploding Dog!");
                card.Play(currentStack); // resolve immediately
            } else {
                this.AddCardToHand(card);
                System.out.println(this.playerName + " drew: " + card.cardName + " (id " + card.cardId + ")");
            }
        }
        else{
            System.out.println("Deck is empty, no card drawn!");
        }
    }
    
    public Boolean CheckForLoss(){
        return this.lost;
    }
    
    public void MakeLose(){
        this.lost = true;
    }
    
    @Override
    public String toString() {
        return ("Player: "+this.playerName+" Id: "+this.playerId);
    }
    
}
