/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1;

/**
 *
 * @author Gianni
 */
public class CardType04Skip extends Card{
    public CardType04Skip(){
        this.cardName="Skip";
        this.cardId=4;
        this.cardDescription="You dont have to draw a card!";
    }
    public void resolve(Game game, Player user) {
        System.out.println(user.playerName + " played SKIP! Their turn ends.");
        game.endCurrentPlayerTurn();
    }
}
