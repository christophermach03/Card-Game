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
    private static Game instance;

    public ArrayList<Player> playerList;
    private GameStack currentStack;
    private Deck currentDeck;
    private int turnIndex = 0;

    private Game() {
        this.playerList = new ArrayList<>();
        this.currentDeck = new Deck(50);
        this.currentStack = new GameStack(playerList, currentDeck);
    }

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    public Deck getCurrentDeck() { return currentDeck; }

    public void setupDeck() {
        currentDeck.AddShuffleCard(15);
        currentDeck.Shuffle();
    }

    public void addPlayer(Player p) {
        playerList.add(p);
        giveHandToPlayer(p);
    }

    public void giveHandToPlayer(Player p) {
        for (int i = 0; i < 10; i++) {
            Card c = currentDeck.pop();
            if (c == null) break;
            p.AddCardToHand(c);
        }
    }

    public Player getCurrentPlayer() {
        if (playerList.isEmpty()) return null;

        int attempts = 0;
        while (playerList.get(turnIndex).lost && attempts < playerList.size()) {
            turnIndex = (turnIndex + 1) % playerList.size();
            attempts++;
        }
        return playerList.get(turnIndex);
    }

    // Call this after a player plays a card
    public Player advanceTurn() {
        do {
            turnIndex = (turnIndex + 1) % playerList.size();
        } while (playerList.get(turnIndex).lost);
        return playerList.get(turnIndex);
    }

    public GameStack getCurrentStack() { return currentStack; }

    public void checkForWinLoss() {
        for (Player p : playerList) {
            if (p.lost) {
                System.out.println(p.playerName + " has lost. Skipping turns.");
            }
        }
    }
}
