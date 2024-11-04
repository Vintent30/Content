package com.example.contentprovider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Date;

public class Call extends AppCompatActivity {
    private static final int REQUEST_CALL_LOG_PERMISSIONS = 1001;
    private ListView callLogListView;
    private ArrayList<Call_Class> callLogList;
    private ArrayAdapter<Call_Class> callLogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        addControls();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CALL_LOG}, REQUEST_CALL_LOG_PERMISSIONS);
        } else {
            showAllCallLogsFromDevice();
        }
    }

    private void addControls() {
        callLogListView = findViewById(R.id.lvNkiCgoi);
        callLogList = new ArrayList<>();
        callLogAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, callLogList
        );
        callLogListView.setAdapter(callLogAdapter);
    }
    private void showAllCallLogsFromDevice() {
        Uri uri = CallLog.Calls.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            int numberIndex = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            int typeIndex = cursor.getColumnIndex(CallLog.Calls.TYPE);
            int dateIndex = cursor.getColumnIndex(CallLog.Calls.DATE);
            int durationIndex = cursor.getColumnIndex(CallLog.Calls.DURATION);

            callLogList.clear();

            while (cursor.moveToNext()) {
                String number = (numberIndex != -1) ? cursor.getString(numberIndex) : "Unknown";
                String type = (typeIndex != -1) ? cursor.getString(typeIndex) : "Unknown";
                long dateMillis = (dateIndex != -1) ? cursor.getLong(dateIndex) : 0;
                Date date = new Date(dateMillis);
                String duration = (durationIndex != -1) ? cursor.getString(durationIndex) : "0";

                Call_Class callLogEntry = new Call_Class(duration, date, type, number);
                callLogList.add(callLogEntry);
            }
            cursor.close();
        }


        callLogAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CALL_LOG_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showAllCallLogsFromDevice();
            } else {
                Toast.makeText(this, "Không có quyền truy cập nhật ký cuộc gọi", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
