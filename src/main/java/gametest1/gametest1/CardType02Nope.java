/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1;

/**
 *
 */
public class CardType02Nope extends Card{
    public CardType02Nope(){
        this.cardName="Nope";
        this.cardId=2;
        this.cardDescription="Negates any card's effect while it's on the stack"
                + "except for Defuse cards.";
        this.needsTarget = true;
    }
    
    public void CheckTargetRestrictions(){
        
    }
    
    
    
    public void PlayWithTarget(GameStack stack, Card targetCard){
        
        targetCard.SetValid(false);
    }
    
    @Override
    public void Play(GameStack stack){
        //get card with previos stack id
        //if no card exists, card fails
        //sets card to not valid
        Card c = stack.GetCardWithStackId(this.stackId-1);
        if (c != null){
            c.valid=false;
        }
    }
}
