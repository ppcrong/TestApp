package com.ppcrong.testapp.activity;

import android.Manifest;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.ppcrong.rxpermlib.RxPermLib;
import com.ppcrong.testapp.R;
import com.socks.library.KLog;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    // region [Variable]
    SensorManager mSensorManager;
    private final float[] mAccelerometerReading = new float[3];
    private final float[] mGyroscopeReading = new float[3];
    private final float[] mMagnetometerReading = new float[3];

    private final float[] mRotationMatrix = new float[9];
    private final float[] mOrientationAngles = new float[3];

    private boolean mIsNowLogging = false;
    // endregion [Variable]

    // region [Widget]
    @BindView(R.id.btn_list_sensors)
    Button mBtnListSensors;
    @BindView(R.id.btn_start_log)
    Button mBtnStartLog;
    // endregion [Widget]

    // region [OnClick]
    @OnClick(R.id.btn_list_sensors)
    public void onClickBtnListSensors() {

        if (mSensorManager == null) {

            KLog.i("mSensorManager is null...");
            return;
        }

        KLog.i();

        final List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        KLog.i("=== LIST AVAILABLE SENSORS ===");
        KLog.i(String.format(Locale.getDefault(), "|%-40s|%-50s|%-10s|", "SensorName", "StringType", "Type"));
        for (Sensor sensor : sensors) {
            KLog.i(String.format(Locale.getDefault(), "|%-40s|%-50s|%-10s|", sensor.getName(), sensor.getStringType(), sensor.getType()));
        }

        KLog.i("=== LIST AVAILABLE SENSORS ===");
    }

    @OnClick(R.id.btn_start_log)
    public void onClickBtnStartLog() {

        if (isNowLogging()) {

            stopLog();
        } else {

            startLog();
        }
    }
    // endregion [OnClick]

    // region [Life Cycle]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KLog.i();

        initSensor();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Get updates from the accelerometer and magnetometer at a constant rate.
        // To make batch operations more efficient and reduce power consumption,
        // provide support for delaying updates to the application.
        //
        // In this example, the sensor reporting delay is small enough such that
        // the application receives an update before the system checks the sensor
        // readings again.
        Sensor accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            mSensorManager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_FASTEST, SensorManager.SENSOR_DELAY_UI);
        }
        Sensor gyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gyroscope != null) {
            mSensorManager.registerListener(this, gyroscope,
                    SensorManager.SENSOR_DELAY_FASTEST, SensorManager.SENSOR_DELAY_UI);
        }
        Sensor magneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magneticField != null) {
            mSensorManager.registerListener(this, magneticField,
                    SensorManager.SENSOR_DELAY_FASTEST, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Don't receive any more updates from either sensor.
        mSensorManager.unregisterListener(this);

        // Stop log when onPause
        stopLog();
    }
    // endregion [Life Cycle]

    // region [Sensor]
    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            System.arraycopy(event.values, 0, mAccelerometerReading,
                    0, mAccelerometerReading.length);
            KLog.i(String.format(Locale.getDefault(), "A,%d,%10.3f,%10.3f,%10.3f", event.timestamp,
                    event.values[0], event.values[1], event.values[2]));
        } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {

            System.arraycopy(event.values, 0, mGyroscopeReading,
                    0, mGyroscopeReading.length);
            KLog.i(String.format(Locale.getDefault(), "G,%d,%10.3f,%10.3f,%10.3f", event.timestamp,
                    event.values[0], event.values[1], event.values[2]));
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {

            System.arraycopy(event.values, 0, mMagnetometerReading,
                    0, mMagnetometerReading.length);
            KLog.i(String.format(Locale.getDefault(), "M,%d,%10.3f,%10.3f,%10.3f", event.timestamp,
                    event.values[0], event.values[1], event.values[2]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
        // You must implement this callback in your code.
    }

    // Compute the three orientation angles based on the most recent readings from
    // the device's accelerometer and magnetometer.
    public void updateOrientationAngles() {

        // Update rotation matrix, which is needed to update orientation angles.
        SensorManager.getRotationMatrix(mRotationMatrix, null,
                mAccelerometerReading, mMagnetometerReading);

        // "mRotationMatrix" now has up-to-date information.

        SensorManager.getOrientation(mRotationMatrix, mOrientationAngles);

        // "mOrientationAngles" now has up-to-date information.
    }

    public void updateOrientationAngles(float[] aReading, float[] mReading) {

        // Update rotation matrix, which is needed to update orientation angles.
        SensorManager.getRotationMatrix(mRotationMatrix, null,
                aReading, mReading);

        // "mRotationMatrix" now has up-to-date information.

        SensorManager.getOrientation(mRotationMatrix, mOrientationAngles);

        // "mOrientationAngles" now has up-to-date information.
    }
    // endregion [Sensor]

    // region [Private Function]
    private void initView() {

        setContentView(R.layout.activity_sensor);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RxPermLib.checkPermissions(this, () -> {

            KLog.i("BODY_SENSORS granted");
        }, Manifest.permission.BODY_SENSORS);
    }

    private void initSensor() {

        mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
    }

    private void startLog() {

        mIsNowLogging = true;
        logAllSensorData().subscribe(this::onLogNext, this::onLogException, this::onLogComplete);
        mBtnStartLog.setText("STOP log");
    }

    private void onLogNext(Integer integer) {

        KLog.i();
    }

    private void onLogException(Throwable throwable) {

        KLog.i(throwable.getMessage());
    }

    private void onLogComplete() {

        KLog.i("Log complete");
    }

    private Observable<Integer> logAllSensorData() {

        return Observable.create((ObservableOnSubscribe<Integer>) emitter -> {

            do {

                // A
                float[] aReading = new float[3];
                System.arraycopy(mAccelerometerReading, 0, aReading,
                        0, aReading.length);

                // G
                float[] gReading = new float[3];
                System.arraycopy(mGyroscopeReading, 0, gReading,
                        0, gReading.length);

                // M
                float[] mReading = new float[3];
                System.arraycopy(mMagnetometerReading, 0, mReading,
                        0, mReading.length);

                // Calculate AHRS
                updateOrientationAngles(aReading, mReading);
                float[] ahrsReading = new float[3];
                System.arraycopy(mOrientationAngles, 0, ahrsReading,
                        0, ahrsReading.length);
                // Convert to degrees
                ahrsReading[0] = (float) Math.toDegrees(mOrientationAngles[0]);
                ahrsReading[1] = (float) Math.toDegrees(mOrientationAngles[1]);
                ahrsReading[2] = (float) Math.toDegrees(mOrientationAngles[2]);

                KLog.i(String.format(Locale.getDefault(),
                        "[LOG] %d,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f",
                        System.currentTimeMillis(),
                        aReading[0], aReading[1], aReading[2],
                        gReading[0], gReading[1], gReading[2],
                        mReading[0], mReading[1], mReading[2],
                        ahrsReading[1], ahrsReading[2], ahrsReading[0]));

                // Write into log

                // Inform subscriber
                emitter.onNext(0);

                // Delay
                Thread.sleep(1000);
            } while (isNowLogging());

            emitter.onComplete();
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    private void stopLog() {

        mIsNowLogging = false;
        mBtnStartLog.setText("START log");
    }

    private boolean isNowLogging() {

        return mIsNowLogging;
    }
    // endregion [Private Function]
}
