package com.example.bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConnectDevice extends AppCompatActivity {

    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_device);

        List<String> s = new ArrayList<String>();
        for(BluetoothDevice bt : pairedDevices){
            s.add(bt.getName());
        }

        final ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, s);
        final ListView lv = findViewById(R.id.list_view);
        lv.setAdapter(adapter);
            //s.add(bt.getName());
            //System.out.println(bt.getName());
            //setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_connect_device, s));


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String device = lv.getItemAtPosition(i).toString();
                //System.out.println(s);
                Intent data = new Intent();
                data.setData(Uri.parse(device));
                setResult(RESULT_OK, data);

                finish();
            }
        });
    }


}
