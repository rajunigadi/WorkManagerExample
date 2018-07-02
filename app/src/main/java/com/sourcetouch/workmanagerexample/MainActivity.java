package com.sourcetouch.workmanagerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_status)
    TextView tvStatus;

    private UUID oneTimeWorkId = null;
    private UUID periodicWorkId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_onetime)
    public void onOneTimeClick() {
        // set worker class
        OneTimeWorkRequest.Builder oneTimeWorkRequestBuilder =
                new OneTimeWorkRequest.Builder(OneTimeWorker.class);

        // create constraint
        Constraints.Builder constraintsBuilder = new Constraints.Builder();
        constraintsBuilder.setRequiredNetworkType(NetworkType.CONNECTED);
        Constraints constraints = constraintsBuilder.build();

        // input data
        Data input = new Data.Builder()
                .putInt("key_input", 10)
                .build();
        oneTimeWorkRequestBuilder.setInputData(input);

        // build task request
        OneTimeWorkRequest oneTimeWorkRequest = oneTimeWorkRequestBuilder
                .setConstraints(constraints)
                .build();

        // enque task
        WorkManager.getInstance().enqueue(oneTimeWorkRequest);
    }

    @OnClick(R.id.btn_periodic)
    public void onPeriodicClick() {
        // set worker class
        PeriodicWorkRequest.Builder periodicWorkRequestBuilder =
                new PeriodicWorkRequest.Builder(PeriodicWorker.class, 5,
                        TimeUnit.SECONDS);

        // create constraint
        Constraints.Builder constraintsBuilder = new Constraints.Builder();
        constraintsBuilder.setRequiredNetworkType(NetworkType.CONNECTED);
        Constraints constraints = constraintsBuilder.build();

        // input data
        Data input = new Data.Builder()
                .putInt("key_input", 10)
                .build();
        periodicWorkRequestBuilder.setInputData(input);

        // build task request
        PeriodicWorkRequest periodicWorkRequest = periodicWorkRequestBuilder
                .setConstraints(constraints)
                .build();

        // enque task
        WorkManager.getInstance().enqueue(periodicWorkRequest);
    }
}
