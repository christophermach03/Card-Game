/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observers;

/**
 *
 * @author madma
 */

public class GameEvent {

    public enum Type {
        CARD_PLAYED,
        CARD_DEALT,
        TURN_CHANGED,
        TIMER_TICK,

        DRAW_REQUESTED   // ★ NEW
    }

    public final Type type;
    public final Object data;

    public GameEvent(Type type, Object data) {
        this.type = type;
        this.data = data;
    }
}