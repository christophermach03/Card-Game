/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cop4331.game;

/**
 *
 */
public class CardType01Shuffle extends Card {
    public CardType01Shuffle(){
        this.cardName="Shuffle";
        this.cardId=1;
        this.cardDescription="Shuffles the deck and stuff.";
    }
    
    @Override
    public void Play(GameStack stack){
        stack.GetCurrentDeck().Shuffle();
    }
}
