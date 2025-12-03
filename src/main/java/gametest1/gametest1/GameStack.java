/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1;
import java.util.*;

/**
 *
 * @author chris
 */
public class GameStack {
    private Stack<Card> current_stack;
    private ArrayList<Player> playerList;
    private Deck current_deck;
    int StackCounter;
    
    public GameStack(ArrayList playerListRef, Deck deck){
        current_stack = new Stack<>();
        this.playerList = playerListRef;
        this.current_deck = deck;
        this.StackCounter = 1;
        
    }
    
    public Stack getStack(){
        return this.current_stack;
    }
    
    
    public Deck GetCurrentDeck(){
        return this.current_deck;
    }
    
    /**
     * Adds a card to stack, checks for responses, then begins resolving the
     * cards. It does this by popping them, then calling their play method.
     * If a card isnt valid because it has been canceled by a nope card, it wont
     * resolve.
     * @param addedCard 
     */
    public void AddToStack(Card addedCard){
        //then adds it to the stack (push)
        current_stack.push(addedCard);
        
        //checks if valid, if true then plays card and removes it from the stack.
        //If not, it just removes it
        
        //
        
        if(addedCard.CheckValid()==true){
            current_stack.pop().Play(this);
        }
        else{
            current_stack.pop();
        }
    }
    
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Card card:current_stack){
            sb.append(card.toString());
        }
        return sb.toString();
    }

}
