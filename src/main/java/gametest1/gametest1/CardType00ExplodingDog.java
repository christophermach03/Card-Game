/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1;

/**
 *
 */
public class CardType00ExplodingDog extends Card{
    public CardType00ExplodingDog(){
        this.cardName="Exploding Dog";
        this.cardId=0;
        this.cardDescription="Makes you lose the game, man.";
    }
    
    @Override
    public void Play(GameStack stack){
        if (this.owner.CheckCardInHandById(3)){
            this.owner.RemoveCardFromHandById(3);
            System.out.println("Used up your diffuse card!");
        }
        this.owner.MakeLose();
    }
    
}
