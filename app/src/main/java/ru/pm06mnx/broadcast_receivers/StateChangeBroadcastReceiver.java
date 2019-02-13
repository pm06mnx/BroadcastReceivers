package ru.pm06mnx.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StateChangeBroadcastReceiver extends BroadcastReceiver {

    private final IStateChangeListener listener;

    public StateChangeBroadcastReceiver(IStateChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String newState = intent.getStringExtra(StateChangeService.BROADCAST_EXTRA_STATE);
        listener.onStateChange(newState);
    }
}
