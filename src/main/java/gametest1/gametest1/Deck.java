/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 */
public class Deck {
    ArrayList<Card> deck;
    int deckMaxSize;
    int explodingDogCards;
    int shuffleCards;
    
    public Deck(Integer deckMaxSize){
        deck = new ArrayList<>();
        this.deckMaxSize = deckMaxSize;
    }
    
    /**
     * Will be used to add cards to the deck, does nothing right now.
     */
    public void Initialize(){
    
    }
    
    public void Shuffle(){
        Collections.shuffle(deck);
    }
    
    /**
     * This method adds any number of bomb cards to the deck. Unfortunately
     * we have to hard code a method for each card type because i have no idea
     * how else to do it.
     * It also sets the deck's exploding dog card count to the total number
     * of exploding dog cards in the deck by using the CheckDeckForCardId 
     * method. The card ID for bomb cards is 0;
     * Prevents them from being added if they exceed the max deck size.
     * @param bombNum number of bomb cards user wants to add
     * @return True if the cards are able to be put into the deck. Cards cannot
     * cannot be put in a deck that doesn't have room.
     */
    public boolean AddBombCard(int bombNum){
        if(bombNum+deck.size()>this.deckMaxSize){
            System.out.println("Prevented deck overflow!");
            return false;
        }
        for(int i=0; i<bombNum; i++){
            deck.add(new CardType00ExplodingDog());
        }
        this.explodingDogCards = CheckDeckForCardId(0);
        return true;
    }
    /**
     * Adds any number of shuffle cards to deck, prevents them from being added
     * if they exceed the max deck size.
     * Shuffle card id is 1.
     * @param shuffleNum number of shuffle cards to be added
     * @return 
     */
    public boolean AddShuffleCard(int shuffleNum){
        if(shuffleNum+deck.size()>this.deckMaxSize){
            System.out.println("Prevented deck overflow!");
            return false;
        }
        for(int i=0; i<shuffleNum; i++){
            deck.add(new CardType01Shuffle());
        }
        this.shuffleCards = CheckDeckForCardId(1);
        return true;
    }
    
    /**
     * Checks the deck for the number of cards with the matching cardID
     * @param desiredCardId
     * @return 
     */
    public int CheckDeckForCardId(int desiredCardId){
        int cardCount = 0;
        for (Card i: deck){
            if(i.cardId == desiredCardId){
                cardCount+=1;
            }
        }
        return cardCount;
    }
    
    /**
     * Removes the top card of the deck, then returns it.
     * @return the top card of the deck
     */
    public Card pop() {
        try{
            Card card = this.deck.get(this.deck.size()-1);
            this.deck.remove(this.deck.size()-1);
            return card;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Deck is empty!");
        }
        return null;
    }
    
    @Override
    public String toString() {
        return ("Exploding Dog Cards: "+CheckDeckForCardId(0)+", "+
                "Shuffle cards: "+CheckDeckForCardId(1));
    }

    
}
