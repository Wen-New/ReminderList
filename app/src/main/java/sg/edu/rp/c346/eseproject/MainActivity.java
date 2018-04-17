package sg.edu.rp.c346.eseproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAdd;
    ListView lvReminder;
    ArrayList<Reminder> reminderList;
    CustomAdapter caReminder;
    private static int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.buttonAdd);
        lvReminder = (ListView) findViewById(R.id.listViewReminder);
        reminderList = new ArrayList<Reminder>();
        caReminder = new CustomAdapter(this, R.layout.activity_row, reminderList);
        lvReminder.setAdapter(caReminder);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), More.class);
                startActivityForResult(intent, 1);
            }
        });

        lvReminder.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                //start next activity here
                Intent intent = new Intent(getBaseContext(), More.class);
                pos = position;
                startActivityForResult(intent, 2);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String title = data.getStringExtra("title");
                String object = data.getStringExtra("object");
                String date = data.getStringExtra("date");
                String time = data.getStringExtra("time");
                Reminder item = new Reminder(title, object, date, time);
                reminderList.add(item);
                caReminder.notifyDataSetChanged();
            }
        } else if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                String title = data.getStringExtra("title");
                String object = data.getStringExtra("object");
                String date = data.getStringExtra("date");
                String time = data.getStringExtra("time");
                Reminder item = new Reminder(title, object, date, time);
                reminderList.set(pos, item);
                caReminder.notifyDataSetChanged();
            }
        }
    }
}