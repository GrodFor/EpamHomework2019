package sitsko.vlad.epamhomework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class HomeworkBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "onReceive intent", Toast.LENGTH_SHORT).show();
    }
}