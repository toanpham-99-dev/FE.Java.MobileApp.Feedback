package com.example.feedbackapp.ui.statisticfeedback;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedbackapp.R;
import com.example.feedbackapp.model.Question;

import java.util.ArrayList;
import java.util.Arrays;

public class QuestionAdp extends RecyclerView.Adapter<QuestionAdp.ViewHolder> {
    // Initialize Arraylist
    private ArrayList<Question> arrayListQuestion;
    private int t;
    //private ArrayList<Integer> listValue;
    //Create constructor
   public QuestionAdp(ArrayList<Question> arrayListQuestion, int t){
       this.arrayListQuestion = arrayListQuestion;
       this.t = t;
   }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       //Initalize view
       View view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.list_row_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Set question name on TextView
        holder.questionName.setText(String.valueOf(t ) +"." + String.valueOf(position + 1) + ' ' + arrayListQuestion.get(position).getQuestionContent());
        // Set radio
        //holder.radioGroup.setVisibility(View.VISIBLE);
        //setRadio(holder, this.state[position]);
       // setRadio(holder, arrayListQuestion.get(position).getSty);
        holder.radiostrongdisagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               arrayListQuestion.get(position).setAnswer(0);
                //setRadio(holder, arrayListQuestion.get(position).getAnswer());
                setRadio(holder,0);
            }
        });
        holder.radiodisagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayListQuestion.get(position).setAnswer(1);
               // setRadio(holder, arrayListQuestion.get(position).getAnswer());
                setRadio(holder,1);
            }
        });
        holder.radioneutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               arrayListQuestion.get(position).setAnswer(2);
              //  setRadio(holder, arrayListQuestion.get(position).getAnswer());
                setRadio(holder,2);
            }
        });
        holder.radiostrongagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              arrayListQuestion.get(position).setAnswer(3);
//                setRadio(holder, arrayListQuestion.get(position).getAnswer());
                setRadio(holder,3);

            }
        });
        holder.radioagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayListQuestion.get(position).setAnswer(4);
//                setRadio(holder, arrayListQuestion.get(position).getAnswer());
                setRadio(holder,4);
                //ListValue.add(value);
            }
        });
       // ListValue.add(value);

    }

    @Override
    public int getItemCount() {
        return arrayListQuestion.size();
    }
/*
    public ArrayList<Integer> getListCheckedRadio()
    {
        ArrayList<Integer> listValue = new ArrayList<Integer>();
        for(int i = 0; i<getItemCount() ; i++)
        {
            listValue.add(0);
        }
        return listValue;
        //listValue.add(selection);
    }*/
    // Initialize radio
    private void setRadio(final ViewHolder holder, int selection) {

       // System.out.println("SELECT:" + selection);
        RadioButton b1 = holder.radiostrongdisagree;
        RadioButton b2 = holder.radiodisagree;
        RadioButton b3 = holder.radioneutral;
        RadioButton b4 = holder.radiostrongagree ;
        RadioButton b5 = holder.radioagree;

        b1.setChecked(false);
        b2.setChecked(false);
        b3.setChecked(false);
        b4.setChecked(false);
        b5.setChecked(false);

        if (selection == 0) b1.setChecked(true);
        if (selection == 1) b2.setChecked(true);
        if (selection == 2) b3.setChecked(true);
        if (selection == 3) b4.setChecked(true);
        if (selection == 4) b5.setChecked(true);
        //listValue.add(selection);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       //Inittial variable
        //int t;
       TextView questionName;
        public RadioGroup radioGroup;
        public RadioButton radiostrongdisagree, radioneutral, radiostrongagree, radiodisagree, radioagree;
       public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Asign variable
           questionName = itemView.findViewById(R.id.questionName);
           //radioGroup = (RadioGroup) itemView.findViewById(R.id.radioGroup);
           radioneutral = (RadioButton) itemView.findViewById(R.id.radioneutral);
           radiostrongdisagree = (RadioButton) itemView.findViewById(R.id.radiostrongdisagree);
           radiostrongagree = (RadioButton) itemView.findViewById(R.id.radiostrongagree);
           radiodisagree = (RadioButton) itemView.findViewById(R.id.radiadisagree);
           radioagree = (RadioButton) itemView.findViewById(R.id.radioagree);
        }
    }
}
