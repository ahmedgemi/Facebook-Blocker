package com.example.ahmed.exam_mode;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

/**
 * Created by ahmed on 04/01/17.
 */

public class CheckService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Task task = new Task();
        task.execute();


        return  START_STICKY;
    }

    public class Task extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {

            int x=1;

            while (x==1){


                ActivityManager actvityManager = (ActivityManager) getSystemService( ACTIVITY_SERVICE );

                List<ActivityManager.RunningAppProcessInfo> list  =  actvityManager.getRunningAppProcesses();


                for (int i=0 ; i < list.size() ; i++){


                    if (list.get(i).processName.contains("facebook")){

                        Log.d("test","dd");

                        if (list.get(i).importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND){ // == 100


                            Intent Intent = new Intent(getApplicationContext(), Main2Activity.class);
                            Intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(Intent);
                        }
                    }

                    Log.d("test", list.get(i).processName);
                }
            }
            return null;
        }
    }
}
