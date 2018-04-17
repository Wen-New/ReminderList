package sg.edu.rp.c346.eseproject;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class More extends AppCompatActivity{
    TextView tvTitle;
    EditText etTitle;
    TextView tvObject;
    EditText etObject;
    TextView tvDate;
    TextView tvDateShown;
    Button btnDate;
    TextView tvTime;
    TextView tvTimeShown;
    Button btnTime;
    Button btnSubmit;
    int hour;
    int minute;
    int reqCode = 12345;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        tvTitle = (TextView)findViewById(R.id.textViewTitle);
        etTitle = (EditText)findViewById(R.id.editTextTitle);
        tvObject = (TextView)findViewById(R.id.textViewObjective);
        etObject = (EditText)findViewById(R.id.editTextObjective);
        tvDate = (TextView)findViewById(R.id.textViewDate);
        tvDateShown = (TextView)findViewById(R.id.textViewDateShown);
        btnDate = (Button)findViewById(R.id.buttonDateDialog);
        tvTime = (TextView)findViewById(R.id.textViewTime);
        tvTimeShown = (TextView)findViewById(R.id.textViewTimeShown);
        btnTime = (Button)findViewById(R.id.buttonTimeDialog);
        btnSubmit = (Button)findViewById(R.id.buttonSubmit);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cur = Calendar.getInstance();
                int dayOfMonth = cur.get(Calendar.DAY_OF_MONTH);
                int monthOfYear = cur.get(Calendar.MONTH);
                int year = cur.get(Calendar.YEAR);

                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tvDateShown.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                        ;
                    }
                };

                DatePickerDialog myDateDialog = new DatePickerDialog(More.this, myDateListener, year, monthOfYear, dayOfMonth);

                myDateDialog.show();
            }
        });
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                        hour = view.getCurrentHour();
                        minute = view.getCurrentMinute();
                        tvTimeShown.setText(String.format("%02d:%02d", hour, minute));
                    }
                };
                TimePickerDialog timeDialog = new TimePickerDialog(More.this, myTimeListener, hour, minute, true);
                timeDialog.show();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String purpose = etObject.getText().toString();
                String date = tvDateShown.getText().toString();
                String time = tvTimeShown.getText().toString();
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("object", purpose);
                intent.putExtra("date", date);
                intent.putExtra("time", time);

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.HOUR_OF_DAY, 8);

                Intent intent2 = new Intent(getBaseContext(),
                        Notification.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        getBaseContext(), reqCode,
                        intent2, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}
