/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observers;

/**
 *
 * @author madma
 */

import java.util.ArrayList;
import java.util.List;

public class GameSubject {

    private final List<GameObserver> observers = new ArrayList<>();

    public void addObserver(GameObserver obs) {
        observers.add(obs);
    }

    public void notifyAll(GameEvent event) {
        for (GameObserver o : observers) {
            o.onNotify(event);
        }
    }
}
