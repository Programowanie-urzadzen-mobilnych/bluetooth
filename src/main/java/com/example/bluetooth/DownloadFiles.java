package com.example.bluetooth;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.bluetooth.BluetoothAdapter;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.annotation.Native;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class DownloadFiles extends AppCompatActivity {

    private Button button1;
    private RadioGroup rg1;
    //private RadioButton rb1;
    public String text;
    public String variable;
    public LocalDateTime startTime;
    public LocalDateTime endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_files);

        final DatePicker DatePicker1 = findViewById(R.id.datePicker1);
        final DatePicker DatePicker2 = findViewById(R.id.datePicker2);

        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePicker1.init(year, month, day, null);
        DatePicker2.init(year, month, day, null);

        final TimePicker Timer1 = findViewById(R.id.Timer1);
        final TimePicker Timer2 = findViewById(R.id.Timer2);

        button1 = findViewById(R.id.Button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                startTime = getDateFromDatePicker(DatePicker1, Timer1);
                endTime = getDateFromDatePicker(DatePicker2, Timer2);
                downloadFile();
            }
        });
    }

    protected void downloadFile(){
        rg1 = findViewById(R.id.radioGroup1);
        String command;
        System.out.println(rg1.getCheckedRadioButtonId());
        if(rg1.getCheckedRadioButtonId() == -1){
            Toast toast = Toast.makeText(this, "Żadne pole nie zostało wybrane", Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            //int selectedID = rg1.getCheckedRadioButtonId();
            int index = rg1.indexOfChild(findViewById(rg1.getCheckedRadioButtonId()));
            System.out.println("index: " + index);
            //rb1 = findViewById(selectedID);

            switch (index){
                case 0:
                    variable = "TEMP";
                    command =  "[\"get\", " + variable + ", "+ startTime + ", " + endTime + ":00]";
                    break;
                case 1:
                    variable = "HUM";
                    command =  "[\"get\", " + variable + ", "+ startTime + ", " + endTime + ":00]";
                    break;
                case 2:
                    variable = "PRESS";
                    command =  "[\"get\", " + variable + ", "+ startTime + ", " + endTime + ":00]";
                    break;
                case 3:
                    variable = "BAT_V";
                    command =  "[\"get\", " + variable + ", "+ startTime + ", " + endTime + ":00]";
                    break;
                case 4:
                    variable = "BAT_C";
                    command =  "[\"get\", " + variable + ", "+ startTime + ", " + endTime + ":00]";
                    break;
                case 5:
                    variable = "SOLAR_V";
                    command =  "[\"get\", " + variable + ", "+ startTime + ", " + endTime + ":00]";
                    break;
                case 6:
                    variable = "SOLAR_C";
                    command =  "[\"get\", " + variable + ", "+ startTime + ", " + endTime + ":00]";
                    break;
                case 7:
                    variable = "NODE_V";
                    command =  "[\"get\", " + variable + ", "+ startTime + ", " + endTime + ":00]";
                    break;
                case 8:
                    variable = "NODE_C";
                    command =  "[\"get\", " + variable + ", "+ startTime + ", " + endTime + ":00]";
                    break;
                default:
                    command = null;
                    break;
                // przesyłanie pliku przez BT - https://stackoverflow.com/questions/22899475/android-sample-bluetooth-code-to-send-a-simple-string-via-bluetooth?fbclid=IwAR1122nDQ-5fPkZcRlc6_FWoXv3I0u5BEIWQasK6At-bqpwWoPXzFOFMaIA
                // prawdopodobnie wystarczy przekopiowac funkcje ze stacka
                // dodanie permission(ewentualnie)
                // zakładamy że w aplikacji pierwszym ekranem będzie logowanie, a telefon trzeba będzie połączyć jeszcze przed wejściem do aplikacji(przez symulator)
                //
            }
            if(command != null){
                BluetoothAdapter blueAdapter = BluetoothAdapter.getDefaultAdapter();
                System.out.println(command);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LocalDateTime getDateFromDatePicker(DatePicker datePicker, TimePicker timePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        return LocalDateTime.of(year, month, day, hour, minute);
    }
}
