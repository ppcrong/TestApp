package com.ppcrong.testapp.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.widget.Button;

import com.ppcrong.loglib.LogLib;
import com.ppcrong.loglib.sLogLib;
import com.ppcrong.rxpermlib.RxPermLib;
import com.ppcrong.testapp.R;
import com.ppcrong.testapp.TestApp;
import com.socks.library.KLog;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogLibActivity extends AppCompatActivity {

    // region [Widget]
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
    // endregion [Widget]

    // region [OnClick]
    @OnClick(R.id.btn_context_dir_s)
    public void onClickBtnContextDirS() {

        KLog.i();

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
        KLog.i(TestApp.LOG_TAG, "getFilesDir", dirPath, dirAbsPath, dirCcPath);

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
        KLog.i(TestApp.LOG_TAG, "getFilesDir", filePath, fileAbsPath, fileCcPath);

        /* getCacheDir */
        KLog.i(TestApp.LOG_TAG, "getCacheDir", getCacheDir().getPath());

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
        KLog.i(TestApp.LOG_TAG, "getExternalFilesDir", dirPath, dirAbsPath, dirCcPath);

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
        KLog.i(TestApp.LOG_TAG, "getExternalFilesDir", filePath, fileAbsPath, fileCcPath);

        // Create file
        try {
            if (file.exists()) {
                boolean b = file.delete();
                KLog.i("Delete " + (b ? "ok" : "fail"));
            }
            boolean b = file.createNewFile();
            KLog.i("Create " + (b ? "ok" : "fail"));
        } catch (IOException e) {
            KLog.e(Log.getStackTraceString(e));
        }

        /* getExternalCacheDir */
        KLog.i(TestApp.LOG_TAG, "getExternalCacheDir", getExternalCacheDir().getPath());

    }

    @OnClick(R.id.btn_env_dir_s)
    public void onClickBtnEnvDirS() {

        KLog.i();

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
        KLog.i(TestApp.LOG_TAG, "getExternalStorageDirectory", dirPath, dirAbsPath, dirCcPath);

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
        KLog.i(TestApp.LOG_TAG, "getExternalStoragePublicDirectory", dirPath, dirAbsPath, dirCcPath);

    }

    @OnClick(R.id.btn_save_file_s)
    public void onClickBtnSaveFileS() {

        sLogLib.saveFile(sLogLib.getExDir("LogLib"), "TestSaveFile.txt", "Hello world sssss");
        sLogLib.readFile(sLogLib.getExDir("LogLib"), "TestSaveFile.txt");
        KLog.i("filename: " + sLogLib.genFileName("Test_s", "postfix", "log"));
        KLog.i("filenameMs: " + sLogLib.genFileNameWithMs("Test_s", "postfix", "log"));
    }

    @OnClick(R.id.btn_open_log_file_s)
    public void onClickBtnOpenLogFileS() {

        boolean b = sLogLib.openLogFile(sLogLib.getExDir("LogLib"), "TestLogFile.txt");
        KLog.i(TestApp.LOG_TAG, "openLogFile " + (b ? "ok" : "fail"));
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

        String path = sLogLib.closeLogFileReturnPath();
        KLog.i("path: " + path);
        mBtnWritingData_s.setEnabled(false);
        mBtnStopToSave_s.setEnabled(false);
        mBtnReadLog_s.setEnabled(true);
    }

    @OnClick(R.id.btn_read_log_s)
    public void onClickReadLogS() {

        sLogLib.readFile(sLogLib.getExDir("LogLib"), "TestLogFile.txt");
    }

    LogLib logger1 = new LogLib();
    LogLib logger2 = new LogLib();

    @OnClick(R.id.btn_save_file)
    public void onClickBtnSaveFile() {

        logger1.saveFile(sLogLib.getExDir("LogLib"), "TestSaveFile1.txt", "Hello world 11111");
        logger1.readFile(sLogLib.getExDir("LogLib"), "TestSaveFile1.txt");
        KLog.i("filename: " + logger1.genFileName("Test1", "postfix", "log"));
        KLog.i("filenameMs: " + logger1.genFileNameWithMs("Test1", "postfix", "log"));
    }

    @OnClick(R.id.btn_open_log_file)
    public void onClickBtnOpenLogFile() {

        boolean b = logger1.openLogFile(sLogLib.getExDir("LogLib"), "TestLogFile1.txt");
        KLog.i(TestApp.LOG_TAG, "openLogFile " + (b ? "ok" : "fail"));
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

        String path = logger1.closeLogFileReturnPath();
        KLog.i("path: " + path);
        mBtnWritingData.setEnabled(false);
        mBtnStopToSave.setEnabled(false);
        mBtnReadLog.setEnabled(true);
    }

    @OnClick(R.id.btn_read_log)
    public void onClickReadLog() {

        logger2.readFile(sLogLib.getExDir("LogLib"), "TestLogFile1.txt");
    }

    @OnClick(R.id.btn_save_file2)
    public void onClickBtnSaveFile2() {

        logger2.saveFile(sLogLib.getExDir("LogLib"), "TestSaveFile2.txt", "Hello world 22222");
        logger2.readFile(sLogLib.getExDir("LogLib"), "TestSaveFile2.txt");
        String filename = logger2.genFileName("Test2", "postfix2", "log");
        String filenameMs = logger2.genFileNameWithMs("Test2", "postfix2", "log");
    }

    @OnClick(R.id.btn_open_log_file2)
    public void onClickBtnOpenLogFile2() {

        boolean b = logger2.openLogFile(sLogLib.getExDir("LogLib"), "TestLogFile2.txt");
        KLog.i(TestApp.LOG_TAG, "openLogFile " + (b ? "ok" : "fail"));
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

        String path = logger2.closeLogFileReturnPath();
        KLog.i("path: " + path);
        mBtnWritingData2.setEnabled(false);
        mBtnStopToSave2.setEnabled(false);
        mBtnReadLog2.setEnabled(true);
    }

    @OnClick(R.id.btn_read_log2)
    public void onClickReadLog2() {

        logger2.readFile(sLogLib.getExDir("LogLib"), "TestLogFile2.txt");
    }
    // endregion [OnClick]

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

        setContentView(R.layout.activity_log_lib);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RxPermLib.checkPermissions(this, () -> {

            KLog.i("WRITE_EXTERNAL_STORAGE granted");
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }
    // endregion [Private Function]
}
