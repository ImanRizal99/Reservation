package sg.edu.rp.c346.reservation;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameInput, phoneInput, groupInput;
    Button submitButton, resetButton;
    DatePicker dp;
    TimePicker tp;
    TextView errorOutput;
    String where = "Non-Smoking";

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.smoking:
                if (checked)
                    where = "Smoking";
                    break;
            case R.id.nonsmoking:
                if (checked)
                    where = "Non-Smoking";
                    break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameText);
        phoneInput = findViewById(R.id.phoneText);
        groupInput = findViewById(R.id.groupText);

        errorOutput = findViewById(R.id.errorOutput);

        submitButton = findViewById(R.id.submitButton);
        resetButton = findViewById(R.id.resetButton);

        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);

        dp.updateDate(2019, 6,1);
        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "";
                Integer phoneNumber = 0;
                Integer group = 0;


                String dayOfMonth = Integer.toString(dp.getDayOfMonth());
                String month = Integer.toString(dp.getMonth()+1);
                String year = Integer.toString(dp.getYear());
                String date = String.format("%s/%s/%s",dayOfMonth,month,year);

                String time = Integer.toString(tp.getCurrentHour());
                String minute = Integer.toString(tp.getCurrentMinute());
                String currentTime = String.format("%s:%s",time,minute);

                if (nameInput.getText().toString().trim().length() > 0) {
                    name = nameInput.getText().toString();
                }
                if (phoneInput.getText().toString().trim().length() > 0) {
                    phoneNumber = Integer.parseInt(phoneInput.getText().toString());
                }
                if (groupInput.getText().toString().trim().length() > 0) {
                    group = Integer.parseInt(groupInput.getText().toString());
                }

                if (name.equals("") || phoneNumber == 0 || group == 0) {
                    errorOutput.setText("Please enter name / phone / pax");
                }
                else {
                    Context context = getApplicationContext();
                    CharSequence output = String.format("Reservation is done at %s on %s\n" +
                            "from %s with %s people at %s area. Thank You!",currentTime,date,name,group,where);
                    int duration = Toast.LENGTH_LONG;

                    final Toast toastGiven = Toast.makeText(context, output, duration);
                    toastGiven.show();

                    errorOutput.setText("");

                }
            }

        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameInput.setText("");
                phoneInput.setText("");
               groupInput.setText("");

                dp.updateDate(2019, 6,1);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);

            }
        });

    }
}
