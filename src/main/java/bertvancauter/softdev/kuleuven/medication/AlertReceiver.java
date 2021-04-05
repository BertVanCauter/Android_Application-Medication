package bertvancauter.softdev.kuleuven.medication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver
{
    private String naam;
    private String aantal;
    @Override
    public void onReceive(Context context, Intent intent)
    {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        naam = ""+intent.getExtras().getString("naam");
        aantal = ""+intent.getExtras().getString("aantal");
        NotificationCompat.Builder nb = notificationHelper.getPushNotification("Neem "+naam,aantal+"x pilletje");
        notificationHelper.getManager().notify(1, nb.build());
    }
}
