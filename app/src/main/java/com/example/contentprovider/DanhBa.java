package com.example.contentprovider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;

public class DanhBa extends AppCompatActivity {

    private static final int REQUEST_CONTACTS_PERMISSION = 1001;
    private ListView contactListView;
    private ArrayList<DanhBa_Class> contactList;
    private ArrayAdapter<DanhBa_Class> contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_ba);

        addControls();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CONTACTS_PERMISSION);
        } else {
            showAllContactsFromDevice();
        }
    }

    private void addControls() {
        contactListView = findViewById(R.id.lvDanhBa);
        contactList = new ArrayList<>();
        contactAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactList);
        contactListView.setAdapter(contactAdapter);
    }

    private void showAllContactsFromDevice() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
            if (cursor != null) {
                contactList.clear();
                while (cursor.moveToNext()) {
                    String nameColumn = ContactsContract.Contacts.DISPLAY_NAME;
                    String phoneColumn = ContactsContract.CommonDataKinds.Phone.NUMBER;

                    int nameIndex = cursor.getColumnIndex(nameColumn);
                    int phoneIndex = cursor.getColumnIndex(phoneColumn);

                    String name = nameIndex != -1 ? cursor.getString(nameIndex) : "Unknown";
                    String phone = phoneIndex != -1 ? cursor.getString(phoneIndex) : "No Number";

                    DanhBa_Class contact = new DanhBa_Class(name, phone);
                    contactList.add(contact);
                }
                contactAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CONTACTS_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showAllContactsFromDevice();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
