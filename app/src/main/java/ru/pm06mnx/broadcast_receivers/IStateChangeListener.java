package ru.pm06mnx.broadcast_receivers;

/**
 * Слушатель для изменения состояний в менеджере состояний
 */
public interface IStateChangeListener {

    void onStateChange(String newState);
}
