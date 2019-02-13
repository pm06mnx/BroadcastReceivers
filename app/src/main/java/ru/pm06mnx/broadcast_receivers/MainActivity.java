package ru.pm06mnx.broadcast_receivers;

import androidx.appcompat.app.AppCompatActivity;
import ru.pm06mnx.broadcast_receivers.state.StateManager;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements IStateChangeListener {

    private View forwardButton;
    private View backwardButton;
    private TextView textView;
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter broadcastFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forwardButton = findViewById(R.id.forward);
        backwardButton = findViewById(R.id.backward);
        textView = findViewById(R.id.current_state);

        forwardButton.setOnClickListener(v -> {
            Intent intent = StateChangeService.newIntent(this, StateChangeService.ACTION_FORWARD);
            startService(intent);
        });
        backwardButton.setOnClickListener(v -> {
            Intent intent = StateChangeService.newIntent(this, StateChangeService.ACTION_BACKWARD);
            startService(intent);
        });

        broadcastReceiver = new StateChangeBroadcastReceiver(this);
        broadcastFilter = new IntentFilter(StateChangeService.BROADCAST_FILTER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, broadcastFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onStateChange(String newState) {
        textView.setText(newState);
    }
}
