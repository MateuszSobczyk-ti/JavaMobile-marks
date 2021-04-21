package com.qone.myapplication2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText nameEditText =(EditText)findViewById(R.id.editTextTextPersonName7);
        EditText secondNameEditText =(EditText)findViewById(R.id.editTextTextPersonName);
        EditText numberEditText =(EditText)findViewById(R.id.editTextNumber);
        List<Boolean> errorList = Arrays.asList(false,false,false);
        Button ocenyButton = (Button)findViewById(R.id.button);

        ocenyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View c){
                Intent intencja = new Intent(MainActivity.this, count_average.class);
                intencja.putExtra("number",numberEditText.getText().toString());
                startActivityForResult(intencja,1);
            }
        });


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
                        setErrorList(errorList, name, ocenyButton, 0);
                    }
                }
        );

        nameEditText.setOnFocusChangeListener(
                new View.OnFocusChangeListener()
                {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        String name = nameEditText.getText().toString();
                        System.out.println("FOCUS 1");
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
                        setErrorList(errorList,name,ocenyButton,1);
                    }
                }
        );

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
                        try {
                            number = Integer.parseInt(number2);
                        } catch (NumberFormatException nfe){}
                        setErrorList(errorList,number2,ocenyButton,2,number);
                    }
                }
        );

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

    @Override
    protected void onActivityResult(int kodZadania, int kodZakonczenia, Intent wyniki)
    {
        super.onActivityResult(kodZadania,kodZakonczenia,wyniki);
        System.out.println("main acitvity");
        if(kodZakonczenia==RESULT_OK)
        {
            double srednia=0;
            Bundle pakunek = wyniki.getExtras();
            srednia = pakunek.getDouble("average");
            if(srednia!=0)
                System.out.println("srednia main activity "+srednia);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        EditText nameEditText =(EditText)findViewById(R.id.editTextTextPersonName7);
        EditText secondNameEditText =(EditText)findViewById(R.id.editTextTextPersonName);
        EditText numberEditText =(EditText)findViewById(R.id.editTextNumber);

        System.out.println("onSaveInstanceState");

        outState.putString("keyOne",nameEditText.getText().toString());
        outState.putString("keyTwo",secondNameEditText.getText().toString());
        outState.putString("keyThree",numberEditText.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        EditText nameEditText =(EditText)findViewById(R.id.editTextTextPersonName7);
        EditText secondNameEditText =(EditText)findViewById(R.id.editTextTextPersonName);
        EditText numberEditText =(EditText)findViewById(R.id.editTextNumber);

        System.out.println("onRestoreInstanceState");

        nameEditText.setText(savedInstanceState.getString("keyOne"));
        secondNameEditText.setText(savedInstanceState.getString("keyTwo"));
        numberEditText.setText(savedInstanceState.getString("keyThree"));
    }




    public void setErrorList(List<Boolean> errorList, String name, Button ocenyButton, int num){
        if (name.length() == 0) {
            errorList.set(num, false);
        } else if (!name.matches("[a-zA-Z ]+")) {
            errorList.set(num, false);
        } else {
            errorList.set(num, true);
        }

        if (!errorList.contains(false)) {
            ocenyButton.setVisibility(View.VISIBLE);
        }
        else{
            ocenyButton.setVisibility(View.INVISIBLE);
        }
    }

    public void setErrorList(List<Boolean> errorList, String name, Button ocenyButton, int num, int number){
        if (name.length() == 0) {
            errorList.set(num, false);
        } else if (number<5 || number>15) {
            errorList.set(num, false);
        } else {
            errorList.set(num, true);
        }

        if (!errorList.contains(false)) {
            ocenyButton.setVisibility(View.VISIBLE);
        }
        else{
            ocenyButton.setVisibility(View.INVISIBLE);
        }
    }
}