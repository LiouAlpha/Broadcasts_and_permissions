package jp.co.liou.broadcasts_and_permissions;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

public class MyBroadcastMonitor extends Service {

    private static final String TAG = MyBroadcastMonitor.class.getSimpleName();
    private MyBroadcastReceiver mReceiver;

    public MyBroadcastMonitor() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent pIntent, int pFlags, int pStartId) {
        Log.d(TAG, "onStartCommand");
        mReceiver = new MyBroadcastReceiver();
        IntentFilter actionFilter = new IntentFilter();
        addAllActions(actionFilter);
        registerReceiver(mReceiver, actionFilter);

        IntentFilter dataTypeFilter = new IntentFilter();
        try {
            dataTypeFilter.addDataType("*/*");
            registerReceiver(mReceiver, dataTypeFilter);
        }catch(IntentFilter.MalformedMimeTypeException ex){
            //do nothing
        }

        IntentFilter dataSchemaFilter = new IntentFilter();
        dataSchemaFilter.addDataScheme("package");
        dataSchemaFilter.addDataScheme("file");
        dataSchemaFilter.addDataScheme("market");
        dataSchemaFilter.addDataScheme("http");
        dataSchemaFilter.addDataScheme("tel");
        dataSchemaFilter.addDataScheme("sms");
        dataSchemaFilter.addDataScheme("mailto");
        dataSchemaFilter.addDataScheme("about");
        dataSchemaFilter.addDataScheme("https");
        dataSchemaFilter.addDataScheme("ftps");
        dataSchemaFilter.addDataScheme("ftp");
        dataSchemaFilter.addDataScheme("javascript");
        registerReceiver(mReceiver, dataSchemaFilter);

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    private void addAllActions(IntentFilter pIntentFilter) {
        List<String> broadcasts = Arrays.asList(getResources().getStringArray(R.array.broadcasts));
        for (String action : broadcasts) {
            pIntentFilter.addAction(action);
        }
    }
}
