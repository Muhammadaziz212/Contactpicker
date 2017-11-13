package com.example.muhammadaziz.sms;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   private static final int RESULT_PICK_CONTACT = 1001;


   Intent i;
    EditText edt1,edt2;
    Button btn1;
    SmsManager smsManager = SmsManager.getDefault();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1=(EditText)findViewById(R.id.no);
        edt2=(EditText)findViewById(R.id.pesan);
        btn1=(Button)findViewById(R.id.kirim);


    }

    public void add(View view) {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check whether the result is ok
        if (resultCode == RESULT_OK) {
            // Check for the request code, we might be usign multiple startActivityForReslut
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else {
            Log.e("MainActivity", "Failed to pick contact");
        }
    }

    private void contactPicked(Intent data) {

        Cursor cursor = null;
        try {
            String phoneNo = null ;
            String name = null;
            // getData() method will have the Content Uri of the selected contact
            Uri uri = data.getData();
            //Query the content uri
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            // column index of the phone number
            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            // column index of the contact name
            phoneNo = cursor.getString(phoneIndex);

            // Set the value to the textviews
           edt1.setText(phoneNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(View view) {

        String no=edt1.getText().toString();
        String msg=edt2.getText().toString();

        //Getting intent and PendingIntent instance
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

        //Get the SmsManager instance and call the sendTextMessage method to send message
        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(no, null, msg, pi,null);

        Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                Toast.LENGTH_LONG).show();

        // Clear the second EditText
        edt1.getText().clear();
        edt2.getText().clear();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}

