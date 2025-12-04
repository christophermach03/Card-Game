/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cop4331.game;

/**
 *
 * @author Gianni, chris
 */
public class CardType06Attack extends Card{
    public CardType06Attack(){
        this.cardName="Attack";
        this.cardId=6;
        this.cardDescription="Don't draw any cards, forces the player to take two turns.";
    }
    @Override
    public void Play(GameStack stack) {
        Player owner = this.owner;
        owner.skipped = true;

        // Loop through all players in the game
        for (Player p : owner.currentGame.getPlayerList()) {
            // Skip the owner themselves
            if (p != owner && !p.lost) {
                p.Attacked(); // Call attacked() on each other player
            }
        }

        owner.currentGame.server.broadcast(owner.playerName + " attacked the other players! They will take extra turns.");
    }
}
