package com.qone.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class count_average extends AppCompatActivity{

    MyRecyclerViewAdapter adapter;
    double srednia=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_average);
        //take the number of marks
        Bundle pakunek = getIntent().getExtras();
        int number=0;
        try {
            number = Integer.parseInt(pakunek.getString("number"));
        }catch (NumberFormatException nfe){}

        //array of subject names from strings.xml
        String[] subjectNames = getResources().getStringArray(R.array.Subjects);

        // data to populate the RecyclerView with
        ArrayList<ModelOceny> rowArray = new ArrayList<>();
        for(int i=0; i<number; i++){
            rowArray.add(new ModelOceny(subjectNames[i],2));
        }

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvOceny);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, rowArray);
        recyclerView.setAdapter(adapter);


        Button averageButton = (Button)findViewById(R.id.button2);
        averageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View c){
                List<ModelOceny> model = adapter.getMarksList();
                for (ModelOceny m:model) {
                    srednia += m.getOcena();
                    System.out.println(m.getNazwa()+" ocena: "+m.getOcena());
                }
                System.out.println("rozmiar listy "+adapter.getItemCount());
                srednia = srednia/adapter.getItemCount();
                srednia = Math.round(srednia*100.0)/100.0;
                System.out.println("srednia "+srednia);

                Bundle pakunek=new Bundle();
                pakunek.putDouble("average",srednia);
                Intent intent = new Intent();
                intent.putExtras(pakunek);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }


}
/*        Bundle pakunek = getIntent().getExtras();
        String imie = pakunek.getString("imie");
        System.out.println(imie);
        imie.toUpperCase();
        Bundle pakunekPowrotny = new Bundle();
        pakunekPowrotny.putString("imie","string powrotny");
        Intent intencja = new Intent();
        intencja.putExtras(pakunekPowrotny);
*/        //setResult(RESULT_OK,intencja);
        //finish();