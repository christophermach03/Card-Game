/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1;

/**
 *
 * @author Gianni
 */
public class CardType06Attack extends Card{
    public CardType06Attack(){
        this.cardName="Attack";
        this.cardId=6;
        this.cardDescription="Don't draw any cards, forces the player to take two turns.";
    }
    public void resolve(Game game, Player user) {
        System.out.println(user.playerName + " played ATTACK!");
        game.forceNextPlayerTurns(2);
        game.endCurrentPlayerTurn();
    }
}
