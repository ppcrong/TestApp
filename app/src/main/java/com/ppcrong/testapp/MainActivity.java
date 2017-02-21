package com.ppcrong.testapp;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;

import com.ppcrong.loglib.LogLib;
import com.ppcrong.loglib.sLogLib;
import com.socks.library.KLog;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hei.permission.PermissionActivity;

public class MainActivity extends PermissionActivity {

    //region Widget
    @BindView(R.id.btn_write_log_s)
    Button mBtnWritingData_s;
    @BindView(R.id.btn_close_log_file_s)
    Button mBtnStopToSave_s;
    @BindView(R.id.btn_read_log_s)
    Button mBtnReadLog_s;
    @BindView(R.id.btn_write_log)
    Button mBtnWritingData;
    @BindView(R.id.btn_close_log_file)
    Button mBtnStopToSave;
    @BindView(R.id.btn_read_log)
    Button mBtnReadLog;
    @BindView(R.id.btn_write_log2)
    Button mBtnWritingData2;
    @BindView(R.id.btn_close_log_file2)
    Button mBtnStopToSave2;
    @BindView(R.id.btn_read_log2)
    Button mBtnReadLog2;
    //endregion

    //region OnClick
    @OnClick(R.id.btn_context_dir_s)
    public void onClickBtnContextDirS() {
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

    @OnClick(R.id.btn_env_dir_s)
    public void onClickBtnEnvDirS() {
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

    @OnClick(R.id.btn_save_file_s)
    public void onClickBtnSaveFileS() {
        sLogLib.saveFile(sLogLib.getExDir("Cloudchip"), "TestSaveFile.txt", "Hello world sssss");
        sLogLib.readFile(sLogLib.getExDir("Cloudchip"), "TestSaveFile.txt");
    }

    @OnClick(R.id.btn_open_log_file_s)
    public void onClickBtnOpenLogFileS() {
        boolean b = sLogLib.openLogFile(sLogLib.getExDir("Cloudchip"), "TestLogFile.txt");
        KLog.d(TestApp.LOG_TAG, "openLogFile " + (b ? "ok" : "fail"));
        mBtnWritingData_s.setEnabled(b);
        mBtnStopToSave_s.setEnabled(b);
        mBtnReadLog_s.setEnabled(!b);
    }

    int nLog = 0;
    @OnClick(R.id.btn_write_log_s)
    public void onClickBtnWriteLogS() {
        nLog++;
        sLogLib.writeLog("Logging data" + nLog + System.getProperty("line.separator"));
    }

    @OnClick(R.id.btn_close_log_file_s)
    public void onClickBtnCloseLogFileS() {
        sLogLib.closeLogFile();
        mBtnWritingData_s.setEnabled(false);
        mBtnStopToSave_s.setEnabled(false);
        mBtnReadLog_s.setEnabled(true);
    }

    @OnClick(R.id.btn_read_log_s)
    public void onClickReadLogS() {
        sLogLib.readFile(sLogLib.getExDir("Cloudchip"), "TestLogFile.txt");
    }

    LogLib logger1 = new LogLib();
    LogLib logger2 = new LogLib();
    @OnClick(R.id.btn_save_file)
    public void onClickBtnSaveFile() {
        logger1.saveFile(sLogLib.getExDir("Cloudchip"), "TestSaveFile1.txt", "Hello world 11111");
        logger1.readFile(sLogLib.getExDir("Cloudchip"), "TestSaveFile1.txt");
    }

    @OnClick(R.id.btn_open_log_file)
    public void onClickBtnOpenLogFile() {
        boolean b = logger1.openLogFile(sLogLib.getExDir("Cloudchip"), "TestLogFile1.txt");
        KLog.d(TestApp.LOG_TAG, "openLogFile " + (b ? "ok" : "fail"));
        mBtnWritingData.setEnabled(b);
        mBtnStopToSave.setEnabled(b);
        mBtnReadLog.setEnabled(!b);
    }

    @OnClick(R.id.btn_write_log)
    public void onClickBtnWriteLog() {
        nLog++;
        logger1.writeLog("Logging data" + nLog + System.getProperty("line.separator"));
    }

    @OnClick(R.id.btn_close_log_file)
    public void onClickBtnCloseLogFile() {
        logger1.closeLogFile();
        mBtnWritingData.setEnabled(false);
        mBtnStopToSave.setEnabled(false);
        mBtnReadLog.setEnabled(true);
    }

    @OnClick(R.id.btn_read_log)
    public void onClickReadLog() {
        logger2.readFile(sLogLib.getExDir("Cloudchip"), "TestLogFile1.txt");
    }

    @OnClick(R.id.btn_save_file2)
    public void onClickBtnSaveFile2() {
        logger2.saveFile(sLogLib.getExDir("Cloudchip"), "TestSaveFile2.txt", "Hello world 22222");
        logger2.readFile(sLogLib.getExDir("Cloudchip"), "TestSaveFile2.txt");
    }

    @OnClick(R.id.btn_open_log_file2)
    public void onClickBtnOpenLogFile2() {
        boolean b = logger2.openLogFile(sLogLib.getExDir("Cloudchip"), "TestLogFile2.txt");
        KLog.d(TestApp.LOG_TAG, "openLogFile " + (b ? "ok" : "fail"));
        mBtnWritingData2.setEnabled(b);
        mBtnStopToSave2.setEnabled(b);
        mBtnReadLog2.setEnabled(!b);
    }

    @OnClick(R.id.btn_write_log2)
    public void onClickBtnWriteLog2() {
        nLog++;
        logger2.writeLog("Logging data" + nLog + System.getProperty("line.separator"));
    }

    @OnClick(R.id.btn_close_log_file2)
    public void onClickBtnCloseLogFile2() {
        logger2.closeLogFile();
        mBtnWritingData2.setEnabled(false);
        mBtnStopToSave2.setEnabled(false);
        mBtnReadLog2.setEnabled(true);
    }

    @OnClick(R.id.btn_read_log2)
    public void onClickReadLog2() {
        logger2.readFile(sLogLib.getExDir("Cloudchip"), "TestLogFile2.txt");
    }
    //endregion

    //region Life cycle
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
    //endregion
}
