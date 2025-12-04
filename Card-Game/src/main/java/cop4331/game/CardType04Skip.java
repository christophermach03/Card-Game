/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cop4331.game;

/**
 *
 * @author Gianni, chris
 */
public class CardType04Skip extends Card{
    public CardType04Skip(){
        this.cardName="Skip";
        this.cardId=4;
        this.cardDescription="You dont have to draw a card!";
    }
    
    @Override
    public void Play(GameStack stack) {
        this.owner.skipped=true;
        this.owner.currentGame.server.broadcast(this.owner.playerName + " skipped their draw!");
    }
}
