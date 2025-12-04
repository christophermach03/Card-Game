/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cop4331.game;

/**
 *@Author chris
 */
public class CardType00ExplodingDog extends Card{
    public CardType00ExplodingDog(){
        this.cardName="Exploding Dog";
        this.cardId=0;
        this.cardDescription="Makes you lose the game, man.";
    }
    
    @Override
    public void Play(GameStack stack) {
        if (this.owner.CheckCardInHandById(3)) {
            this.owner.RemoveCardFromHandById(3);
            this.owner.currentGame.server.broadcast("You used up a diffuse card!");
            stack.GetCurrentDeck().AddBombCard(1);
            stack.GetCurrentDeck().Shuffle();
            // Do NOT lose if diffuse was used
        } else {
            this.owner.MakeLose();
            this.owner.currentGame.server.broadcast("You lose the game man!");
            if (this.owner.currentGame != null) {
                this.owner.currentGame.server.broadcast(this.owner.playerName + " drew an Exploding Dog and lost!");
            }
        }
    }
}
