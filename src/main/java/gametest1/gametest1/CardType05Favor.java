/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1;

/**
 *
 * @author Gianni
 */
public class CardType05Favor extends Card{
    public CardType05Favor(){
        this.cardName="Favor";
        this.cardId=5;
        this.cardDescription="One playe gives you a card of their choice.";
    }
    public void resolve(Game game, Player user) {
        Player target = game.chooseOtherPlayer(user);

        Card given = target.currentHand.hand.pop(); // random or first
        user.AddCardToHand(given);

        System.out.println(target.playerName + " gave a card to " + user.playerName);
    }
}
