/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cop4331.game;
import java.util.*;

/**
 *
 * @author Gianni, chris
 */
public class CardType05Favor extends Card{
    public CardType05Favor(){
        this.cardName="Favor";
        this.cardId=5;
        this.cardDescription="One player gives you a random card.";
    }
    @Override
    public void Play(GameStack stack) {
        Player owner = this.owner;

        // Get a list of other players
        ArrayList<Player> others = new ArrayList<>();
        for (Player p : owner.currentGame.getPlayerList()) {
            if (p != owner && !p.lost) {
                others.add(p);
            }
        }

        if (others.size() == 0) {
            owner.currentGame.server.sendToPlayer(owner.playerId, "No other players to take a card from!");
            return;
        }

        // Picks a random player from the others
        Player target = others.get(new Random().nextInt(others.size()));

        // Takes a random card
        Card stolenCard = target.GiveUpRandomCard();

        if (stolenCard != null) {
            owner.AddCardToHand(stolenCard);
            owner.currentGame.server.broadcast(owner.playerName + " took a card from " + target.playerName + "!");
        } else {
            owner.currentGame.server.sendToPlayer(owner.playerId, target.playerName + " had no cards to give!");
        }
    }
}
