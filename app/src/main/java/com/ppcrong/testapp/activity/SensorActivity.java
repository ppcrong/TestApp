package com.ppcrong.testapp.activity;

import android.Manifest;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.ppcrong.testapp.R;
import com.socks.library.KLog;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hei.permission.PermissionActivity;

public class SensorActivity extends PermissionActivity {

    // region [Variable]
    SensorManager mSensorManager;
    // endregion [Variable]

    // region [Widget]
    @BindView(R.id.btn_list_sensors)
    Button mBtnListSensors;
    // endregion [Widget]

    // region [OnClick]
    @OnClick(R.id.btn_list_sensors)
    public void onClickBtnListSensors() {

        KLog.i();

        mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
        final List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        KLog.i("=== LIST AVAILABLE SENSORS ===");
        KLog.i(String.format(Locale.getDefault(), "|%-40s|%-50s|%-10s|", "SensorName", "StringType", "Type"));
        for (Sensor sensor : sensors) {
            KLog.i(String.format(Locale.getDefault(), "|%-40s|%-50s|%-10s|", sensor.getName(), sensor.getStringType(), sensor.getType()));
        }

        KLog.i("=== LIST AVAILABLE SENSORS ===");
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

        setContentView(R.layout.activity_sensor);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        checkPermission(() -> {
        }, R.string.rationale, Manifest.permission.BODY_SENSORS);
    }
    // endregion [Private Function]
}
