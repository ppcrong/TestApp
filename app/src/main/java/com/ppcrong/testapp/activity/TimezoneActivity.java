package com.ppcrong.testapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ppcrong.testapp.R;
import com.socks.library.KLog;

import java.util.TimeZone;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimezoneActivity extends AppCompatActivity {

    // region [OnClick]
    @OnClick(R.id.btn_list_timezone)
    public void onClickBtnListTimezone() {

        KLog.i();

        String[] timezones = TimeZone.getAvailableIDs();
        int i = 0;
        for (String tzId : timezones) {
            if (i == 0 || i % 10 == 0) {

                KLog.i("-----------------------------------------------------------------");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                KLog.i(String.format("| %-40s | %-10s | %-6s |", "Name", "RawOffset", "GMT"));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            TimeZone tz = TimeZone.getTimeZone(tzId);
            String name = tz.getDisplayName();
            int offset = tz.getRawOffset();
            int hour = (int) (Math.abs(offset) / 60l / 60l / 1000l);
            int minute = (int) (Math.abs(offset) / 60l / 1000l) % 60;
            String gmt = ((offset >= 0) ? "+" : "-") + String.format("%02d:%02d", hour, minute);
            KLog.i(String.format("| %-40s | %-10d | %-6s |", name, offset, gmt));
            i++;

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

        setContentView(R.layout.activity_timezone);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
