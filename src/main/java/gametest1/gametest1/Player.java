/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1;

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
    
    /**
     * Plays a card with extra steps.
     * @param playedCard 
     */
    public void PlayCard(Card playedCard){
        /**
         * 1. Check for targets
         * 2. remove card from hand
         * 3. Add card to stack
         * 4. Stack does its thing
         */
        //TEST THIS!!!!!
        if(playedCard.ConfirmTargetPlayer(currentStack)){
            RemoveCardFromHandById(playedCard.cardId);
            currentStack.AddToStack(playedCard);
        }
        
    }
    /**
     * Adds the top card of the deck to the players hand, while it is removed from the deck.
     * @param deck 
     */
    public void DrawCard(Deck deck){
        Card card = deck.pop();
        if (card != null){
            this.AddCardToHand(card);
        }
        else{
            System.out.println("Deck is empty, no card drawn!");
        }
    }
    
    /**
     * Does nothing right now, but will be used to prompt the user what card
     * they want to play, then plays it.
     */
    public void TakeTurn(){
        //do this next!!!!
    }
    
    public String getPlayerName(){
        return playerName;
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
