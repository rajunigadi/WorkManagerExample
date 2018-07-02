package com.sourcetouch.workmanagerexample;

import android.support.annotation.NonNull;
import android.util.Log;

import androidx.work.Worker;

/**
 * Created by Rajashekhar Vanahalli on 02/07/18.
 */
public class PeriodicWorker extends Worker {

    @NonNull
    @Override
    public Result doWork() {
        // Fetch the arguments (and specify default values):
        int k = getInputData().getInt("key_input", 0);

        for(long i=0;i<k;i++) {
            Log.e("PeriodicWorker", "Count: " + i);
        }
        return Result.SUCCESS;
    }
}
