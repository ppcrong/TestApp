package com.ppcrong.testapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ppcrong.testapp.activity.LogLibActivity;
import com.ppcrong.testapp.activity.RxJava2Activity;
import com.ppcrong.testapp.activity.SensorActivity;
import com.ppcrong.testapp.activity.TimezoneActivity;
import com.ppcrong.testapp.activity.setting.SettingsActivity;
import com.ppcrong.utils.MiscUtils;
import com.socks.library.KLog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

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

    @OnClick(R.id.btn_timezone)
    public void onClickBtnTimezone() {

        KLog.i();

        showTimezoneActivity();
    }

    @OnClick(R.id.btn_rxjava2)
    public void onClickBtnRxJava2() {

        KLog.i();

        showRxJava2Activity();
    }

    @OnClick(R.id.btn_settings)
    public void onClickBtnSettings() {

        KLog.i();

        showSettingsActivity();
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
        MiscUtils.startSafeIntent(this, pageIntent);
    }

    private void showSensorActivity() {

        Intent pageIntent = new Intent();
        pageIntent.setClass(this, SensorActivity.class);
        MiscUtils.startSafeIntent(this, pageIntent);
    }

    private void showTimezoneActivity() {

        Intent pageIntent = new Intent();
        pageIntent.setClass(this, TimezoneActivity.class);
        MiscUtils.startSafeIntent(this, pageIntent);
    }

    private void showRxJava2Activity() {

        Intent pageIntent = new Intent();
        pageIntent.setClass(this, RxJava2Activity.class);
        MiscUtils.startSafeIntent(this, pageIntent);
    }

    private void showSettingsActivity() {

        Intent pageIntent = new Intent();
        pageIntent.setClass(this, SettingsActivity.class);
        MiscUtils.startSafeIntent(this, pageIntent);
    }
    // endregion [Private Function]
}
