/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cop4331.game;
import cop4331.server.Server;
import java.util.*;
/**
 *
 * @author chris
 */
public class Game {
    ArrayList<Player> playerList;
    GameStack currentStack;
    Deck currentDeck;
    Player activePlayer;
    Server server; //reference  to server for messaging
    boolean gameOver = false;
    
    /**
     * Creates the playerlist, a deck, and a stack
     * @param server
     */
    public Game(Server server){
        this.server = server;
        this.playerList = playerList = new ArrayList<Player>();
        this.currentDeck = new Deck();
        this.currentStack = new GameStack(playerList, currentDeck);
        
    }
    /**
     * Creates the players, adds them to the list.
     */
    public void Setup(){
        this.AssembleDeck();
        this.GiveHandsToPlayers();
        this.AddBombCardsToDeck();
    }
    
    public void AssembleDeck(){
        this.currentDeck.AddAttackCard(0);
        this.currentDeck.AddDefuseCard(3);
        this.currentDeck.AddFutureCard(4);
        this.currentDeck.AddShuffleCard(4);
        this.currentDeck.AddSkipCard(4);
        this.currentDeck.AddFavorCard(4);
        this.currentDeck.Shuffle();
    }
    
    public void AddBombCardsToDeck(){
        int bombNum = playerList.size()-1;
        for (int i=0; i<bombNum; i++){
            this.currentDeck.AddBombCard(1);
        }
    }
     
    
    
    public void GiveHandsToPlayers(){
        for (Player p : playerList){
            for (int i=0; i<4; i++){
                p.DrawCard(currentDeck);
            }
            p.AddCardToHand(new CardType03Defuse());
        }
    }
    
    public ArrayList<Player> getPlayerList(){
        return playerList;
    }
    
    public void addPlayer(Player p){
        this.playerList.add(p);
        p.currentGame = this;
        //ALSO ASSIGN CURRENT STACK (fixes bug if it goes here)
        p.currentStack = this.currentStack;
    }
    
    public Player getPlayerById(int id){
        for (Player p : playerList){
            if(p.playerId == id) return p;
        }
        return null;
    }
    
    public Deck getCurrentDeck(){
        return currentDeck;
    }
    
    public Player CheckForWin() {
        ArrayList<Player> playersStillIn = new ArrayList<>();
        for (Player p : this.playerList) {
            if (!p.lost) {
                playersStillIn.add(p);
            }
        }
        if (playersStillIn.size() == 1) {
            gameOver = true;
            return playersStillIn.get(0);
        }
        return null;
    }
    
    public void setActivePlayer(Player p){
        this.activePlayer=p;
    }
    
    public void moveUpActivePlayer(){
        //find active player in playerlist
        //check if theres another player in the next index
        //if there is, set that to be active player
        //if there isnt, set active player to be index 0
    }
    
    public void startGame() {
        if (playerList.size() == 0) {
            return;
        }

        activePlayer = playerList.get(0);
        server.broadcast("Game is starting...");
        server.sendToPlayer(activePlayer.playerId, "You are the first to act.");

        // SHOW HAND IMMEDIATELY
        sendHandToActivePlayer();

        // Prompt for card input
        server.sendToPlayer(activePlayer.playerId, "Type the CardID of the card you wish to play. Type 0 to pass");
    
    }
    
    public void takeTurn(int playerId, String msg) {
        //check for skip before draws, then clear
        
        if (gameOver) {
            server.sendToPlayer(playerId, "The game is over!");
            return;
        }
        
        Player p = getPlayerById(playerId);
        if (p == null || p != activePlayer) {
            server.sendToPlayer(playerId, "It is not your turn.");
            return;
        }

        // Try to parse the input as an integer (card ID)
        int cardId;
        try {
            cardId = Integer.parseInt(msg.trim());
        } catch (NumberFormatException e) {
            server.sendToPlayer(playerId, "Invalid input. Enter a card ID number.");
            sendHandToActivePlayer(); // show hand again
            return;
        }
        
        // Passing
        if (cardId == 0){
            server.sendToPlayer(playerId, "You passed the turn.");
            
            //draw a new card
            if (p.skipped) {
                server.sendToPlayer(playerId, "You are skipped this turn and do not draw a card.");
                p.skipped = false; // reset skip for next turn
            } else {
                // draw a new card normally
                p.DrawCard(currentDeck);
            }
            
            server.sendToPlayer(playerId, "Updated hand: ");
            sendHandToActivePlayer();
            
            Player winner = CheckForWin();
            if (winner != null) {
                server.broadcast(winner.playerName + " has won the game!");
                return;
            }
            
            
            nextPlayerTurn();
            return;
        }

        // Check if the player has this card
        if (!p.CheckCardInHandById(cardId)) {
            server.sendToPlayer(playerId, "You don't have a card with that ID. Try again.");
            sendHandToActivePlayer(); // show hand again
            return;
        }
        
        
        
        // Check to see if they tried to play a diffuse card
        if (cardId == 3){
            server.sendToPlayer(playerId, "Sorry, you can't play diffuse cards!");
            sendHandToActivePlayer(); // show hand again
            return;
        }
        
        

        // Play the card
        Card cardToPlay = p.GetCardInHandById(cardId);
        p.PlayCard(cardToPlay);
        
        server.sendToPlayer(playerId, "You played: "+ cardToPlay.cardName);
        
        //draw a new card after playing
        if (p.skipped) {
            server.sendToPlayer(playerId, "You are skipped this turn and do not draw a card.");
            p.skipped = false; // reset skip for next turn
        } else {
            // draw a new card normally
            p.DrawCard(currentDeck);
        }
        
        server.sendToPlayer(playerId, "Updated hand: ");
        sendHandToActivePlayer();
        
        // Check for game end
        Player winner = CheckForWin();
        if (winner != null) {
            server.broadcast(winner.playerName + " has won the game!");
            return;
        }
        
        // Advance to next player
        nextPlayerTurn();
        
    }
    
    public void sendHandToActivePlayer(){
        if (activePlayer == null) {
            return;
        }

        // Get hand as a string
        StringBuilder handStr = new StringBuilder();
        handStr.append("Your hand: ");
        for (Card c : activePlayer.currentHand.getHand()) {
            handStr.append(c.cardName).append(": id ");
            handStr.append(c.cardId).append(", ");
        }

        // Send it to the client
        server.sendToPlayer(activePlayer.playerId, handStr.toString());
    }
    
    private void nextPlayerTurn() {
        if (gameOver) return; // stop turn rotation
        int index = playerList.indexOf(activePlayer);
        index = (index + 1) % playerList.size();
        activePlayer = playerList.get(index);

        server.broadcast("It is now " + activePlayer.playerName + "'s turn!");
        
        
        // AUTOMATICALLY show the hand
        sendHandToActivePlayer();
        
        //prompt for cardID
        server.sendToPlayer(activePlayer.playerId, "Type the CardID of the card you wish to play");
    }
    
}
