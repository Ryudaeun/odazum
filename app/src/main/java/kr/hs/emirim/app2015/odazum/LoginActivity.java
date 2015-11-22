package kr.hs.emirim.app2015.odazum;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "오다주움:LoginActivity";

    int year, month, day, hour, minute;
    EditText editName;
    Button but_birth;
    private Button but;
    private View v1;
    private String msg;
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            msg = String.format("%d / %d / %d", year, monthOfYear + 1, dayOfMonth);
            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
            but_birth.setText(msg);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editName = (EditText) findViewById(R.id.editName);
        but = (Button) findViewById(R.id.but_join);

        but.setOnClickListener(this);

        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);


        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(LoginActivity.this, dateSetListener, year, month, day).show();
                but_birth = (Button) findViewById(R.id.btn1);
            }
        });


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        Log.d(TAG, "login 성공");
    }
}
