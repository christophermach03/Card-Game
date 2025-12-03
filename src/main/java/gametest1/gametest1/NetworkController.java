/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chris
 */
public class NetworkController {

    private final NetworkManager network;
    private final Map<Integer, Card> cardIdMap; // cardID -> card object

    public NetworkController(NetworkManager network) {
        this.network = network;
        this.cardIdMap = new HashMap<>();
        // Prepopulate cardID map
        cardIdMap.put(0, new CardType00ExplodingDog());
        cardIdMap.put(1, new CardType01Shuffle());
    }

    // Player wants to draw a card
    public void requestDrawCard(Player player) {
        if (!network.isAuthorizedUser()) {
            network.broadcast(player.playerName + " \\ Draw_Card");
        } else {
            // If dealer, just draw locally
            Card c = Game.getInstance().getCurrentDeck().pop();
            if (c != null) {
                player.AddCardToHand(c);
                network.broadcast(player.playerName + " \\ Add_Card \\ " + c.cardId);
            }
        }
    }

    // Dealer receives "Draw_Card" and responds
    public void handleAddCard(Player player, int cardId) {
        Card c = cardIdMap.get(cardId);
        if (c != null) {
            player.AddCardToHand(c);
        }
    }

    // Player wants to play a card
    public void requestPlayCard(Player player, int cardId) {
        if (!network.isAuthorizedUser()) {
            network.broadcast(player.playerName + " \\ Play_Card \\ " + cardId);
        } else {
            // Dealer resolves card
            Card c = player.currentHand.hand.stream()
                    .filter(card -> card.cardId == cardId)
                    .findFirst()
                    .orElse(null);
            if (c != null) {
                player.PlayCard(c);
                network.broadcast(player.playerName + " \\ Played_Card \\ " + cardId);

                // Advance turn after playing
                Player next = Game.getInstance().advanceTurn();
                network.broadcast("TURN_CHANGED \\ " + next.playerName);
            }
        }
    }

    // Dealer receives "Play_Card" request
    public void handlePlayCard(Player player, int cardId) {
        Card c = cardIdMap.get(cardId);
        if (c != null) {
            player.PlayCard(c);
        }
    }
}