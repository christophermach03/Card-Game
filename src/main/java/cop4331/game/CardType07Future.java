/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cop4331.game;

/**
 *
 * @author chris
 */
public class CardType07Future extends Card{
    public CardType07Future(){
        this.cardName="See the Future";
        this.cardId=7;
        this.cardDescription="Look at the top three cards of the deck.";
    }
    @Override
    public void Play(GameStack stack) {
        Card topCard = stack.GetCurrentDeck().getTopOfDeck();
        this.owner.currentGame.server.broadcast("The top of the deck is: "+topCard.cardName);
    }
}
