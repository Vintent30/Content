package com.example.contentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DanhBa extends AppCompatActivity {

    ListView listView;
    ArrayList<Contact> dsDanhBa;
    ArrayAdapter<Contact> adapterDanhBa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_ba);
        addControls();
        showAllContact();
    }

    private void showAllContact() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            dsDanhBa.clear();  // Xóa dữ liệu cũ trước khi thêm mới
            while (cursor.moveToNext()) {
                String tenCotName = ContactsContract.Contacts.DISPLAY_NAME;
                String tenCotPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;

                int viTriCotName = cursor.getColumnIndex(tenCotName);
                int viTriCotPhone = cursor.getColumnIndex(tenCotPhone);

                String name = cursor.getString(viTriCotName);
                String phone = cursor.getString(viTriCotPhone);

                Contact contact = new Contact(name, phone);
                dsDanhBa.add(contact);
            }
            cursor.close();  // Đóng con trỏ sau khi sử dụng
            adapterDanhBa.notifyDataSetChanged();  // Cập nhật ListView
        }
    }

    private void addControls() {
        listView = findViewById(R.id.lvDanhBa);
        dsDanhBa = new ArrayList<>();
        adapterDanhBa = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, dsDanhBa
        );
        listView.setAdapter(adapterDanhBa);
    }
}
