package ru.pm06mnx.broadcast_receivers;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;
import ru.pm06mnx.broadcast_receivers.state.StateManager;

/**
 * Сервис для изменения состояния
 */
public class StateChangeService extends IntentService {

    public final static String ACTION_FORWARD = "Forward";
    public final static String ACTION_BACKWARD = "Backward";
    public static final String BROADCAST_FILTER = "ru.pm06mnx.broadcast_receivers.STATE_CHANGE_BROADCAST";
    public static final String BROADCAST_EXTRA_STATE = "state";
    public static final String TAG = "StateChangeService";

    public StateChangeService() {
        super(TAG);
    }

    /**
     * @param context контекст
     * @param action действие для выполнения
     * @return создает Intent для изменения состояния через сервис
     */
    public static Intent newIntent(Context context, String action) {
        Intent intent = new Intent(context, StateChangeService.class);
        intent.setAction(action);
        return intent;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        Log.i(TAG, "Получили intent "+Thread.currentThread().getName());
        new Thread(() -> {
            safeSleep(2000);
            switch (intent.getAction()) {
                case ACTION_FORWARD:
                    StateManager.getInstance().moveForward();
                    break;
                case ACTION_BACKWARD:
                    StateManager.getInstance().moveBackward();
                    break;
            }
            Log.i(TAG, "Отправляем broadcast "+Thread.currentThread().getName());
            sendBroadcast();
        }).start();
    }

    private void sendBroadcast() {
        Intent intent = new Intent(BROADCAST_FILTER);
        intent.putExtra(BROADCAST_EXTRA_STATE, StateManager.getInstance().getCurrentState());
        intent.setFlags(Intent.FLAG_EXCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }

    private void safeSleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
