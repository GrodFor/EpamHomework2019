package sitsko.vlad.epamhomework;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class HomeworkService extends Service {

    private static final String HOMEWORK_ACTION = "sitsko.vlad.epamhomework.HOMEWORK_ACTION";
    public static final String BROADCAST_MESSAGE = "This is broadcast message.";

    public HomeworkService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "onStartCommand service", Toast.LENGTH_SHORT).show();
        Intent broadIntent = new Intent(HOMEWORK_ACTION).putExtra("broadIntent", BROADCAST_MESSAGE);
        sendBroadcast(broadIntent);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "onDestroy service", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
