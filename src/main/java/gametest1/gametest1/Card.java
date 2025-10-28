/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1;
import java.util.*;

/**
 *
 */
public class Card {
    String cardName;
    String cardDescription;
    Integer cardId; //Card IDs are hard coded integers assigned to certain cards in an arbitrary order.
    //Used as an easy way to identify if a card is a certain card type or not.
    
    Boolean valid; //Whether or not the card is valid
    Boolean needsTarget; //Whether or not the card needs a target.
    String targetType; //Targets can only be Player or Card
    ArrayList<Integer> currentTargetIds; //stoes the stack id of target cards
    ArrayList<Integer> strictTargetTypes; //A list of cardIDs, if the card can only target certain types of cards.
    ArrayList<Integer> strictTargetExclusions; //A list of cardIDs, if the card cant target certain types of cards.
    Boolean canRespond; //Whether or not this card is capable of responding
    Integer stackId; //The stack ID of this card, is assigned when it is put onto the stack. Currently unimplemented
    
    
    public Card(){
       this.valid =  true;
       this.stackId=0;
       this.currentTargetIds = new ArrayList<>();
    }
    
    
    /**
     * Used to confirm what targets a card will select, if it needs any.
     * Adds them to the array currentTargetIds, which stores the stack ids of
     * the cards on that stack its targeting.
     * @param stack
     * @return 
     */
    public Boolean ConfirmTargets(GameStack stack){
                
        Scanner input = new Scanner(System.in);        
        if(targetType.toLowerCase().equals("card")){
            while(true){
                System.out.println(stack);
                System.out.println("Give stack id of desired target!"
                        + " Or type 0 to return!");
                int targetId = input.nextInt();
                if(this.strictTargetExclusions.contains(targetId)){
                    System.out.println("Card is invalid target!");
                }
                else if(targetId == 0){
                    return false;
                }
                else{
                    //NEEDS A TRY CATCH STATEMENT HERE!!!!!!!!!!!
                    this.currentTargetIds.add(targetId);
                    return true;
                }
                
            }
            
        }
        else if(targetType.toLowerCase().equals("player")){
            //put some code here!
        }
        else{
            throw new IllegalArgumentException("Card "+this.cardName+" was not"
                    + " set up properly. Needs a target type!");
        }
        //null should never actually be returned. If it is, there is a bug
        //somewhere
        return null;
    }
    
    
    /**
     * This is the actual cards effect.
     * @param stack 
     */
    public void Play(GameStack stack){
        System.out.println(cardName+" played!");
    }
    
    /**
     * Used to make the card valid or invalid, used by the Nope card to cancel
     * the effect of another card. If a card is njot valid, the stack will not
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
        this.currentTargetIds.clear();
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
    
    @Override
    public String toString() {
        return ("Card: "+this.cardName+", Description: "+this.cardDescription+
                "Stack Id:"+this.stackId+System.lineSeparator());
        
    }
}
