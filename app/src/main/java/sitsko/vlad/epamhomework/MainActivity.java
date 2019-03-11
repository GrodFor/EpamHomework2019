package sitsko.vlad.epamhomework;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String HOMEWORK_ACTION = "sitsko.vlad.epamhomework.HOMEWORK_ACTION";

    private TextView broadcastTextReceive;
    private HomeworkBroadcast homeworkBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_view, new HomeworkFragment())
                .commit();

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

        broadcastTextReceive = findViewById(R.id.broadcastText);

        homeworkBroadcast = new HomeworkBroadcast() {
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);

                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    broadcastTextReceive.setText(bundle.getString("broadIntent"));
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter(HOMEWORK_ACTION);
        registerReceiver(homeworkBroadcast, intentFilter);
    }
}
