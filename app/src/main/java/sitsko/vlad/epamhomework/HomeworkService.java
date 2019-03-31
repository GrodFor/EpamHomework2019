package sitsko.vlad.epamhomework;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class HomeworkService extends Service {

    public static final String TAG = HomeworkService.class.getSimpleName();
    private static final String HOMEWORK_ACTION = "sitsko.vlad.epamhomework.HOMEWORK_ACTION";
    public static final String BROADCAST_MESSAGE = "This is broadcast message.";
    public static final String SERVICE_BROAD_INTENT = "broadIntent";

    public HomeworkService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: service");
        Intent broadIntent = new Intent(HOMEWORK_ACTION).putExtra(SERVICE_BROAD_INTENT, BROADCAST_MESSAGE);
        sendBroadcast(broadIntent);
        stopSelf();

        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy: service");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
