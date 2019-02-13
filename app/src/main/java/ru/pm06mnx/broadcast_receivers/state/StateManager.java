package ru.pm06mnx.broadcast_receivers.state;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Менеджер состояний
 */
public enum StateManager {

    INSTANCE;

    public final static List<String> AVAILABLE_STATES = Collections.unmodifiableList(Arrays.asList("A", "B", "C", "D", "E"));

    private int currentState;

    /**
     * Меняет состояние на следующее
     */
    public void moveForward() {
        if (currentState < AVAILABLE_STATES.size()-1) {
            currentState++;
        }
    }

    /**
     * Меняет состояние на предыдущее
     */
    public void moveBackward() {
        if (currentState > 0) {
            currentState--;
        }
    }

    /**
     * @return текущее состояние
     */
    public String getCurrentState() {
        return AVAILABLE_STATES.get(currentState);
    }

    /**
     * @return синглетон менеджера состояний
     */
    public static StateManager getInstance() {
        return INSTANCE;
    }
}
