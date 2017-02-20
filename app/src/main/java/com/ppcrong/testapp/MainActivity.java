package com.ppcrong.testapp;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;

import com.ppcrong.loglib.LogLib;
import com.socks.library.KLog;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hei.permission.PermissionActivity;

public class MainActivity extends PermissionActivity {

    //region Widget
    @BindView(R.id.btn_write_log)
    Button mBtnWritingData;
    @BindView(R.id.btn_close_log_file)
    Button mBtnStopToSave;
    @BindView(R.id.btn_read_log)
    Button mBtnReadLog;
    //endregion

    //region OnClick
    @OnClick(R.id.btn_context_dir)
    public void onClickBtnContextDir() {
        KLog.d();

        /* getFilesDir */
        // Get directory path
        String dirPath = getFilesDir().getPath();
        String dirAbsPath = getFilesDir().getAbsolutePath();
        String dirCcPath = "";
        try {
            dirCcPath = getFilesDir().getCanonicalPath();
        } catch (IOException e) {
            KLog.e(Log.getStackTraceString(e));
        }
        KLog.d(TestApp.LOG_TAG, "getFilesDir", dirPath, dirAbsPath, dirCcPath);

        // Get file path
        File file = new File(getFilesDir(), "getFilesDir.txt");
        String filePath = file.getPath();
        String fileAbsPath = file.getAbsolutePath();
        String fileCcPath = "";
        try {
            fileCcPath = file.getCanonicalPath();
        } catch (IOException e) {
            KLog.e(Log.getStackTraceString(e));
        }
        KLog.d(TestApp.LOG_TAG, "getFilesDir", filePath, fileAbsPath, fileCcPath);

        /* getCacheDir */
        KLog.d(TestApp.LOG_TAG, "getCacheDir", getCacheDir().getPath());

        /* getExternalFilesDir */
        // Get directory path
        dirPath = getExternalFilesDir(null).getPath();
        dirAbsPath = getExternalFilesDir(null).getAbsolutePath();
        dirCcPath = "";
        try {
            dirCcPath = getExternalFilesDir(null).getCanonicalPath();
        } catch (IOException e) {
            KLog.e(Log.getStackTraceString(e));
        }
        KLog.d(TestApp.LOG_TAG, "getExternalFilesDir", dirPath, dirAbsPath, dirCcPath);

        // Get file path
        file = new File(getExternalFilesDir(null), "getExternalFilesDir.txt");
        filePath = file.getPath();
        fileAbsPath = file.getAbsolutePath();
        fileCcPath = "";
        try {
            fileCcPath = file.getCanonicalPath();
        } catch (IOException e) {
            KLog.e(Log.getStackTraceString(e));
        }
        KLog.d(TestApp.LOG_TAG, "getExternalFilesDir", filePath, fileAbsPath, fileCcPath);

        // Create file
        try {
            if (file.exists()) {
                boolean b = file.delete();
                KLog.d("Delete " + (b ? "ok" : "fail"));
            }
            boolean b = file.createNewFile();
            KLog.d("Create " + (b ? "ok" : "fail"));
        } catch (IOException e) {
            KLog.e(Log.getStackTraceString(e));
        }

        /* getExternalCacheDir */
        KLog.d(TestApp.LOG_TAG, "getExternalCacheDir", getExternalCacheDir().getPath());

    }

    @OnClick(R.id.btn_env_dir)
    public void onClickBtnEnvDir() {
        KLog.d();

        /* getExternalStorageDirectory */
        // Get directory path
        String dirPath = Environment.getExternalStorageDirectory().getPath();
        String dirAbsPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String dirCcPath = "";
        try {
            dirCcPath = Environment.getExternalStorageDirectory().getCanonicalPath();
        } catch (IOException e) {
            KLog.e(Log.getStackTraceString(e));
        }
        KLog.d(TestApp.LOG_TAG, "getExternalStorageDirectory", dirPath, dirAbsPath, dirCcPath);

        /* getExternalStoragePublicDirectory */
        // Get directory path
        dirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
        dirAbsPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        dirCcPath = "";
        try {
            dirCcPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getCanonicalPath();
        } catch (IOException e) {
            KLog.e(Log.getStackTraceString(e));
        }
        KLog.d(TestApp.LOG_TAG, "getExternalStoragePublicDirectory", dirPath, dirAbsPath, dirCcPath);

    }

    @OnClick(R.id.btn_save_file)
    public void onClickBtnSaveFile() {
        LogLib.saveFile(LogLib.getExDir("Cloudchip"), "TestSaveFile.txt", "Hello world");
        LogLib.readFile(LogLib.getExDir("Cloudchip"), "TestSaveFile.txt");
    }

    @OnClick(R.id.btn_open_log_file)
    public void onClickBtnOpenLogFile() {
        boolean b = LogLib.openLogFile(LogLib.getExDir("Cloudchip"), "TestLogFile.txt");
        KLog.d(TestApp.LOG_TAG, "openLogFile " + (b ? "ok" : "fail"));
        mBtnWritingData.setEnabled(b);
        mBtnStopToSave.setEnabled(b);
        mBtnReadLog.setEnabled(!b);
    }

    int nLog = 0;
    @OnClick(R.id.btn_write_log)
    public void onClickBtnWriteLog() {
        nLog++;
        LogLib.writeLog("Logging data" + nLog + System.getProperty("line.separator"));
    }

    @OnClick(R.id.btn_close_log_file)
    public void onClickBtnCloseLogFile() {
        LogLib.closeLogFile();
        mBtnWritingData.setEnabled(false);
        mBtnStopToSave.setEnabled(false);
        mBtnReadLog.setEnabled(true);
    }

    @OnClick(R.id.btn_read_log)
    public void onClickReadLog() {
        LogLib.readFile(LogLib.getExDir("Cloudchip"), "TestLogFile.txt");
    }
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        KLog.d();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        checkPermission(new CheckPermListener() {
            @Override
            public void superPermission() {
            }
        }, R.string.rationale, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }
}
