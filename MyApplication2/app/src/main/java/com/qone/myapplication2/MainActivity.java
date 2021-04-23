package com.qone.myapplication2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    double averageMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText nameEditText =(EditText)findViewById(R.id.editTextTextPersonName7);
        EditText secondNameEditText =(EditText)findViewById(R.id.editTextTextPersonName);
        //numberEditText store amount of marks
        EditText numberEditText =(EditText)findViewById(R.id.editTextNumber);
        List<Boolean> errorList = Arrays.asList(false,false,false);
        Button markButton = (Button)findViewById(R.id.button);

        //click button with text named "oceny" to call onClickListener and change activity on count_average activity
        markButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View c){
                Intent intencja = new Intent(MainActivity.this, count_average.class);
                //send to another activity amount of marks
                intencja.putExtra("number",numberEditText.getText().toString());
                startActivityForResult(intencja,1);
            }
        });

        //set errorList after text changed
        nameEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String name = nameEditText.getText().toString();
                        setErrorList(errorList, name, markButton, 0);
                    }
                }
        );

        //set error warning and toast when it lose focus
        nameEditText.setOnFocusChangeListener(
                new View.OnFocusChangeListener()
                {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        String name = nameEditText.getText().toString();
                        if(!hasFocus) {
                            if (name.length() == 0) {
                                nameEditText.setError("Field cannot be empty");
                                Toast.makeText(MainActivity.this, "Popraw dane", Toast.LENGTH_LONG).show();
                            } else if (!name.matches("[a-zA-Z ]+")) {
                                nameEditText.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                                Toast.makeText(MainActivity.this, "Popraw dane", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                }
        );

        //set errorList after text changed
        secondNameEditText.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String name = secondNameEditText.getText().toString();
                        setErrorList(errorList,name,markButton,1);
                    }
                }
        );

        //set error warning and toast when it lose focus
        secondNameEditText.setOnFocusChangeListener(
                new View.OnFocusChangeListener()
                {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        String secondName = secondNameEditText.getText().toString();
                        if(!hasFocus) {
                            if (secondName.length() == 0) {
                                secondNameEditText.setError("Field cannot be empty");
                                Toast.makeText(MainActivity.this, "Popraw dane", Toast.LENGTH_LONG).show();
                            } else if (!secondName.matches("[a-zA-Z ]+")) {
                                secondNameEditText.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                                Toast.makeText(MainActivity.this, "Popraw dane", Toast.LENGTH_LONG).show();
                            } else {
                            }
                        }
                    }
                }
        );

        //set errorList after text changed
        numberEditText.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        int number=0;
                        String number2 = numberEditText.getText().toString();
                        //cast String to number
                        try {
                            number = Integer.parseInt(number2);
                        } catch (NumberFormatException nfe){}
                        setErrorList(errorList,number2,markButton,2,number);
                    }
                }
        );

        //set error warning and toast when it lose focus
        numberEditText.setOnFocusChangeListener(
                new View.OnFocusChangeListener()
                {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        int number=0;
                        String number2 = numberEditText.getText().toString();
                        try {
                            number = Integer.parseInt(number2);
                        } catch (NumberFormatException nfe){}
                        if(!hasFocus) {
                            if (0 == number2.length()) {
                                numberEditText.setError("Field cannot be empty");
                                Toast.makeText(MainActivity.this, "Popraw dane", Toast.LENGTH_LONG).show();
                            } else if (number < 5 || number > 15) {
                                numberEditText.setError("ENTER number from <5;15>");
                                Toast.makeText(MainActivity.this, "Popraw dane", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
        );
    }

    //get information about averageMarks from count_average activity
    @Override
    protected void onActivityResult(int kodZadania, int kodZakonczenia, Intent wyniki) {
        super.onActivityResult(kodZadania, kodZakonczenia, wyniki);

        if (kodZakonczenia == RESULT_OK) {
            Bundle pakunek = wyniki.getExtras();
            averageMark = pakunek.getDouble("average");
            pokazSrednia(averageMark);
        }
    }

    protected void pokazSrednia(double averageMark){
        TextView message = (TextView)findViewById(R.id.textView3);
        Button ocenyButton = (Button)findViewById(R.id.button);
        //messages for button
        String mess1 = "Super!";
        String mess2 = "Tym razem mi nie poszlo";

        if(averageMark!=0) {
            message.setText("Twoja srednia to "+averageMark);
            //reduce text size
            ocenyButton.setTextSize(15);
            if(averageMark>=3) {
                ocenyButton.setText(mess1);
            }
            else{
                ocenyButton.setText(mess2);
            }
            //show message
            message.setVisibility(View.VISIBLE);
        }

        //click button below message to close application and get message
        ocenyButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(ocenyButton.getText()==mess1)
                Toast.makeText(MainActivity.this, "Gratulacje! Otrzymujesz zaliczenie.", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Wysylam podanie o zaliczenie warunkowe.", Toast.LENGTH_LONG).show();
            finish();
        }
    });

    }

    //save instance state and then restore saved information
    @Override
    protected void onSaveInstanceState(Bundle outState){
        EditText nameEditText =(EditText)findViewById(R.id.editTextTextPersonName7);
        EditText secondNameEditText =(EditText)findViewById(R.id.editTextTextPersonName);
        EditText numberEditText =(EditText)findViewById(R.id.editTextNumber);

        System.out.println("onSaveInstanceState");

        outState.putString("keyOne",nameEditText.getText().toString());
        outState.putString("keyTwo",secondNameEditText.getText().toString());
        outState.putString("keyThree",numberEditText.getText().toString());
        outState.putDouble("keySrednia",this.averageMark);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
         if(savedInstanceState!=null) {
            super.onRestoreInstanceState(savedInstanceState);

            EditText nameEditText = (EditText) findViewById(R.id.editTextTextPersonName7);
            EditText secondNameEditText = (EditText) findViewById(R.id.editTextTextPersonName);
            EditText numberEditText = (EditText) findViewById(R.id.editTextNumber);
            TextView message = (TextView) findViewById(R.id.textView3);
            Button ocenyButton = (Button) findViewById(R.id.button);

            System.out.println("onRestoreInstanceState");

            nameEditText.setText(savedInstanceState.getString("keyOne"));
            secondNameEditText.setText(savedInstanceState.getString("keyTwo"));
            numberEditText.setText(savedInstanceState.getString("keyThree"));
            this.averageMark = savedInstanceState.getDouble("keySrednia");
            //if averageMark is calculated - set the message and button
            if (averageMark != 0)
                pokazSrednia(averageMark);
        }
    }



    //checking error for Strings
    public void setErrorList(List<Boolean> errorList, String name, Button ocenyButton, int num){
        if (name.length() == 0) {
            errorList.set(num, false);
        } else if (!name.matches("[a-zA-Z ]+")) {
            errorList.set(num, false);
        } else {
            errorList.set(num, true);
        }

        //change button visibility depend on information about error
        if (!errorList.contains(false)) {
            ocenyButton.setVisibility(View.VISIBLE);
        }
        else{
            ocenyButton.setVisibility(View.INVISIBLE);
        }
    }

    //checking error for numbers
    public void setErrorList(List<Boolean> errorList, String name, Button ocenyButton, int num, int number){
        if (name.length() == 0) {
            errorList.set(num, false);
        } else if (number<5 || number>15) {
            errorList.set(num, false);
        } else {
            errorList.set(num, true);
        }

        //change button visibility depend on information about error
        if (!errorList.contains(false)) {
            ocenyButton.setVisibility(View.VISIBLE);
        }
        else{
            ocenyButton.setVisibility(View.INVISIBLE);
        }
    }
}