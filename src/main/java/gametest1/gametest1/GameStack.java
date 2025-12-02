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
    
    /**
     * Only use for testing purposes, adds a card but doesnt remove it
     * @param testCard 
     */
    public void TestAdd(Card testCard){
        Card card = testCard;
        this.AssignStackCounter(card);
        current_stack.push(card);
        
    }
    /**
     * Gives a card a stack counter. This is used for Nope cards so they can
     * target specific cards on the stack to cancel.
     * @param card 
     */
    public void AssignStackCounter(Card card){
        card.stackId = this.StackCounter;
        this.StackCounter++;
    }
    
    public Card GetCardWithStackId(int stackIdToFind){
        for (Card c : this.current_stack){
            if (c.stackId == stackIdToFind){
                return c;
            }
        }
        return null;
    }
    
    /**
     * Adds a card to stack, checks for responses, then begins resolving the
     * cards. It does this by popping them, then calling their play method.
     * If a card isnt valid because it has been canceled by a nope card, it wont
     * resolve.
     * @param addedCard 
     */
    public void AddToStack(Card addedCard){
        //gives card a stack counter
        this.AssignStackCounter(addedCard);
        //then adds it to the stack (push)
        current_stack.push(addedCard);
        
        //DOESNT DO ANYTHING, WILL DELETE LATER
        //CheckForResponses("others");
        
        //checks if valid, if true then plays card and removes it from the stack.
        //If not, it just removes it
        if(addedCard.CheckValid()==true){
            current_stack.pop().Play(this);
        }
        else{
            current_stack.pop();
        }
    }
    
    /**
     * Right now this does nothing, but it is supposed to ask each player if
     * they want to play a response card (nope, defuser).
     * @param target 
     */
    public void CheckForResponses(String target){
        System.out.println("Target is: "+target);
        
        if ("others".equals(target)){
            //bruh
        }
        else if("self".equals(target)){
            
        }
        Scanner scanner = new Scanner(System.in);
        while(true){
        System.out.println("Any Responses? Y/N?");
        String response = scanner.nextLine();
            if("Y".equals(response.toUpperCase())){
                System.out.println("a response!");
                break;
            }
            if("N".equals(response.toUpperCase())){
                System.out.println("no response!");
                break;
            }
            else{
                System.out.println("please y or n only");
            }
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
