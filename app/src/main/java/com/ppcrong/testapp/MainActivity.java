package com.ppcrong.testapp;

import android.Manifest;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;

import com.ppcrong.loglib.LogLib;
import com.ppcrong.loglib.sLogLib;
import com.ppcrong.testapp.activity.LogLibActivity;
import com.ppcrong.testapp.activity.SensorActivity;
import com.ppcrong.testapp.utils.Utils;
import com.socks.library.KLog;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hei.permission.PermissionActivity;

public class MainActivity extends AppCompatActivity {

    // region [Widget]
    @BindView(R.id.btn_log_lib)
    Button mBtnLogLib;
    @BindView(R.id.btn_sensor)
    Button mBtnSensor;
    // endregion [Widget]

    // region [OnClick]
    @OnClick(R.id.btn_log_lib)
    public void onClickBtnLogLib() {

        KLog.i();

        showLogLibActivity();
    }

    @OnClick(R.id.btn_sensor)
    public void onClickBtnSensor() {

        KLog.i();

        showSensorActivity();
    }
    // region [OnClick]

    // region [Life Cycle]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KLog.i();

        initView();
    }
    // endregion [Life Cycle]

    // region [Private Function]
    private void initView() {

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void showLogLibActivity() {

        Intent pageIntent = new Intent();
        pageIntent.setClass(this, LogLibActivity.class);
        Utils.startSafeIntent(this, pageIntent);
    }

    private void showSensorActivity() {

        Intent pageIntent = new Intent();
        pageIntent.setClass(this, SensorActivity.class);
        Utils.startSafeIntent(this, pageIntent);
    }
    // endregion [Private Function]
}
