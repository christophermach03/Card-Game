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
public class Game {
    ArrayList<Player> playerList;
    GameStack currentStack;
    Deck currentDeck;
    
    /**
     * Creates the playerlist, a deck, and a stack
     */
    public Game(){
        this.playerList = playerList = new ArrayList<Player>();
        this.currentDeck = new Deck(50);
        this.currentStack = new GameStack(playerList, currentDeck);
        Card tempcard1 = new CardType01Shuffle();
        Card tempcard2 = new CardType02Nope();
        Card tempcard3 = new CardType00ExplodingDog();
        
        //testing stuff
        //this.currentStack.TestAdd(tempcard1);
        //this.currentStack.TestAdd(tempcard2);
        //this.currentStack.TestAdd(tempcard3);
        //System.out.println(this.currentStack);
    }
    /**
     * Creates the players, adds them to the list.
     */
    public void Setup(){
        Scanner input = new Scanner(System.in);
        
        System.out.println("How many players are there?");
        
        int playerCount = input.nextInt();
        input.nextLine();
        
        //This loop creates the players and assigns them names.
        //The players are added to playerList, an arrayList
        for(int i=0; i<playerCount; i++){
            System.out.println("What is the name of player "+ (i)+"?");
            String name = input.nextLine();
            playerList.add(new Player(name, i));
        }
        
        for(int i=0; i<playerList.size(); i++){
            System.out.println(playerList.get(i));
        }
    }
    
    public void AssembleDeck(){
    
    }
    
    public void GiveHandsToPlayers(){
        
    }
    
    public void TakeRound(){
        
    }
    
    public void TakeTurn(Player currentActivePlayer){
        
    }
    
    public void CheckforWinLoss(){
        
    }
    public static void main(String[] args) {
        //All this stuff down here is just being used for testing.
        System.out.println("Hello");
        Game current_game = new Game();
        
        
        CardType00ExplodingDog test_card_1 = new CardType00ExplodingDog();
        CardType01Shuffle test_card_2 = new CardType01Shuffle();
        
        //System.out.println(test_card_1);
        //System.out.println(test_card_2);
        
        Deck current_deck = new Deck(10);
        System.out.println(current_deck);
        current_deck.AddBombCard(5);
        current_deck.AddShuffleCard(3);
        System.out.println(current_deck);
        
        
        //current_game.Setup();
    }
}
