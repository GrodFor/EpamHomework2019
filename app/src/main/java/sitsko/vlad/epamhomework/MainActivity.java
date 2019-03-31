package sitsko.vlad.epamhomework;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String HOMEWORK_ACTION = "sitsko.vlad.epamhomework.HOMEWORK_ACTION";
    public static final String TAG = MainActivity.class.getSimpleName();

    private HomeworkBroadcast homeworkBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_view, new HomeworkFragment())
                    .commit();
        }

        Log.d(TAG, "Number of fragments: "
                + getSupportFragmentManager().getFragments().size());

        findViewById(R.id.startService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this, HomeworkService.class));
            }
        });

        findViewById(R.id.stopService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this, HomeworkService.class));
            }
        });

        final TextView intentMessageTextView = findViewById(R.id.broadcastText);

        homeworkBroadcast = new HomeworkBroadcast() {
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);

                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    intentMessageTextView.setText(bundle.getString(HomeworkService.SERVICE_BROAD_INTENT));
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter(HOMEWORK_ACTION);
        registerReceiver(homeworkBroadcast, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy: broadcast");
        unregisterReceiver(homeworkBroadcast);
    }
}
