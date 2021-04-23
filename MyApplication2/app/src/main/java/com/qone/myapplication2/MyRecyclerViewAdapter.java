package com.qone.myapplication2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//class recyclerView - useful to present flexibly data as a list
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<ModelOceny> marksList;
    //LayoutInflater is useful to build Java Object based on XML files
    private LayoutInflater inflater;

    // data(subjectName and mark as a list) is passed into the constructor
    MyRecyclerViewAdapter(Activity context, List<ModelOceny> data) {
        this.inflater = context.getLayoutInflater();
        this.marksList = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.wiersz_ocena, null);
        //holder is useful to save reference to component in individual rows
        return new ViewHolder(view);
    }

    // binds,connect the data to the TextView and RadioGroup in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.myTextView.setTag(position);
        holder.radioGroup.setTag(marksList.get(position));

        ModelOceny modelOceny1 = marksList.get(position);
        String subjectName = modelOceny1.getNazwa();
        holder.myTextView.setText(subjectName);
        holder.radioGroup.check(R.id.radioButton6);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return marksList.size();
    }

    public List<ModelOceny> getMarksList(){return marksList;}


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView myTextView;
        RadioGroup radioGroup;
        RadioButton rb1, rb2, rb3, rb4;

        public ViewHolder(@NonNull View Row) {
            super(Row);
            myTextView = Row.findViewById(R.id.textView5);
            radioGroup = (RadioGroup) Row.findViewById(R.id.radioGroup);
            rb1 = (RadioButton) Row.findViewById(R.id.radioButton6);
            rb2 = (RadioButton) Row.findViewById(R.id.radioButton9);
            rb3 = (RadioButton) Row.findViewById(R.id.radioButton11);
            rb4 = (RadioButton) Row.findViewById(R.id.radioButton12);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    ModelOceny model = (ModelOceny)group.getTag();
                    RadioButton checked = Row.findViewById(group.getCheckedRadioButtonId());
                    model.setOcena(Integer.valueOf(checked.getText().toString()));
                }
            });
        }
    }
}
