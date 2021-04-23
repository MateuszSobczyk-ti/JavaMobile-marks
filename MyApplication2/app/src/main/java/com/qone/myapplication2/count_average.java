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
    double averageMark=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_average);
        //take the number of marks
        Bundle pack = getIntent().getExtras();
        int number=0;
        try {
            number = Integer.parseInt(pack.getString("number"));
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
                //count averageMark
                for (ModelOceny m:model) {
                    averageMark += m.getOcena();
                    System.out.println(m.getNazwa()+" ocena: "+m.getOcena());
                }
                averageMark = averageMark/adapter.getItemCount();
                averageMark = Math.round(averageMark*100.0)/100.0;
                System.out.println("srednia "+averageMark);

                //finish this activity and go to MainActivity
                Bundle pack=new Bundle();
                //load information about averageMark
                pack.putDouble("average",averageMark);
                Intent intent = new Intent();
                intent.putExtras(pack);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

}