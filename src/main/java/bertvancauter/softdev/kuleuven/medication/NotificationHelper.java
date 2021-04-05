package bertvancauter.softdev.kuleuven.medication;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper
{
    public static final String pushID = "PushID";
    public static final String pushName = "Push";

    private NotificationManager mManager;
    public NotificationHelper(Context base)
    {
        super(base);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            createChannels();
        }


    }
    @TargetApi(Build.VERSION_CODES.O)
    public void createChannels()
    {
        NotificationChannel pushChannel = new NotificationChannel(pushID, pushName, NotificationManager.IMPORTANCE_DEFAULT);
        pushChannel.enableLights(true);
        pushChannel.enableVibration(true);
        pushChannel.setLightColor(R.color.colorPrimary);
        pushChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(pushChannel);

    }

    public NotificationManager getManager()
    {
        if(mManager ==  null)
        {
            mManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder getPushNotification(String title, String message)
    {
        return new NotificationCompat.Builder(getApplicationContext(), pushID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_alarmbell);
    }

}
