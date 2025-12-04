/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cop4331.game;
import java.util.*;

/**
 * @author chris, matias
 * Represents a card in the game. Each card has a name, description, owner,
 * and stack-related properties. Cards can target players or other cards and may
 * respond to effects.
 * 
 * Invariants:
 * - stackId >= 0
 * - valid is never null
 * - currentTargetStackIds and currentTargetPlayers are never null
 */
public class Card {
    String cardName;
    String cardDescription;
    Integer cardId; //Card IDs are hard coded integers assigned to certain cards in an arbitrary order.
    //Used as an easy way to identify if a card is a certain card type or not.
    
    Player owner;
    Boolean valid; //Whether or not the card is valid
    
    Boolean needsTarget; //Whether or not the card needs a target.
    String targetType; //Targets can only be Player or Card
    ArrayList<Integer> currentTargetStackIds; //stoes the stack id of target cards
    ArrayList<Player> currentTargetPlayers;// stores a reference to any players being affected by the card effect.
    
    Boolean canRespond; //Whether or not this card is capable of responding
    Integer stackId; //The stack ID of this card, is assigned when it is put onto the stack. Currently unimplemented
    
    /**
     * Constructs a new card with default values.
     * Postconditions:
     * - valid is true
     * - stackId is 0
     * - currentTargetStackIds initialized
     */    public Card(){
       this.valid =  true;
       this.stackId=0;
       this.currentTargetStackIds = new ArrayList<>();
    }
    
    
    /**
     * Used to confirm what targets a card will select, if it needs any.
     * Adds them to the array currentTargetIds, which stores the stack ids of
     * the cards on that stack its targeting.
     * @param stack
     * @return 
     */
    public Boolean ConfirmTargetPlayer(GameStack stack){
        if(this.needsTarget == false){
            return true;
        }
        else{
            //show a list of the other players to the Owner
            //prompts a selection for the effect.
            //Adds that player to currentTargetPlayers
            
            return true;//temp for now
        }
    }
    
    
    /**
     * This is the actual cards effect.
     * @param stack The game stack
     * @pre stack != null
     */
    public void Play(GameStack stack){
        System.out.println(cardName+" played!");
    }
    
    /**
     * Used to make the card valid or invalid, used by the Nope card to cancel
     * the effect of another card. If a card is not valid, the stack will not
     * call its play method.
     * @param value 
     */
    public void SetValid(Boolean value){
        this.valid = value;
    }
    
    /**
     * Removes all targets from the card, used in the refresh method.
     */
    public void ClearTargets(){
        this.currentTargetStackIds.clear();
    }
    
    /**
     * Will basically reset this card to a blank copy of itself. Should be used
     * whenever the card is put into a hand.
     */
    public void Refresh(){
        this.valid = true;
        this.stackId=0;
        this.ClearTargets();
    }
    
    /**
     * Checks if the card is valid, meaning whether or not it can resolve.
     * @return 
     */
    public Boolean CheckValid(){
        return valid;
    }
    
    /**
     * Sets the cards stack id, done by the stack. Currently unimplemented.
     * @param value 
     */
    public void SetStackId(int value){
        this.stackId= value;
    }
    
    /**
     * 
     * @return The card's stack Id. Currently unimplemented
     */
    public int GetStackId(){
        return this.stackId;
    }
    /**
     * Sets the owner of the card.
     * @param player the player who owns this card
     * @pre player != null
     **/
    public void SetOwner(Player player){
        this.owner = player;
    }
    
    /**
     * Gets the owner of the card.
     * @return the owner player
     **/
    public Player GetOwner(){
        return this.owner;
    }
    
    @Override
    public String toString() {
        return ("Card: "+this.cardName+", Description: "+this.cardDescription+
                "Stack Id:"+this.stackId+System.lineSeparator());
        
    }
}
