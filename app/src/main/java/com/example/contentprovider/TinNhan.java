package com.example.contentprovider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class TinNhan extends AppCompatActivity {
    private static final int REQUEST_SMS_ASK_PERMISSIONS = 1002;
    private ListView Messagelv;
    private ArrayList<TinNhan_Class> listMessage;
    private ArrayAdapter<TinNhan_Class> MessageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_nhan);
        addControls();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_SMS}, REQUEST_SMS_ASK_PERMISSIONS);
        } else {
            showAllMessagesFromDevice();
        }
    }
    private void addControls() {
        Messagelv = findViewById(R.id.lvTinNhan);
        listMessage = new ArrayList<>();
        MessageAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, listMessage
        );
        Messagelv.setAdapter(MessageAdapter);
    }

    private void showAllMessagesFromDevice() {
        Uri uri = Uri.parse("content://sms/sent");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            int addressIndex = cursor.getColumnIndex("address");
            int bodyIndex = cursor.getColumnIndex("body");

            listMessage.clear();

            while (cursor.moveToNext()) {
                String address = addressIndex != -1 ? cursor.getString(addressIndex) : "Unknown";
                String body = bodyIndex != -1 ? cursor.getString(bodyIndex) : "No Content";

                TinNhan_Class message = new TinNhan_Class(address, body);
                listMessage.add(message);
            }

            cursor.close();
            MessageAdapter.notifyDataSetChanged();

            if (listMessage.isEmpty()) {
                Toast.makeText(this, "Không có tin nhắn nào trong hộp thư đến.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Không thể truy cập tin nhắn.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_SMS_ASK_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showAllMessagesFromDevice();
            } else {
                Toast.makeText(this, "Không có quyền đọc SMS", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
